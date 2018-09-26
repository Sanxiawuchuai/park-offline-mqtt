package com.drzk.utils;

import java.util.Hashtable;
import java.util.Properties;
import java.util.Vector;

import com.drzk.fact.InRealTimeBase;
import com.drzk.fact.OutRealTimeBase;
import com.drzk.vo.ParkAccountType;
import com.drzk.vo.ParkCamSet;
import com.drzk.vo.ParkChannelSet;
import com.drzk.vo.ParkDeviceStatus;
import com.drzk.vo.ParkDisInfo;
import com.drzk.vo.ParkEquipments;
import com.drzk.vo.ParkFreeType;
import com.drzk.vo.ParkLocalSet;
import com.drzk.vo.SysParameters;

/**
 * 车场全局参数类
 * Date: 2018年5月16日 下午2:09:11 <br>
 * @author wangchengxi
 */
public class GlobalPark {
	public static int g_MaxIndex = 64;
	//车场临时车是否满位
	public static boolean p_TempFullNum=false;
	//车场月卡车是否满位
	public static boolean p_monthFullNum=false;
	/**
	 * 车场系统参数
	 */
	public static Properties properties = new Properties();
	//本机IP地址
	public static String localIP;
	//车场岗亭列表
	public static Vector<ParkLocalSet> parkLocalSet =new Vector<ParkLocalSet>();
	//车场通道列表
	public static Vector<ParkChannelSet> parkContset =new Vector<ParkChannelSet>();
	//车场相机列表
	public static Vector<ParkCamSet> parkCamset=new Vector<ParkCamSet>();
	//车类型列表
	public static Vector<ParkAccountType> parkAccountType=new Vector<ParkAccountType>();
	//车场免费列表 
	public static Vector<ParkFreeType> parkFreeType=new Vector<ParkFreeType>();
	//车场商家列表
	public static Vector<ParkEquipments> parkEquipments=new Vector<ParkEquipments>();
	//车场优惠列表
	public static Vector<ParkDisInfo> parkDisInfo=new Vector<ParkDisInfo>();
	//通道状态列表
	public static Vector<ParkDeviceStatus> parkDeviceStatus=new Vector<ParkDeviceStatus>();
	//控制器事件
	public static Hashtable<String,InRealTimeBase>  inChannelEvent = new Hashtable<String,InRealTimeBase> ();
	public static Hashtable<String,OutRealTimeBase>  outChannelEvent = new Hashtable<String,OutRealTimeBase> ();
	
}
