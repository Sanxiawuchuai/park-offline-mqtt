
package com.drzk.timer;

import java.util.Date;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.kie.api.cdi.KSession;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.drzk.common.InOutRealTimeBase.Step;
import com.drzk.fact.InRealTimeBase;
import com.drzk.service.IParkingService;
import com.drzk.utils.LoggerUntils;

/**
 * ClassName:ParkingInTask <br>
 * Function: TODO ADD FUNCTION. <br>
 * Reason: TODO ADD REASON. <br>
 * Date: 2018年6月14日 上午9:43:04 <br>
 * 
 * @author chenlong
 * @version
 * @since JDK 1.8
 * @see
 */
@Component
@Scope("prototype")
public class ParkingInTask implements Runnable{
	private static Logger logger = Logger.getLogger("userLog");
	@KSession("ksession_parkin")
	private KieSession kSession;
	
	@Autowired
	private IParkingService parkingInService;
	
	private InRealTimeBase parkIn;
	
	
	/**
	* parkIn.
	*
	* @param parkIn the parkIn to set
	* @since JDK 1.8
	*/
	public void setParkIn(InRealTimeBase parkIn) {
		this.parkIn = parkIn;
	}

	@Override
	public void run() {
		try {
			kSession.setGlobal("parkInService", parkingInService);
			kSession.insert(parkIn);
			kSession.fireAllRules();
		}catch (Exception ex) {
			logger.error("入场处理线程:",ex);
		}
	}

}
