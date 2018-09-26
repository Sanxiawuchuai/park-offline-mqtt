package com.drzk.online.impl;

import com.drzk.common.ParkMethod;
import com.drzk.mapper.*;
import com.drzk.online.service.YktIssueService;
import com.drzk.online.vo.CarportAndCarVO;
import com.drzk.online.vo.RenewalVO;
import com.drzk.parklib.send.MainBoardSdk;
import com.drzk.service.entity.LoadUserMsgBody;
import com.drzk.service.entity.LoadUserMsgBodyReturn;
import com.drzk.service.entity.MainBoardMessage;
import com.drzk.service.entity.ReplyHead;
import com.drzk.utils.GlobalPark;
import com.drzk.utils.StringUtils;
import com.drzk.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author zhangbin
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: 发卡的实现类
 * @date 2018/9/17 15:13
 */
@Service("yktIssueService")
public class YktIssueServiceImpl implements YktIssueService {

    @Autowired
    private YktCardIssueMapper yktCardIssueMapper;
    @Autowired
    private YktCardIssueParkMapper yktCardIssueParkMapper;
    @Autowired
    private YktCardIssueRelMapper yktCardIssueRelMapper;
    @Autowired
    private YktCardRsmoneyMapper yktCardRsmoneyMapper;
    @Autowired
    private PerPersonsMapper perPersonsMapper;
    @Autowired
    private ParkControlPlanRelMapper parkControlPlanRelMapper;
    @Autowired
    private VwParkCarIsuseMapper vwParkCarIsuseMapper;


    @Override
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public int saveYktIssue(CarportAndCarVO carportAndCarVO, String objectId, String perName, String perTel,Integer operationType) {
        YktCardIssue yktCardIssue =yktCardIssueMapper.findByObjectId(objectId);     //判断是修改还是新增
        int result=0;
        Integer status=null;
        if(operationType==0||operationType==1){         //如果是发行或延期，状态为空
            status=0;
        }else if(operationType==6){             //销户
            status=6;
        }

        //判断是修改还是新增
        if(yktCardIssue==null) {
            PerPersons perPersons = perPersonsMapper.getPerInfo(perName, StringUtils.encode(perTel));
            yktCardIssue = new YktCardIssue();            //发卡的主表
            yktCardIssue.setcFlag(4);             //卡介质默认为纯车牌
            yktCardIssue.setStatus(status);            //卡状态
            yktCardIssue.setIsShare(0);                                       //默认不共享车位
            yktCardIssue.setIsLoad(1);                                        //默认上传标志
            yktCardIssue.setCuid(objectId);
            yktCardIssue.setBalanceMoney(carportAndCarVO.getBalanceMoney());
            yktCardIssue.setpId(perPersons.getPid());
            yktCardIssue.setCreateDate(new Date());
            result = yktCardIssueMapper.insert(yktCardIssue);
        }else{
            yktCardIssue.setStatus(status);
            yktCardIssue.setCreateDate(new Date());
            result=yktCardIssueMapper.update(yktCardIssue);
        }

        if(result==1){
            saveIssuePark(yktCardIssue.getId(),carportAndCarVO,operationType);        //保存副表信息
            saveIssueRel(yktCardIssue.getId(), 1);              //控制器表
        }

        return result;
    }

    /**
     * 保存副表记录
     * @param yktId
     * @param carportAndCarVO
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void saveIssuePark(Integer yktId,CarportAndCarVO carportAndCarVO,Integer operationType){
        //如果是发卡，新增副表记录
        if(operationType==0) {
            YktCardIssuePark yktCardIssuePark=new YktCardIssuePark();
            yktCardIssuePark.setYktId(yktId);
            yktCardIssuePark.setCarNo(carportAndCarVO.getCarNo());
            yktCardIssuePark.setEndDate(carportAndCarVO.getEndTime());
            yktCardIssuePark.setStartDate(carportAndCarVO.getStartTime());
            yktCardIssuePark.setCardType(carportAndCarVO.getCardTypeId());
            yktCardIssuePark.setsType(carportAndCarVO.getStype());
            yktCardIssuePark.setPlanId(1);          //车场级别默认为1
            yktCardIssueParkMapper.insert(yktCardIssuePark);        //保存副表记录
            saveCardRsMoney(carportAndCarVO,yktCardIssuePark,operationType,null);               //保存交易记录表
        }else{
            YktCardIssuePark yktCardIssuePark=yktCardIssueParkMapper.findById(yktId);
            Date oldTime=yktCardIssuePark.getEndDate();         //保存默认的历史记录
            setSTime(yktCardIssuePark,carportAndCarVO.getStartTime());
            yktCardIssuePark.setEndDate(carportAndCarVO.getEndTime());
            yktCardIssueParkMapper.update(yktCardIssuePark);        //保存副表记录
            saveCardRsMoney(carportAndCarVO,yktCardIssuePark,operationType,oldTime);               //保存交易记录表
        }
    }

    /**
     * 保存卡片下载标志功能
     *
     * @param yktId  卡片的ID
     * @param planId 级别id
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    public void saveIssueRel(Integer yktId, Integer planId) {
        yktCardIssueRelMapper.deleteByYktId(yktId);           //先删除相关卡片的信息，再新增
        YktCardIssueRel yktCardIssueRel = new YktCardIssueRel();
        //得到当前卡片选中的权限级别有多少个控制器
        List<ParkControlPlanRel> list = parkControlPlanRelMapper.selectByPlanId(planId);
        yktCardIssueRel.setSign(0);         //默认下载标记为0，为未下载
        yktCardIssueRel.setYktId(yktId);
        list.forEach(parkControlPlanRel -> {
            yktCardIssueRel.setMachNo(parkControlPlanRel.getMachNo());
            yktCardIssueRelMapper.insert(yktCardIssueRel);         //保存到未下载标志类中
        });
    }

    /**
     * 保存卡操作的记录
     * @param
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    public void saveCardRsMoney(CarportAndCarVO carportAndCarVO,YktCardIssuePark yktCardIssuePark,Integer operationType,Date oldTime){
        YktCardRsmoney yktCardRsmoney=new YktCardRsmoney();
        yktCardRsmoney.setYktid(yktCardIssuePark.getYktId());
        yktCardRsmoney.setBalanceMoney(carportAndCarVO.getBalanceMoney());
        yktCardRsmoney.setCreateDate(new Date());
        yktCardRsmoney.setFrontDate(oldTime);
        yktCardRsmoney.setNewStartDate(yktCardIssuePark.getStartDate());
        yktCardRsmoney.setNewEndDate(yktCardIssuePark.getEndDate());
        yktCardRsmoney.setPayType(carportAndCarVO.getPayType());

        Integer cardType=carportAndCarVO.getCardTypeId();       //账户类型
        if (operationType == 0) {           //卡状态为发行正常
            yktCardRsmoney.setsType(0);            //状态为发行
            if (cardType > 50 && cardType < 60) {                       //如果是储值卡
                yktCardRsmoney.setBeforeMoney(0.0);                     //默认存0
            }
        } else if (operationType == 6) {     //卡状态为销户
            yktCardRsmoney.setsType(6);            //状态为发行
            if (cardType > 50 && cardType < 60) {                       //如果是储值卡
                yktCardRsmoney.setBeforeMoney(carportAndCarVO.getBalanceMoney());                     //默认存0
            }
        }else if(operationType==1){         //延期
            yktCardRsmoney.setsType(1);            //状态为延期
            //yktCardRsmoney.setBeforeMoney(yktCardIssueVo.getBalanceMoney());
        }
        yktCardRsmoneyMapper.insert(yktCardRsmoney);
    }

    /** 重新加载车牌授权 数据 */
    @Override
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public void reloadCarGrantData() {
        YktCardIssueRel condition = new YktCardIssueRel();
        condition.setSign(0);
        // 查询有改变的内容
        List<YktCardIssueRel> cardRel = yktCardIssueRelMapper.selectByCondition(condition);
        for (YktCardIssueRel yktCardIssueRel : cardRel) {
            Integer yktId = yktCardIssueRel.getYktId();// 一卡通id
            Integer macNo = yktCardIssueRel.getMachNo(); // 控制器机号
            if(macNo == null) {
                return;
            }
            // 查询一卡通信息
            VwParkCarIsuse parkCardCondition = new VwParkCarIsuse();
            parkCardCondition.setId(yktId);
            VwParkCarIsuse result = vwParkCarIsuseMapper.selectMonthCar(parkCardCondition);
            LoadUserMsgBody userMsgBody = new LoadUserMsgBody();

            userMsgBody.setCarType(result.getCardType().toString());
            userMsgBody.setCarNo(result.getCarNo());
            userMsgBody.setUserTimeStart(result.getStartDate());
            userMsgBody.setUserTimeEnd(result.getEndDate());
            userMsgBody.setStoredValue(result.getBalanceMoney().toString());
            userMsgBody.setListType("0");

            ParkChannelSet channel = ParkMethod.getChannelSetByControlIndex(macNo);

            int status = result.getStatus();// 0正常、1正在挂失、2已挂失、3正在解挂、4已补卡、5挂失退款、6已销户
            String method = "loadUserInfo";
            if (status == 6) {
                method = "deleteUserInfo";
            }


            // 下发硬件
            MainBoardMessage<ReplyHead, LoadUserMsgBodyReturn> replyVo = MainBoardSdk.sendAndGet(channel.getDsn(),
                    method, userMsgBody, LoadUserMsgBodyReturn.class);

            if(replyVo!=null) {
                if ("0".equals(replyVo.getHead().getStatus())) {
                    // 修改标志位
                    yktCardIssueRel.setSign(1);
                    yktCardIssueRelMapper.updateByPrimaryKey(yktCardIssueRel);
                }
            }
        }
    }

    @Override
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public int delay(RenewalVO renewalVO) {
        Integer result=0;
        YktCardIssuePark yktCardIssuePark=yktCardIssueParkMapper.findCardByCarNo(renewalVO.getCarNo());         //根据车牌号码，得到是否存在当前的信息
        if(yktCardIssuePark!=null) {
            saveDelay(yktCardIssuePark,renewalVO);              //保存卡操作历史

            setSTime(yktCardIssuePark,renewalVO.getStartDate());
            yktCardIssuePark.setEndDate(renewalVO.getEndDate());
            result=yktCardIssueParkMapper.update(yktCardIssuePark);         //更新副表的有效期

            if (renewalVO.getCarTypeId() > 50 && renewalVO.getCarTypeId() < 60) {       //如果账户类型是储值车
                YktCardIssue yktCardIssue=yktCardIssueMapper.findById(yktCardIssuePark.getYktId());         //得到当前的主表信息
                yktCardIssue.setBalanceMoney(yktCardIssue.getBalanceMoney()+renewalVO.getAmount());         //储值车的值叠加
                yktCardIssueMapper.update(yktCardIssue);            //修改主表的数据
            }

           saveIssueRel(yktCardIssuePark.getYktId(),yktCardIssuePark.getPlanId());          //更新下载标志

        }
        return result;
    }

    /**
     * 线上续期下发保存数据
     * @param yktCardIssuePark
     * @param renewalVO
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    public void saveDelay(YktCardIssuePark yktCardIssuePark,RenewalVO renewalVO){
        YktCardIssue yktCardIssue=yktCardIssueMapper.findById(yktCardIssuePark.getYktId());
        YktCardRsmoney yktCardRsmoney=new YktCardRsmoney();
        yktCardRsmoney.setYktid(yktCardIssuePark.getYktId());
        yktCardRsmoney.setCreateDate(new Date());
        yktCardRsmoney.setFrontDate(yktCardIssuePark.getEndDate());
        yktCardRsmoney.setNewStartDate(renewalVO.getStartDate());
        yktCardRsmoney.setNewEndDate(renewalVO.getEndDate());
        yktCardRsmoney.setPayType(renewalVO.getPayType());
        yktCardRsmoney.setsType(1);            //状态为续期
        if (renewalVO.getCarTypeId() > 50 && renewalVO.getCarTypeId() < 60) {                       //如果是储值卡
            yktCardRsmoney.setBeforeMoney(yktCardIssue.getBalanceMoney());                     //默认存0
            yktCardRsmoney.setBalanceMoney(renewalVO.getAmount()+yktCardIssue.getBalanceMoney());
        }else{
            yktCardRsmoney.setBeforeMoney(yktCardIssue.getBalanceMoney());                     //默认存0
            yktCardRsmoney.setBalanceMoney(yktCardIssue.getBalanceMoney());
        }
        yktCardRsmoneyMapper.insert(yktCardRsmoney);
    }

    /**
     * 延期时，起始时间的变更问题
     * @param oldParkVo 旧的历史副表
     * @param newStartDate 当前起始日期
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    public YktCardIssuePark setSTime(YktCardIssuePark oldParkVo,Date newStartDate){
        String delayType=GlobalPark.properties.getProperty("DELAY_TYPE");        //获取当前延期的类型
        if(delayType.equals("2")){              //如果计费类型为2，连续性计费，起止时间不改变
            return oldParkVo;
        }else{
            Date nowDate=new Date();       //获取当前的时间
            Date oldDate=oldParkVo.getEndDate();     //旧的日期
            if(oldDate.before(nowDate)){        //当前延期日期已过期
                oldParkVo.setStartDate(newStartDate);
            }
            return oldParkVo;
        }
    }
}
