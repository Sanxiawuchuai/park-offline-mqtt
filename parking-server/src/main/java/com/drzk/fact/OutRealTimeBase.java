package com.drzk.fact;

import com.drzk.charge.vo.PaymentVo;
import com.drzk.vo.ParkCarIn;
import com.drzk.vo.ParkCarOut;

import java.io.Serializable;
import java.util.List;

/**
 *  出场传入规则引擎的对象 <br>
 * Date: 2018年5月17日 下午2:26:58 <br>
 * 
 * @author chenlong
 * @version
 * @since JDK 1.8
 * @see
 */
public class OutRealTimeBase extends AbsRealTimeBase implements Serializable {
	private static final long serialVersionUID = 5119002557717875958L;

	private ParkCarOut out; //将要保存的出场实体
	private List<ParkCarIn> similarCarin; //相似车牌的入场记录集合
	private ParkCarIn inRecord; //用来存储对应的入场记录
	private PaymentVo payMentVo = new PaymentVo(); //收费对象
	
	
	
	/**
	 * @return the similarCarin
	 */
	public List<ParkCarIn> getSimilarCarin() {
		return similarCarin;
	}

	/**
	 * @param similarCarin the similarCarin to set
	 */
	public void setSimilarCarin(List<ParkCarIn> similarCarin) {
		this.similarCarin = similarCarin;
	}

	/**
	* out.
	*
	* @return the out
	* @since JDK 1.8
	*/
	public ParkCarOut getOut() {
		return out;
	}
	
	/**
	* out.
	*
	* @param out the out to set
	* @since JDK 1.8
	*/
	public void setOut(ParkCarOut out) {
		this.out = out;
	}
	
	/**
	* inRecord.
	*
	* @return the inRecord
	* @since JDK 1.8
	*/
	public ParkCarIn getInRecord() {
		return inRecord;
	}
	
	/**
	* inRecord.
	*
	* @param inRecord the inRecord to set
	* @since JDK 1.8
	*/
	public void setInRecord(ParkCarIn inRecord) {
		this.inRecord = inRecord;
	}
	
	/**
	* payMentVo.
	*
	* @return the payMentVo
	* @since JDK 1.8
	*/
	public PaymentVo getPayMentVo() {
		return payMentVo;
	}
	
	/**
	* payMentVo.
	*
	* @param payMentVo the payMentVo to set
	* @since JDK 1.8
	*/
	public void setPayMentVo(PaymentVo payMentVo) {
		this.payMentVo = payMentVo;
	}
	
	
	
}
