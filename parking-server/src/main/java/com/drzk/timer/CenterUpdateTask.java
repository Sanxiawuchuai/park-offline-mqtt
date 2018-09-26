
package com.drzk.timer;

import com.drzk.common.ParkMethod;
import com.drzk.mapper.ParkStandardChargeMapper;
import com.drzk.mapper.SysParametersMapper;
import com.drzk.mapper.VwParkCarIsuseMapper;
import com.drzk.mapper.YktCardIssueRelMapper;
import com.drzk.offline.vo.CenterUpdateVO;
import com.drzk.online.service.OnlineDSScanSever;
import com.drzk.parklib.load.LoadChannelPara;
import com.drzk.parklib.send.MainBoardSdk;
import com.drzk.service.ILoadDeviceListService;
import com.drzk.service.entity.*;
import com.drzk.utils.GlobalPark;
import com.drzk.utils.JsonUtil;
import com.drzk.utils.LoggerUntils;
import com.drzk.utils.StringUtils;
import com.drzk.vo.*;
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
@Scope("singleton")
public class CenterUpdateTask implements Runnable {
	@Autowired
	private SysParametersMapper sysParametersMapper;
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
			case 1:
				loadDeviceService.loadUserInfo();
				onlineDSScanSever.parkcaruser();
				onlineDSScanSever.parkcaroperation();
				break;
			case 2:
				onlineDSScanSever.personnelinfo();
				break;
			case 3:
				reloadSysPara(VO);
				onlineDSScanSever.parksetting();
				//推送车场参数到岗亭
				ParkMethod.pushToBoxs();
				break;
			case 4:
				reloadBoxSet(VO);
				onlineDSScanSever.boxinfo();
				//推送车场参数到岗亭
				ParkMethod.pushToBoxs();
				break;
			case 5:
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
			case 7:
				reloadChargeStandard(VO);
				onlineDSScanSever.feescale();
				break;
			case 8:
				onlineDSScanSever.parkblackcar();
				break;
			case 9:
				onlineDSScanSever.parkmonthlyfeetype();
				break;
			case 10:
				onlineDSScanSever.carGroupInfo();
				break;
			case 11:
				onlineDSScanSever.departmentinfo();
				break;
			case 12:
				onlineDSScanSever.couponrule();
				//推送车场参数到岗亭
				ParkMethod.pushToBoxs();
				break;
				case 13:
				onlineDSScanSever.couponeqinfo();
				//推送车场参数到岗亭
				ParkMethod.pushToBoxs();
				break;
			case 14:
				loadDeviceService.loadUserInfo();
				onlineDSScanSever.parkcaruser();				//同步车主信息
				onlineDSScanSever.parkcaroperation();
				break;
				//---------------------------以下只限调试 
			case 15:
				onlineDSScanSever.parkcarin();
				break;
			case 16:
				onlineDSScanSever.companyinfo();;
				break;
			case 17:
				reloadFreeInfo();
                ParkMethod.pushToBoxs();
				break;
			case 18:
				onlineDSScanSever.pushOverTimes();
				break;
			default:
				break;
			}
		} catch (Exception e) {
			LoggerUntils.error(logger, e);
		}

	}

	/** 重新加载系统参数 
	 * @throws Exception */
	private void reloadSysPara(CenterUpdateVO VO) throws Exception {
		List<SysParameters> sysPara = sysParametersMapper.selectAll();

		// 服务器内存更新
		for (SysParameters sysParameters : sysPara) {
			String key = sysParameters.getParameterCode();
			String value = sysParameters.getParameterValue();
			//System.out.println("key:"+ key);
			if(GlobalPark.properties.containsKey(key) && !StringUtils.isNullOrEempty(value)) {
				GlobalPark.properties.setProperty(key, value);
			}
		}
		// 车场参数加载到主板
		LoadChannelPara.loadPara();
	}

	/** 重新加载岗亭设置 */
	private void reloadBoxSet(CenterUpdateVO VO) {
		// 内存更新
		GlobalPark.parkLocalSet.clear();
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
}
