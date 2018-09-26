
package com.drzk.offline.service;


/**
  * 与岗亭通信的接口 <br>
 * Date: 2018年7月30日 上午10:05:11 <br>
 * 
 * @author chenlong
 * @version
 * @since JDK 1.8
 * @see
 */
public interface IBoxOperation {
	/**
	 * 登录
	 * @param model
	 * @param head
	 */
    void boxLogin(String jsonStr);

	/**
	 * 岗亭登出
	 * @param model
	 * @param head
	 */
    void boxLogout(String jsonStr);
	
	/**
	 * 交班
	 * @param model
	 * @param head
	 */
    void boxChangeshifts(String jsonStr);
	/**
	 * 手工入场
	 * @param model
	 * @param head
	 */
    void boxHandIn(String jsonStr);
	/**
	 * 手工出场
	 * @param model
	 * @param head
	 */
    void boxHandOut(String jsonStr);
	
	/**
	 * 电脑开闸
	 * @param model
	 * @param head
	 */
    void boxComputerGate(String jsonStr);
	
	/**
	 * 高峰模式设置
	 * @param model
	 * @param head
	 */
    void boxPeakMode(String jsonStr);
	
	/** 
	 * 软件触发相机 
	 * @param jsonStr
	 */
    void boxReshoot(String jsonStr);
	
	/**
	 * 入场确认开闸岗亭返回
	 * @param jsonStr
	 */
    void boxInIsOpen(String jsonStr);
	
	/**
	 * 入场校正车牌
	 * @param jsonStr
	 */
    void boxInChanelCorrecting(String jsonStr);
	void boxOutChanelCorrecting(String jsonStr);
	/**
	 * 出场开闸确认
	 * 
	 * @param jsonStr
	 */
    void boxOutIsOpen(String jsonStr);
	
	/**
	 * 找入场记录
	 * @param jsonStr
	 */
    void boxGetInRecord(String jsonStr);
	/** 收费改变 */
	void boxPayCharge(String jsonStr);
	/** 中央缴费 */
	void centrialCharge(String jsonStr);
	/** 中央收费确认 */
	void centrialChargePaySuccess(String jsonStr);
	
	/**
	 * 获取新的无牌车号码
	 * @param model
	 * @param head
	 */
    void getNoCarNo(String jsonStr);
    
    /**
	 * 播报收费语音
	 * @param model
	 * @param head
	 */
	void boxSpeechSounds(String jsonStr);
	
	/**
	 * 修改入场车牌
	 * @param model
	 * @param head
	 */
	void boxModifyCarNo(String jsonStr);
	
	/**
	 * 计费
	 * @param model
	 * @param head
	 */
	void getPayCharge(String jsonStr);
}
