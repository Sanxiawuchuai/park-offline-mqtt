package com.drzk.online.constant;

import com.drzk.online.service.DownOfService;
import com.drzk.utils.JsonUtil;
import com.drzk.utils.SpringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhangbin
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: 线上下发数据功能
 * @date 2018/9/11 11:11
 */
public class DownOffEvent {

    private static Logger log = LoggerFactory.getLogger("userLog");

    /**
     * 订阅线上的主题，根据方法名不能，存放到不同的表中
     *
     * @param jsonStr
     */
    public static void receiveJson(String jsonStr) {
        DownOfService downOfService = SpringUtil.getBean( DownOfService.class );
        try {
            String method = JsonUtil.getMethodByJsonStr( jsonStr );
            switch (method) {
                case ConstantUtil.BLACK_CAR_METHOD:                //特殊车辆同步
                    downOfService.syCarBlackInfo( jsonStr );
                    break;
                case ConstantUtil.DEVICE_LONG_RENTAL_METHOD:                //长租计费同步
                    downOfService.syncLongRental( jsonStr );
                    break;
                case ConstantUtil.DEVICE_PAID_STOP_METHOD:                //临停计费同步
                    downOfService.syncPaidStop( jsonStr );
                    break;
                case ConstantUtil.DEVICE_DISCOUNT_METHOD:                //打折同步
                    downOfService.syncDiscount( jsonStr );
                    break;
                case ConstantUtil.DEVICE_MERCHANTS_METHOD:                //商户同步
                    downOfService.syncMerchants( jsonStr );
                    break;
                case ConstantUtil.DEVICE_TIME_OUT_METHOD:                //超时收费
                    downOfService.syncTimeOut( jsonStr );
                    break;    
                case ConstantUtil.CARPORT_GROUP_METHOD:
                    downOfService.syCarportGroup( jsonStr );
                    break;
                //同步车场设置
                case ConstantUtil.CONFIG_METHOD:
                    downOfService.syncParkConfig( jsonStr );
                    break;
                //同步人事
                case ConstantUtil.COMPANY_METHOD:
                case ConstantUtil.DEPARTMENT_METHOD:
                case ConstantUtil.PERSONNEL_METHOD:
                    downOfService.syncParkPersonnel( jsonStr );
                    break;
                //同步出入口设备
                case ConstantUtil.BOX_METHOD:
                case ConstantUtil.CONTROLLER_METHOD:
                case ConstantUtil.CAMERA_METHOD:
                    downOfService.syncParkDevice( jsonStr );
                    break;
                case ConstantUtil.HAIRPIN_METHOD:                   //发行车牌同步(同时保存账户操作记录)
                    downOfService.syncIssueInfo(jsonStr);
                    break;
                case ConstantUtil.BATCH__RENT_PARKING:                   //发行车牌同步(同时保存账户操作记录)
                    downOfService.syncBatchRentParking(jsonStr);
                    break;
                case ConstantUtil.OPEN_DOOR:                   //远程开闸
                    downOfService.syncOpenDoor(jsonStr);
                    break;
            }
        } catch (Exception e) {
            log.error( "线上同步数据异常:",e);
        }
    }

    /**
     * 云端下发车场编号，存放到数据库中
     * @param json
     */
    public static void lowerNum(String json){
        DownOfService downOfService = SpringUtil.getBean( DownOfService.class );
        downOfService.lowerNum(json);       //下发车场操作
    }
}
