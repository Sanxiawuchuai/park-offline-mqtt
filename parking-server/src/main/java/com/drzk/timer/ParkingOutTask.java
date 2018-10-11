
package com.drzk.timer;

import java.util.Date;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.kie.api.cdi.KSession;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.drzk.fact.OutRealTimeBase;
import com.drzk.service.IParkingService;

/**
 * ClassName:ParkingOutTask <br>
 * Date: 2018年6月14日 上午10:42:23 <br>
 * 
 * @author chenlong
 * @version
 * @since JDK 1.8
 * @see
 */
@Component
@Scope("prototype")
public class ParkingOutTask implements Runnable {
	private static Logger logger = Logger.getLogger("userLog");

	@KSession("ksession_parkout")
	private KieSession kSession;

	@Autowired
	private IParkingService parkingOutService;

	private OutRealTimeBase parkOut;

	/**
	 * parkOut.
	 *
	 * @param parkOut
	 *            the parkOut to set
	 * @since JDK 1.8
	 */
	public void setParkOut(OutRealTimeBase parkOut) {
		this.parkOut = parkOut;
	}

	@Override
	public void run() {
		try {
			long t1 = new Date().getTime();
			kSession.setGlobal("parkOutService", parkingOutService);
			//parkOut.setNextStep(Step.START);
			kSession.insert(parkOut);
			kSession.fireAllRules();
			long t2 = new Date().getTime();

			System.out.println(t2 - t1);
			for (Entry<String, Object> entry : parkOut.getStatusMap().entrySet()) {
				System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
			}
		} catch (Exception ex) {
			logger.error("出场处理线程:",ex);
		}

	}
}
