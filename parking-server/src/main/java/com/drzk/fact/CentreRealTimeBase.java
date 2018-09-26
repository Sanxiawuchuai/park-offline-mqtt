package com.drzk.fact;

import java.io.Serializable;

import com.drzk.charge.vo.PaymentVo;
import com.drzk.vo.ParkCarIn;
import com.drzk.vo.ParkCentralCharge;
/**
 *  中央收费传入规则引擎的对象 <br>
 * Date: 2018年09月12日 下午2:26:58 <br>
 * 
 * @author wangchengxi
 * @version
 * @since JDK 1.8
 * @see
 */
public class CentreRealTimeBase extends AbsRealTimeBase implements Serializable{
	private static final long serialVersionUID = -7429192659978166868L;
	
	private ParkCarIn inRecord; //用来存储对应的入场记录
	private PaymentVo payMentVo = new PaymentVo(); //收费对象
	private ParkCentralCharge centreRecord;  //中央记录
	
	public ParkCarIn getInRecord() {
		return inRecord;
	}
	public ParkCentralCharge getCentreRecord() {
		return centreRecord;
	}
	public void setCentreRecord(ParkCentralCharge centreRecord) {
		this.centreRecord = centreRecord;
	}
	public void setInRecord(ParkCarIn inRecord) {
		this.inRecord = inRecord;
	}
	public PaymentVo getPayMentVo() {
		return payMentVo;
	}
	public void setPayMentVo(PaymentVo payMentVo) {
		this.payMentVo = payMentVo;
	}
}
