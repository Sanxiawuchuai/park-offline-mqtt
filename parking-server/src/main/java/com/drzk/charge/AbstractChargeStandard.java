
package com.drzk.charge;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.drzk.charge.vo.PaymentVo;
import com.drzk.mapper.ParkStandardChargeMapper;
import com.drzk.mapper.VwParkCarchargeMapper;
import com.drzk.online.vo.ParkCenterPayment;
import com.drzk.vo.ParkCentralCharge;
import com.drzk.vo.ParkOverTimeSet;
import com.drzk.vo.ParkStandardCharge;

/**
 * 所有收费标准的公共类 <br>
 * Date: 2018年6月13日 下午3:47:33 <br>
 * 
 * @author chenlong
 * @version
 * @since JDK 1.8
 * @see
 */
public abstract class AbstractChargeStandard {

//	@Autowired
//	private ParkStandardChargeMapper parkStandardChargeMapper;
//	@Autowired
//	private VwParkCarchargeMapper vwParkCarchargeMapper;
//	

	
	public int getOvertimeCharge(ParkCentralCharge model) {
	     return 0;
	}
	/**
	 * 计费入口
	 * @param startTime 计费开始时间
	 * @param endTime 计费结束时间
	 * @param cardType 计费卡类型
	 * @return 返回值为分
	 */
	public int getCharge(Date startTime, Date endTime, int cardType) {
		return 0;
	}
	
	/**
	 * 多次进出优惠(只针对过零点)
	 * @param carNo 车牌
	 * @param carType 卡类型
	 * @param payFee 应缴费用
	 * @return 多次进出优惠之后的费用
	 
	public double getMimoCharge(String carNo,int carType,double payFee)
	{
		double payMoney = 0;//费用
		int topMoney = 0;
		
		if(carNo.substring(0,3) == "NOP") return payFee;
		ParkStandardCharge standModel = parkStandardChargeMapper.selectByCardType(carType);
		if(standModel != null)
		{
			if(standModel.getUnitType() == 0)
			{
				topMoney = standModel.getTopMoney() * 10;
			}
			else
				topMoney = standModel.getTopMoney();
		}
		else
			return payFee;
		
		if(topMoney ==0)
			return payFee;
		
		payMoney = vwParkCarchargeMapper.selectByCarNo(carNo).doubleValue();
		if(payMoney == 0)
		{
			 if(topMoney >payFee)
				 return payFee;
			 else
				 return topMoney;
		}
		else
		{
			if(topMoney - payMoney >=0)
			{
				if(topMoney - payMoney -payFee >=0)
					return payFee;
				else
					return topMoney - payMoney;
			}
			else
				return 0;
		}
		 
	}
	*/

}
