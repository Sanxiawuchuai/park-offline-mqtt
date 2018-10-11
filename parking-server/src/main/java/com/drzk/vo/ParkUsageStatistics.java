package com.drzk.vo;

import java.io.Serializable;
import java.util.Date;

public class ParkUsageStatistics implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1285592700800477025L;

	/**
     * 自增长id
     */
    private Integer id;

    /**
     * 日期(yyyy-MM-dd)
     */
    private String sDay;

    /**
     * 卡类型
     */
    private Byte cardType;

    /**
     * 入场时间
     */
    private Date inTime;

    /**
     * 出场时间
     */
    private Date outTime;

    /**
     * 入场小时
     */
    private Byte inHour;

    /**
     * 出场小时
     */
    private Byte outHour;

    /**
     * 第0小时
     */
    private Float t0;

    /**
     * 第1小时
     */
    private Float t1;

    /**
     * 第2小时
     */
    private Float t2;

    /**
     * 第3小时
     */
    private Float t3;

    /**
     * 第4小时
     */
    private Float t4;

    /**
     * 第5小时
     */
    private Float t5;

    /**
     * 第6小时
     */
    private Float t6;

    /**
     * 第7小时
     */
    private Float t7;

    /**
     * 第8小时
     */
    private Float t8;

    /**
     * 第9小时
     */
    private Float t9;

    /**
     * 第10小时
     */
    private Float t10;

    /**
     * 第11小时
     */
    private Float t11;

    /**
     * 第12小时
     */
    private Float t12;

    /**
     * 第13小时
     */
    private Float t13;

    /**
     * 第14小时
     */
    private Float t14;

    /**
     * 第15小时
     */
    private Float t15;

    /**
     * 第16小时
     */
    private Float t16;

    /**
     * 第17小时
     */
    private Float t17;

    /**
     * 第18小时
     */
    private Float t18;

    /**
     * 第19小时
     */
    private Float t19;

    /**
     * 第20小时
     */
    private Float t20;

    /**
     * 第21小时
     */
    private Float t21;

    /**
     * 第22小时
     */
    private Float t22;

    /**
     * 第23小时
     */
    private Float t23;

    /**
     * 自增长id
     * @return id 自增长id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 自增长id
     * @param id 自增长id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 日期(yyyy-MM-dd)
     * @return s_day 日期(yyyy-MM-dd)
     */
    public String getsDay() {
        return sDay;
    }

    /**
     * 日期(yyyy-MM-dd)
     * @param sDay 日期(yyyy-MM-dd)
     */
    public void setsDay(String sDay) {
        this.sDay = sDay == null ? null : sDay.trim();
    }

    /**
     * 卡类型
     * @return card_type 卡类型
     */
    public Byte getCardType() {
        return cardType;
    }

    /**
     * 卡类型
     * @param cardType 卡类型
     */
    public void setCardType(Byte cardType) {
        this.cardType = cardType;
    }

    /**
     * 入场时间
     * @return in_time 入场时间
     */
    public Date getInTime() {
        return inTime;
    }

    /**
     * 入场时间
     * @param inTime 入场时间
     */
    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }

    /**
     * 出场时间
     * @return out_time 出场时间
     */
    public Date getOutTime() {
        return outTime;
    }

    /**
     * 出场时间
     * @param outTime 出场时间
     */
    public void setOutTime(Date outTime) {
        this.outTime = outTime;
    }

    /**
     * 入场小时
     * @return in_hour 入场小时
     */
    public Byte getInHour() {
        return inHour;
    }

    /**
     * 入场小时
     * @param inHour 入场小时
     */
    public void setInHour(Byte inHour) {
        this.inHour = inHour;
    }

    /**
     * 出场小时
     * @return out_hour 出场小时
     */
    public Byte getOutHour() {
        return outHour;
    }

    /**
     * 出场小时
     * @param outHour 出场小时
     */
    public void setOutHour(Byte outHour) {
        this.outHour = outHour;
    }

    /**
     * 第0小时
     * @return t_0 第0小时
     */
    public Float getT0() {
        return t0;
    }

    /**
     * 第0小时
     * @param t0 第0小时
     */
    public void setT0(Float t0) {
        this.t0 = t0;
    }

    /**
     * 第1小时
     * @return t_1 第1小时
     */
    public Float getT1() {
        return t1;
    }

    /**
     * 第1小时
     * @param t1 第1小时
     */
    public void setT1(Float t1) {
        this.t1 = t1;
    }

    /**
     * 第2小时
     * @return t_2 第2小时
     */
    public Float getT2() {
        return t2;
    }

    /**
     * 第2小时
     * @param t2 第2小时
     */
    public void setT2(Float t2) {
        this.t2 = t2;
    }

    /**
     * 第3小时
     * @return t_3 第3小时
     */
    public Float getT3() {
        return t3;
    }

    /**
     * 第3小时
     * @param t3 第3小时
     */
    public void setT3(Float t3) {
        this.t3 = t3;
    }

    /**
     * 第4小时
     * @return t_4 第4小时
     */
    public Float getT4() {
        return t4;
    }

    /**
     * 第4小时
     * @param t4 第4小时
     */
    public void setT4(Float t4) {
        this.t4 = t4;
    }

    /**
     * 第5小时
     * @return t_5 第5小时
     */
    public Float getT5() {
        return t5;
    }

    /**
     * 第5小时
     * @param t5 第5小时
     */
    public void setT5(Float t5) {
        this.t5 = t5;
    }

    /**
     * 第6小时
     * @return t_6 第6小时
     */
    public Float getT6() {
        return t6;
    }

    /**
     * 第6小时
     * @param t6 第6小时
     */
    public void setT6(Float t6) {
        this.t6 = t6;
    }

    /**
     * 第7小时
     * @return t_7 第7小时
     */
    public Float getT7() {
        return t7;
    }

    /**
     * 第7小时
     * @param t7 第7小时
     */
    public void setT7(Float t7) {
        this.t7 = t7;
    }

    /**
     * 第8小时
     * @return t_8 第8小时
     */
    public Float getT8() {
        return t8;
    }

    /**
     * 第8小时
     * @param t8 第8小时
     */
    public void setT8(Float t8) {
        this.t8 = t8;
    }

    /**
     * 第9小时
     * @return t_9 第9小时
     */
    public Float getT9() {
        return t9;
    }

    /**
     * 第9小时
     * @param t9 第9小时
     */
    public void setT9(Float t9) {
        this.t9 = t9;
    }

    /**
     * 第10小时
     * @return t_10 第10小时
     */
    public Float getT10() {
        return t10;
    }

    /**
     * 第10小时
     * @param t10 第10小时
     */
    public void setT10(Float t10) {
        this.t10 = t10;
    }

    /**
     * 第11小时
     * @return t_11 第11小时
     */
    public Float getT11() {
        return t11;
    }

    /**
     * 第11小时
     * @param t11 第11小时
     */
    public void setT11(Float t11) {
        this.t11 = t11;
    }

    /**
     * 第12小时
     * @return t_12 第12小时
     */
    public Float getT12() {
        return t12;
    }

    /**
     * 第12小时
     * @param t12 第12小时
     */
    public void setT12(Float t12) {
        this.t12 = t12;
    }

    /**
     * 第13小时
     * @return t_13 第13小时
     */
    public Float getT13() {
        return t13;
    }

    /**
     * 第13小时
     * @param t13 第13小时
     */
    public void setT13(Float t13) {
        this.t13 = t13;
    }

    /**
     * 第14小时
     * @return t_14 第14小时
     */
    public Float getT14() {
        return t14;
    }

    /**
     * 第14小时
     * @param t14 第14小时
     */
    public void setT14(Float t14) {
        this.t14 = t14;
    }

    /**
     * 第15小时
     * @return t_15 第15小时
     */
    public Float getT15() {
        return t15;
    }

    /**
     * 第15小时
     * @param t15 第15小时
     */
    public void setT15(Float t15) {
        this.t15 = t15;
    }

    /**
     * 第16小时
     * @return t_16 第16小时
     */
    public Float getT16() {
        return t16;
    }

    /**
     * 第16小时
     * @param t16 第16小时
     */
    public void setT16(Float t16) {
        this.t16 = t16;
    }

    /**
     * 第17小时
     * @return t_17 第17小时
     */
    public Float getT17() {
        return t17;
    }

    /**
     * 第17小时
     * @param t17 第17小时
     */
    public void setT17(Float t17) {
        this.t17 = t17;
    }

    /**
     * 第18小时
     * @return t_18 第18小时
     */
    public Float getT18() {
        return t18;
    }

    /**
     * 第18小时
     * @param t18 第18小时
     */
    public void setT18(Float t18) {
        this.t18 = t18;
    }

    /**
     * 第19小时
     * @return t_19 第19小时
     */
    public Float getT19() {
        return t19;
    }

    /**
     * 第19小时
     * @param t19 第19小时
     */
    public void setT19(Float t19) {
        this.t19 = t19;
    }

    /**
     * 第20小时
     * @return t_20 第20小时
     */
    public Float getT20() {
        return t20;
    }

    /**
     * 第20小时
     * @param t20 第20小时
     */
    public void setT20(Float t20) {
        this.t20 = t20;
    }

    /**
     * 第21小时
     * @return t_21 第21小时
     */
    public Float getT21() {
        return t21;
    }

    /**
     * 第21小时
     * @param t21 第21小时
     */
    public void setT21(Float t21) {
        this.t21 = t21;
    }

    /**
     * 第22小时
     * @return t_22 第22小时
     */
    public Float getT22() {
        return t22;
    }

    /**
     * 第22小时
     * @param t22 第22小时
     */
    public void setT22(Float t22) {
        this.t22 = t22;
    }

    /**
     * 第23小时
     * @return t_23 第23小时
     */
    public Float getT23() {
        return t23;
    }

    /**
     * 第23小时
     * @param t23 第23小时
     */
    public void setT23(Float t23) {
        this.t23 = t23;
    }
}