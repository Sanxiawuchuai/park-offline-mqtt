
package com.drzk.service;

/**
 * 加载设备信息<br>
 * 包括:岗亭列表,控制器列表,相机列表<br>
 * 获取本机IP地址属于公共方法,与车场无关,所以放在ParkMethod中<br>
 * 系统参数在初始化Spring时已加载<br>
 * Date: 2018年5月26日 上午10:10:34 <br>
 * 
 * @author chenlong
 * @version
 * @since JDK 1.8
 * @see
 */
public interface ILoadDeviceListService {

	/**
	 * 加载控制器列表<br>
	 * 
	 * @author chenlong
	 * @since JDK 1.8
	 */
    void getContolInfo();

	/**
	 * 加载岗亭列表<br>
	 * @author chenlong
	 * @since JDK 1.8
	 */
    void getWorkStation();

	/**
	 * 加载相机列表<br>
	 * @author chenlong
	 * @since JDK 1.8
	 */
    void getCamInfo();
	/**
	 * 加载车场车类型<br>
	 * @author wangchengxi
	 * @since JDK 1.8
	 */
    void getAccountTypeInfo();
	/**
	 * 加载免费类型<br>
	 * @author wangchengxi
	 * @since JDK 1.8
	 */
    void getFreeTypeInfo();
	/**
	 * 加载商家列表<br>
	 * @author wangchengxi
	 * @since JDK 1.8
	 */
    void getEquipmentsInfo();
	
	/**
	 * 加载优惠<br>
	 * @author wangchengxi
	 * @since JDK 1.8
	 */
    void getDisInfo();
    /**
	 * 加载用户授权数据<br>
	 * @author wangchengxi
	 * @since JDK 1.8
	 */
    void loadUserInfo();
}
