package com.drzk.app;

import com.drzk.parklib.load.LoadChannelPara;
import com.drzk.service.ILoadDeviceListService;
import com.drzk.utils.SpringUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

@Component
public class Application {
	@Autowired
	private ILoadDeviceListService loadDeviceService;
//	@Autowired
//	private LoadUserInfo loadUserInfo;
	private static Logger logger = Logger.getLogger("userLog");

	public static void main(String[] args) {
		start(); // 加载车场参数
	}

	
	/**
	 * 重新启动 Date: 2018年5月16日 下午2:09:11 <br>
	 * 
	 * @author wangchengxi
	 */
	public void restart() {
		SpringUtil.cleanApplicationContext();
		start();
		System.out.println("restart success!!!!");
	}

	/**
	 * 启动加载车场参数 Date: 2018年5月16日 下午2:09:11 <br>
	 * 
	 * @author wangchengxi
	 */
	@SuppressWarnings("resource")
	public static void start() {
		try {
			logger.debug("准备启动车场服务端......");
			new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
			Application app = SpringUtil.getBean(Application.class);
			intPark(app);
			logger.debug("车场服务端启动成功......");
		} catch (Exception e) {
			logger.error("车场服务端启动失败:"+e);
		}
	}
	/**
	 * 车场初始化线程 Date: 2018年09月23日 下午2:09:11 <br>
	 * 
	 * @author wangchengxi
	 */
    private static void intPark(Application app) {
    	ThreadPoolTaskExecutor threadPool = SpringUtil.getBean(ThreadPoolTaskExecutor.class);
    	threadPool.execute(new Runnable() {
			@Override
			public void run() {
				try {
					logger.debug("车场服务端初始化开始......");
					// 加载岗亭列表
					app.loadDeviceService.getWorkStation();
					// 加载控制器列表
					app.loadDeviceService.getContolInfo();
					// 加载相机列表
					app.loadDeviceService.getCamInfo();
					// 加载车类型列表
					app.loadDeviceService.getAccountTypeInfo();
					// 加载免费列表
					app.loadDeviceService.getFreeTypeInfo();
					// 加载商家列表
					app.loadDeviceService.getEquipmentsInfo();
					// 加载打折列表
					app.loadDeviceService.getDisInfo();

					// 给主板加载参数
					LoadChannelPara.loadPara();
					// 给主板加载时间
					LoadChannelPara.loadSysTime();
					
					//加载车牌授权数据
					//app.loadDeviceService.loadUserInfo();
					logger.debug("车场服务端初始化结束......");
				}catch (Exception e) {
					logger.error("车场服务端初始失败:"+e);
				}
			}
    	});
    }
    
}
