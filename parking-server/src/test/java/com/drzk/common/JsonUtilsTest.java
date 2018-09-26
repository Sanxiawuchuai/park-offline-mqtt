
package com.drzk.common;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.drzk.common.InOutRealTimeBase.Step;
import com.drzk.common.vo.TestVo;
import com.drzk.fact.InRealTimeBase;
import com.drzk.fact.OutRealTimeBase;
import com.drzk.service.entity.BroadcastReadDeviceMsgBody;
import com.drzk.service.entity.Head;
import com.drzk.service.entity.MainBoardMessage;
import com.drzk.utils.FastJsonUtils;
import com.drzk.utils.JsonUtil;
import com.drzk.vo.ParkCarIn;

import net.sf.json.JSONObject;

/**
 * ClassName:JsonUtilsTest <br>
 * Date: 2018年6月12日 下午2:04:59 <br>
 * 
 * @author chenlong
 * @version
 * @since JDK 1.8
 * @see
 */
public class JsonUtilsTest {

	public static void main(String[] args) {
		TestVo vo = new TestVo();
		vo.setName("chenlong");
		vo.setNextStep(Step.END);
		
		String jsonStr = JsonUtil.objectToJsonStr(vo);
		System.out.println(jsonStr);
		
		String str = "{\"name\":\"chenlong\",\"nextStep\":\"START\"}";
		TestVo test = JsonUtil.jsonStrToObject(str, TestVo.class);
		Step next = test.getNextStep();
		
		System.out.println(next == Step.START);
	}
	
	
	
}
