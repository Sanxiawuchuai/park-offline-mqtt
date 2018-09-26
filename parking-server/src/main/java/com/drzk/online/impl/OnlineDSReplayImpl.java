package com.drzk.online.impl;

import com.drzk.mapper.*;
import com.drzk.online.service.OnlineDSReplaySever;
import com.drzk.online.vo.ObjectBody;
import com.drzk.online.vo.ReplayHeadVO;
import com.drzk.online.vo.ReplayVO;
import com.drzk.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 处理同步数据结果
 * @author yxf
 *
 */
@Service
public class OnlineDSReplayImpl implements OnlineDSReplaySever
{
	@Autowired
	ParkCarInMapper parkCarInMapper;
	@Autowired
	ParkCarOutMapper parkCarOutMapper;
	@Autowired
	ParkLocalSetMapper parkLocalSetMapper;
	@Autowired
	ParkCamSetMapper parkCamSetMapper;
	@Autowired
	ParkCarBlackListMapper parkCarBlackListMapper;
	@Autowired
	ParkCarInExceptionMapper parkCarInExceptionMapper;
	@Autowired
	YktCardIssueMapper yktCardIssueMapper;
	@Autowired
	ParkSumUserMapper parkSumUserMapper;
	@Autowired
	ParkCentralChargeMapper parkCentralChargeMapper;
	@Autowired
	ParkMonthSetMapper parkMonthSetMapper;
	@Autowired
	PerDeptMapper perDeptMapper;
	@Autowired
	PerPersonsMapper perPersonsMapper;
	@Autowired
	ParkEquipmentsMapper parkEquipmentsMapper;
	@Autowired
	ParkDisInfoMapper parkDisInfoMapper;
	@Autowired
	ParkDisDetailMapper parkDisDetailMapper;
	@Autowired
	ParkStandardChargeMapper parkStandardChargeMapper;
	@Autowired
	BackUpParkCarInMapper backUpParkCarInMapper;
	@Autowired
	ParkChannelSetMapper parkChannelSetMapper;
	@Autowired
	PerCompanyMapper perCompanyMapper;
	
	@Override
	public void OnlineDSReplay(String jsonStr)
	{
		try
		{
		ReplayVO<ReplayHeadVO,ObjectBody> messgeVo = JsonUtil.jsonToReplayVO(jsonStr,
				ReplayHeadVO.class, ObjectBody.class);
		List<ObjectBody> body = messgeVo.getBody();
		if(body.size()>0)
		{ 
			List<String> objectIDS = new ArrayList<String>();
			for(ObjectBody model :body)
			{
				if(model != null && model.getObjectId()!= null)
				{
					objectIDS.add(model.getObjectId());
				}
			}
			if(objectIDS!= null && objectIDS.size()>0)
			{
				String method = JsonUtil.getMethodByJsonStr(jsonStr);
				method =method.replace("park/", "");
				method = method.replace("/v1", "");
				switch(method)
				{
				case "parkcarin":
					parkCarInMapper.updateDSStatusFalied(1, objectIDS);
					break;
				case "boxinfo":
					parkLocalSetMapper.updateDS(1,objectIDS);
					break;
				case "parkcarout":
					parkCarOutMapper.updateDSStatus(1,objectIDS);
					break;
				case "controllerinfo":
					parkChannelSetMapper.updateDSStatus(1,objectIDS);
					break;
				case "parkcameras":
					parkCamSetMapper.updateDSStatus(1, objectIDS);
					break;
				case "parkblackcar"://黑名单
					parkCarBlackListMapper.updateDSStatusFalied(1, objectIDS);
					break;
				case "parkunusualcarin"://异常入场
					parkCarInExceptionMapper.updateDSStatusFalied(1, objectIDS);
					break;
				case "parkcaroperation"://车主充值信息(实时上传)
					yktCardIssueMapper.updateDSStatusFalied(1, objectIDS);
					break;
				case "parksumusers"://交班
					parkSumUserMapper.updateDSStatusFalied(1, objectIDS);
					break;
				case "parkcenterpayment":
					parkCentralChargeMapper.updateDSStatusFalied(1,objectIDS);//更新上传状态为1
					break;
				case "parkmonthlyfeetype":
					parkMonthSetMapper.updateDSStatusFalied(1,objectIDS);//更新上传状态为1
					break;
				case "departmentinfo"://部门信息 
					perDeptMapper.updateStatus(1,objectIDS);//更新上传状态为1
					break;
				case "parkdeletecarin":
//					parkCarInMapper.updateDSStatusFalied(1, objectIDS);
//					backUpParkCarInMapper.insertF(objectIDS);
					parkCarInMapper.deleteDSDelete(objectIDS);
					break;
				case "parkopengateillegally":
					parkCarOutMapper.updateDSStatus(1, objectIDS);
					break;
				case "parkcaruser":
					List<String> pids = perPersonsMapper.selectPidByPuid(objectIDS);
					yktCardIssueMapper.updateDSStatusBypid(1, pids);
					break;
				case "personnelinfo":
					perPersonsMapper.updateStatus(1, objectIDS);
					break;
				case "couponeqinfo":
					parkEquipmentsMapper.updateDSStatus(1, objectIDS);
					break;
				case "couponrule":
					parkDisInfoMapper.updateDSStatus(1, objectIDS);
					break;
				case "couponuserlist":
					parkDisDetailMapper.updateDSStatus(1, objectIDS);
					break;
				case "feescale":
					parkStandardChargeMapper.updateDSStatus(1, objectIDS);
					break;
				case "companyinfo":
					perCompanyMapper.updateStatus(1, objectIDS);
					break;
				
				}
				
			}
			
		}
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
	}
}
