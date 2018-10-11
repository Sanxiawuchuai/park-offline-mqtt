
package com.drzk.timer;

import com.drzk.common.ParkMethod;
import com.drzk.mapper.ParkStandardChargeMapper;
import com.drzk.offline.vo.CenterUpdateVO;
import com.drzk.online.service.OnlineDSScanSever;
import com.drzk.online.service.ParkConfigService;
import com.drzk.parklib.load.LoadChannelPara;
import com.drzk.service.ILoadDeviceListService;
import com.drzk.service.entity.Head;
import com.drzk.service.entity.MainBoardMessage;
import com.drzk.service.impl.ClientMQTT;
import com.drzk.utils.CompUtils;
import com.drzk.utils.GlobalPark;
import com.drzk.utils.JsonUtil;
import com.drzk.vo.ParkStandardCharge;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * ClassName:CenterUpdateTask <br>
 * Date: 2018年8月3日 下午5:24:32 <br>
 * 
 * @author chenlong
 * @version
 * @since JDK 1.8
 * @see
 */

@Component
@Scope("prototype")
public class CenterUpdateTask implements Runnable {
	@Autowired
	private ParkConfigService parkConfigService;
	@Autowired
	private ILoadDeviceListService loadDeviceService;
	@Autowired
	private ParkStandardChargeMapper parkStandardChargeMapper;
	@Autowired
	OnlineDSScanSever onlineDSScanSever;
	
	private String json;
	
	private static Logger logger = Logger.getLogger("userLog");
	/**
	* json.
	*
	* @param json the json to set
	* @since JDK 1.8
	*/
	public void setJson(String json) {
		this.json = json;
	}

	@Override
	public void run() {
		try {
			MainBoardMessage<Head, CenterUpdateVO> messaeVo = JsonUtil.jsonToBoardMessage(json, Head.class,
					CenterUpdateVO.class);
			if (messaeVo == null) {
				return;
			}
			int type = Integer.parseInt(messaeVo.getBody().getType());
			CenterUpdateVO VO = messaeVo.getBody();
			/**
			 * https://rd-trac.drzk.cn/yun/ticket/1991 1.车牌授权（修改卡、发行卡、卡延期、卡销户） 下发硬件，同步云端
			 * 2.人员档案信息 增、修改、删除 同步云端 3.车场系统更改 下发硬件，同步云端 4.岗亭设置 下发硬件，同步云端 5.控制器设置 下发硬件，同步云端
			 * 6.相机设置 下发硬件，同步云端 7.收费设置 下发硬件，同步云端
			 */

			switch (type) {
			case 1://车牌授权
				loadDeviceService.loadUserInfo();
				onlineDSScanSever.parkcaruser();
				onlineDSScanSever.parkcaroperation();
				break;
			case 2://人员档案信息
				onlineDSScanSever.personnelinfo();
				break;
			case 3: //车场系统更改
				reloadSysPara();
				onlineDSScanSever.parksetting();
				//推送车场参数到岗亭
				ParkMethod.pushToBoxs();
				break;
			case 4://岗亭设置
				reloadBoxSet(VO);
				onlineDSScanSever.boxinfo();
				//推送车场参数到岗亭
				ParkMethod.pushToBoxs();
				break;
			case 5: //控制器设置
				GlobalPark.parkDeviceStatus.clear();//控制有变化清空状态
				reloadChannelSet(VO);
				onlineDSScanSever.controllerinfo();
				//推送车场参数到岗亭
				ParkMethod.pushToBoxs();
				break;
			case 6:
				reloadCameraSet(VO);
				onlineDSScanSever.parkcameras();
				//推送车场参数到岗亭
				ParkMethod.pushToBoxs();
				break;
			case 7: //收费设置
				reloadChargeStandard(VO);
				onlineDSScanSever.feescale();
				break;
			case 8://黑白单设置
				onlineDSScanSever.parkblackcar();
				break;
			case 9://月租费率设置
				onlineDSScanSever.parkmonthlyfeetype();
				break;
			case 10://车位组设置
				onlineDSScanSever.carGroupInfo();
				break;
			case 11://部门设置
				onlineDSScanSever.departmentinfo();
				break;
			case 12://打折模式设置
				reloadDisInfo();
				onlineDSScanSever.couponrule();
				//推送车场参数到岗亭
				ParkMethod.pushToBoxs();
				break;
			case 13: //打折商家设置
				reloadEquipmentsInfo();
				onlineDSScanSever.couponeqinfo();
				// 推送车场参数到岗亭
				ParkMethod.pushToBoxs();
				break;
			case 14://车牌授权
				loadDeviceService.loadUserInfo();
				onlineDSScanSever.parkcaruser();				//同步车主信息
				onlineDSScanSever.parkcaroperation();
				break;
				//---------------------------以下只限调试 
			case 15://车场级别重授权下发硬件
				onlineDSScanSever.parkcarin();
				break;
			case 16://公司信息设置
				onlineDSScanSever.companyinfo();
				break;
			case 17://免费车设置
				reloadFreeInfo();
                ParkMethod.pushToBoxs();
				break;
			case 18://超时收费设置
				onlineDSScanSever.pushOverTimes();
				break;
			case 20://车场编号授权
				parkConfigService.reloadSysParams();			//重新加载参数
				onlineDSScanSever.reportAuth();
				break;
			default:
				break;
			}
		} catch (Exception ex) {
			logger.error("后台管理数据发送线程:", ex);
		}
	}

	/** 重新加载系统参数 
	 * @throws Exception */
	private void reloadSysPara() throws Exception {
		parkConfigService.reloadSysParams();

		//重新启动与云端之间的通讯
		if("1".equals(GlobalPark.properties.getProperty("UPLOAD_DATA_CLOUD"))&&"1".equals(GlobalPark.properties.getProperty("CLOUD_STATUS"))) {
			//加载服务器相关的参数
			CompUtils.getAllSn();
			ClientMQTT.start();
		}else{
            ClientMQTT.unScribe();         //取消相关的订阅
        }

		// 车场参数加载到主板
		LoadChannelPara.loadPara();
	}

	/** 重新加载岗亭设置 */
	private void reloadBoxSet(CenterUpdateVO VO) {
		// 内存更新
//		GlobalPark.parkLocalSet.clear();
		loadDeviceService.getWorkStation();
	}

	/** 重新加载控制器设置 
	 * @throws Exception */
	private void reloadChannelSet(CenterUpdateVO VO) throws Exception {
		// 内存更新
		GlobalPark.parkContset.clear();
		loadDeviceService.getContolInfo();
		// 车场参数加载到主板
		LoadChannelPara.loadPara();
	}
	
	/** 重新加载相机设置
	 * @throws Exception */
	private void reloadCameraSet(CenterUpdateVO VO) throws Exception {
		// 内存更新
		GlobalPark.parkCamset.clear();
		loadDeviceService.getCamInfo();
		// 车场参数加载到主板
		//LoadChannelPara.loadPara();
	}
	
	/** 重新加载收费标准*/
	private void reloadChargeStandard(CenterUpdateVO VO) {
		List<ParkStandardCharge> parkStandardCharge = parkStandardChargeMapper.selectGroupByCardType();
		LoadChannelPara.loadChargeStandard(parkStandardCharge);
	}

	/**加载免费车类型*/
	private void reloadFreeInfo(){
		GlobalPark.parkFreeType.clear();		//清除免费类型
		loadDeviceService.getFreeTypeInfo();
	}
	/**加载优惠模式*/
	private void reloadDisInfo(){
		GlobalPark.parkDisInfo.clear();		//清除优惠模式
		loadDeviceService.getDisInfo();
	}
	/**加载优惠模式*/
	private void reloadEquipmentsInfo(){
		GlobalPark.parkEquipments.clear();		//清除优惠模式
		loadDeviceService.getEquipmentsInfo();
	}
}
