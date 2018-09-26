
package com.drzk.timer;

import com.drzk.bean.MqttMessageVO;
import com.drzk.utils.LoggerUntils;
import org.apache.log4j.Logger;
import org.kie.api.runtime.KieSession;

/**
 * ClassName:ParkingOutTask <br>
 * Date: 2018年6月14日 上午10:42:23 <br>
 * 
 * @author chenlong
 * @version
 * @since JDK 1.8
 * @see
 */
//@Component
//@Scope("prototype")
public class TestTask implements Runnable {
	private static Logger logger = Logger.getLogger("userLog");

	//@KSession("test_ksession")
	private KieSession kSession;

	@Override
	public void run() {
		try {
			MqttMessageVO vo = new MqttMessageVO();
			//vo.setStatus(1);
			
			//kSession.setGlobal("parkOutService", parkingOutService);
			kSession.insert(vo);
			kSession.fireAllRules();

//			System.out.println("status:" + vo.getStatus());
//			System.out.println("topic:" + vo.getTopic());
//			System.out.println("body:" + vo.getBody());
		} catch (Exception e) {
			LoggerUntils.error(logger, e);
		}

	}
}
