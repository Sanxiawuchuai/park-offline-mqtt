
package com.drzk.service;

/**
 * 加载主控板参数<br>
 * 包括确认工单，加载时间，配置信息，网络参数等<br>
 * Date: 2018年5月28日 下午1:41:59 <br>
 * 
 * @author chenlong
 * @version
 * @since JDK 1.8
 * @see
 */
public interface ILoadControlBoardPramaterService {
	//确认工单
    boolean orderNoConfirm();
	
	//加载控制器时间
    boolean loadContTime();
	
	//加载控制器配置
    boolean loadContSys();
	
	//加载网络参数
    boolean setNetworkPar();
	
	//加载收费标准参数
    boolean loadChargeStand();
	
	//加载卡片参数
    boolean loadCardSet();
	
}
