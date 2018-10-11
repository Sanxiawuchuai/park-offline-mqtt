package com.drzk.vo;

import java.io.Serializable;
import java.util.Date;

public class ParkTrafficStatistics implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -2697030035763682921L;

	/**
     * 自增长id
     */
    private Integer id;

    /**
     * 日期
     */
    private Date sDate;

    /**
     * 小时
     */
    private Byte sTime;

    /**
     * 手动开闸
     */
    private Integer handGate;

    /**
     * 电脑开闸
     */
    private Integer computerGate;

    /**
     * 月租卡A入
     */
    private Integer monthCardInA;

    /**
     * 月租卡A出
     */
    private Integer monthCardOutA;

    /**
     * 月租卡B入
     */
    private Integer monthCardInB;

    /**
     * 月租卡B出
     */
    private Integer monthCardOutB;

    /**
     * 月租卡C入
     */
    private Integer monthCardInC;

    /**
     * 月租卡C出
     */
    private Integer monthCardOutC;

    /**
     * 月租卡D入
     */
    private Integer monthCardInD;

    /**
     * 月租卡D出
     */
    private Integer monthCardOutD;

    /**
     * 月临卡A入
     */
    private Integer monthTmpInA;

    /**
     * 月临卡A出
     */
    private Integer monthTmpOutA;

    /**
     * 月临卡B入
     */
    private Integer monthTmpInB;

    /**
     * 月临卡B出
     */
    private Integer monthTmpOutB;

    /**
     * 月临卡C入
     */
    private Integer monthTmpInC;

    /**
     * 月临卡C出
     */
    private Integer monthTmpOutC;

    /**
     * 月临卡D入
     */
    private Integer monthTmpInD;

    /**
     * 月临卡D出
     */
    private Integer monthTmpOutD;

    /**
     * 临时卡A入
     */
    private Integer tempCardInA;

    /**
     * 临时卡A出
     */
    private Integer tempCardOutA;

    /**
     * 临时卡B入
     */
    private Integer tempCardInB;

    /**
     * 临时卡B出
     */
    private Integer tempCardOutB;

    /**
     * 临时卡C入
     */
    private Integer tempCardInC;

    /**
     * 临时卡C出
     */
    private Integer tempCardOutC;

    /**
     * 临时卡D入
     */
    private Integer tempCardInD;

    /**
     * 临时卡D出
     */
    private Integer tempCardOutD;

    /**
     * 临免卡入
     */
    private Integer tempFreeInCard;

    /**
     * 临免卡出
     */
    private Integer tempFreeOutCard;

    /**
     * 免费卡A入
     */
    private Integer freeCardInA;

    /**
     * 免费卡A出
     */
    private Integer freeCardOutA;

    /**
     * 免费卡B入
     */
    private Integer freeCardInB;

    /**
     * 免费卡B出
     */
    private Integer freeCardOutB;

    /**
     * 储值卡A入
     */
    private Integer storedCardInA;

    /**
     * 储值卡A出
     */
    private Integer storedCardOutA;

    /**
     * 储值卡B入
     */
    private Integer storedCardInB;

    /**
     * 储值卡B出
     */
    private Integer storedCardOutB;

    /**
     * 储值卡C入
     */
    private Integer storedCardInC;

    /**
     * 储值卡C出
     */
    private Integer storedCardOutC;

    /**
     * 储值卡D入
     */
    private Integer storedCardInD;

    /**
     * 储值卡D出
     */
    private Integer storedCardOutD;

    /**
     * 储临卡A入
     */
    private Integer storedTempInA;

    /**
     * 储临卡A出
     */
    private Integer storedTempOutA;

    /**
     * 储临卡B入
     */
    private Integer storedTempInB;

    /**
     * 储临卡B出
     */
    private Integer storedTempOutB;

    /**
     * 储临卡C入
     */
    private Integer storedTempInC;

    /**
     * 储临卡C出
     */
    private Integer storedTempOutC;

    /**
     * 储临卡D入
     */
    private Integer storedTempInD;

    /**
     * 储临卡D出
     */
    private Integer storedTempOutD;

    /**
     * 入场总数
     */
    private Integer allInCount;

    /**
     * 出场总数
     */
    private Integer allOutCount;

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
     * 日期
     * @return s_date 日期
     */
    public Date getsDate() {
        return sDate;
    }

    /**
     * 日期
     * @param sDate 日期
     */
    public void setsDate(Date sDate) {
        this.sDate = sDate;
    }

    /**
     * 小时
     * @return s_time 小时
     */
    public Byte getsTime() {
        return sTime;
    }

    /**
     * 小时
     * @param sTime 小时
     */
    public void setsTime(Byte sTime) {
        this.sTime = sTime;
    }

    /**
     * 手动开闸
     * @return hand_gate 手动开闸
     */
    public Integer getHandGate() {
        return handGate;
    }

    /**
     * 手动开闸
     * @param handGate 手动开闸
     */
    public void setHandGate(Integer handGate) {
        this.handGate = handGate;
    }

    /**
     * 电脑开闸
     * @return computer_gate 电脑开闸
     */
    public Integer getComputerGate() {
        return computerGate;
    }

    /**
     * 电脑开闸
     * @param computerGate 电脑开闸
     */
    public void setComputerGate(Integer computerGate) {
        this.computerGate = computerGate;
    }

    /**
     * 月租卡A入
     * @return month_card_in_a 月租卡A入
     */
    public Integer getMonthCardInA() {
        return monthCardInA;
    }

    /**
     * 月租卡A入
     * @param monthCardInA 月租卡A入
     */
    public void setMonthCardInA(Integer monthCardInA) {
        this.monthCardInA = monthCardInA;
    }

    /**
     * 月租卡A出
     * @return month_card_out_a 月租卡A出
     */
    public Integer getMonthCardOutA() {
        return monthCardOutA;
    }

    /**
     * 月租卡A出
     * @param monthCardOutA 月租卡A出
     */
    public void setMonthCardOutA(Integer monthCardOutA) {
        this.monthCardOutA = monthCardOutA;
    }

    /**
     * 月租卡B入
     * @return month_card_in_b 月租卡B入
     */
    public Integer getMonthCardInB() {
        return monthCardInB;
    }

    /**
     * 月租卡B入
     * @param monthCardInB 月租卡B入
     */
    public void setMonthCardInB(Integer monthCardInB) {
        this.monthCardInB = monthCardInB;
    }

    /**
     * 月租卡B出
     * @return month_card_out_b 月租卡B出
     */
    public Integer getMonthCardOutB() {
        return monthCardOutB;
    }

    /**
     * 月租卡B出
     * @param monthCardOutB 月租卡B出
     */
    public void setMonthCardOutB(Integer monthCardOutB) {
        this.monthCardOutB = monthCardOutB;
    }

    /**
     * 月租卡C入
     * @return month_card_in_c 月租卡C入
     */
    public Integer getMonthCardInC() {
        return monthCardInC;
    }

    /**
     * 月租卡C入
     * @param monthCardInC 月租卡C入
     */
    public void setMonthCardInC(Integer monthCardInC) {
        this.monthCardInC = monthCardInC;
    }

    /**
     * 月租卡C出
     * @return month_card_out_c 月租卡C出
     */
    public Integer getMonthCardOutC() {
        return monthCardOutC;
    }

    /**
     * 月租卡C出
     * @param monthCardOutC 月租卡C出
     */
    public void setMonthCardOutC(Integer monthCardOutC) {
        this.monthCardOutC = monthCardOutC;
    }

    /**
     * 月租卡D入
     * @return month_card_in_d 月租卡D入
     */
    public Integer getMonthCardInD() {
        return monthCardInD;
    }

    /**
     * 月租卡D入
     * @param monthCardInD 月租卡D入
     */
    public void setMonthCardInD(Integer monthCardInD) {
        this.monthCardInD = monthCardInD;
    }

    /**
     * 月租卡D出
     * @return month_card_out_d 月租卡D出
     */
    public Integer getMonthCardOutD() {
        return monthCardOutD;
    }

    /**
     * 月租卡D出
     * @param monthCardOutD 月租卡D出
     */
    public void setMonthCardOutD(Integer monthCardOutD) {
        this.monthCardOutD = monthCardOutD;
    }

    /**
     * 月临卡A入
     * @return month_tmp_in_a 月临卡A入
     */
    public Integer getMonthTmpInA() {
        return monthTmpInA;
    }

    /**
     * 月临卡A入
     * @param monthTmpInA 月临卡A入
     */
    public void setMonthTmpInA(Integer monthTmpInA) {
        this.monthTmpInA = monthTmpInA;
    }

    /**
     * 月临卡A出
     * @return month_tmp_out_a 月临卡A出
     */
    public Integer getMonthTmpOutA() {
        return monthTmpOutA;
    }

    /**
     * 月临卡A出
     * @param monthTmpOutA 月临卡A出
     */
    public void setMonthTmpOutA(Integer monthTmpOutA) {
        this.monthTmpOutA = monthTmpOutA;
    }

    /**
     * 月临卡B入
     * @return month_tmp_in_b 月临卡B入
     */
    public Integer getMonthTmpInB() {
        return monthTmpInB;
    }

    /**
     * 月临卡B入
     * @param monthTmpInB 月临卡B入
     */
    public void setMonthTmpInB(Integer monthTmpInB) {
        this.monthTmpInB = monthTmpInB;
    }

    /**
     * 月临卡B出
     * @return month_tmp_out_b 月临卡B出
     */
    public Integer getMonthTmpOutB() {
        return monthTmpOutB;
    }

    /**
     * 月临卡B出
     * @param monthTmpOutB 月临卡B出
     */
    public void setMonthTmpOutB(Integer monthTmpOutB) {
        this.monthTmpOutB = monthTmpOutB;
    }

    /**
     * 月临卡C入
     * @return month_tmp_in_c 月临卡C入
     */
    public Integer getMonthTmpInC() {
        return monthTmpInC;
    }

    /**
     * 月临卡C入
     * @param monthTmpInC 月临卡C入
     */
    public void setMonthTmpInC(Integer monthTmpInC) {
        this.monthTmpInC = monthTmpInC;
    }

    /**
     * 月临卡C出
     * @return month_tmp_out_c 月临卡C出
     */
    public Integer getMonthTmpOutC() {
        return monthTmpOutC;
    }

    /**
     * 月临卡C出
     * @param monthTmpOutC 月临卡C出
     */
    public void setMonthTmpOutC(Integer monthTmpOutC) {
        this.monthTmpOutC = monthTmpOutC;
    }

    /**
     * 月临卡D入
     * @return month_tmp_in_d 月临卡D入
     */
    public Integer getMonthTmpInD() {
        return monthTmpInD;
    }

    /**
     * 月临卡D入
     * @param monthTmpInD 月临卡D入
     */
    public void setMonthTmpInD(Integer monthTmpInD) {
        this.monthTmpInD = monthTmpInD;
    }

    /**
     * 月临卡D出
     * @return month_tmp_out_d 月临卡D出
     */
    public Integer getMonthTmpOutD() {
        return monthTmpOutD;
    }

    /**
     * 月临卡D出
     * @param monthTmpOutD 月临卡D出
     */
    public void setMonthTmpOutD(Integer monthTmpOutD) {
        this.monthTmpOutD = monthTmpOutD;
    }

    /**
     * 临时卡A入
     * @return temp_card_in_a 临时卡A入
     */
    public Integer getTempCardInA() {
        return tempCardInA;
    }

    /**
     * 临时卡A入
     * @param tempCardInA 临时卡A入
     */
    public void setTempCardInA(Integer tempCardInA) {
        this.tempCardInA = tempCardInA;
    }

    /**
     * 临时卡A出
     * @return temp_card_out_a 临时卡A出
     */
    public Integer getTempCardOutA() {
        return tempCardOutA;
    }

    /**
     * 临时卡A出
     * @param tempCardOutA 临时卡A出
     */
    public void setTempCardOutA(Integer tempCardOutA) {
        this.tempCardOutA = tempCardOutA;
    }

    /**
     * 临时卡B入
     * @return temp_card_in_b 临时卡B入
     */
    public Integer getTempCardInB() {
        return tempCardInB;
    }

    /**
     * 临时卡B入
     * @param tempCardInB 临时卡B入
     */
    public void setTempCardInB(Integer tempCardInB) {
        this.tempCardInB = tempCardInB;
    }

    /**
     * 临时卡B出
     * @return temp_card_out_b 临时卡B出
     */
    public Integer getTempCardOutB() {
        return tempCardOutB;
    }

    /**
     * 临时卡B出
     * @param tempCardOutB 临时卡B出
     */
    public void setTempCardOutB(Integer tempCardOutB) {
        this.tempCardOutB = tempCardOutB;
    }

    /**
     * 临时卡C入
     * @return temp_card_in_c 临时卡C入
     */
    public Integer getTempCardInC() {
        return tempCardInC;
    }

    /**
     * 临时卡C入
     * @param tempCardInC 临时卡C入
     */
    public void setTempCardInC(Integer tempCardInC) {
        this.tempCardInC = tempCardInC;
    }

    /**
     * 临时卡C出
     * @return temp_card_out_c 临时卡C出
     */
    public Integer getTempCardOutC() {
        return tempCardOutC;
    }

    /**
     * 临时卡C出
     * @param tempCardOutC 临时卡C出
     */
    public void setTempCardOutC(Integer tempCardOutC) {
        this.tempCardOutC = tempCardOutC;
    }

    /**
     * 临时卡D入
     * @return temp_card_in_d 临时卡D入
     */
    public Integer getTempCardInD() {
        return tempCardInD;
    }

    /**
     * 临时卡D入
     * @param tempCardInD 临时卡D入
     */
    public void setTempCardInD(Integer tempCardInD) {
        this.tempCardInD = tempCardInD;
    }

    /**
     * 临时卡D出
     * @return temp_card_out_d 临时卡D出
     */
    public Integer getTempCardOutD() {
        return tempCardOutD;
    }

    /**
     * 临时卡D出
     * @param tempCardOutD 临时卡D出
     */
    public void setTempCardOutD(Integer tempCardOutD) {
        this.tempCardOutD = tempCardOutD;
    }

    /**
     * 临免卡入
     * @return temp_free_in_card 临免卡入
     */
    public Integer getTempFreeInCard() {
        return tempFreeInCard;
    }

    /**
     * 临免卡入
     * @param tempFreeInCard 临免卡入
     */
    public void setTempFreeInCard(Integer tempFreeInCard) {
        this.tempFreeInCard = tempFreeInCard;
    }

    /**
     * 临免卡出
     * @return temp_free_out_card 临免卡出
     */
    public Integer getTempFreeOutCard() {
        return tempFreeOutCard;
    }

    /**
     * 临免卡出
     * @param tempFreeOutCard 临免卡出
     */
    public void setTempFreeOutCard(Integer tempFreeOutCard) {
        this.tempFreeOutCard = tempFreeOutCard;
    }

    /**
     * 免费卡A入
     * @return free_card_in_a 免费卡A入
     */
    public Integer getFreeCardInA() {
        return freeCardInA;
    }

    /**
     * 免费卡A入
     * @param freeCardInA 免费卡A入
     */
    public void setFreeCardInA(Integer freeCardInA) {
        this.freeCardInA = freeCardInA;
    }

    /**
     * 免费卡A出
     * @return free_card_out_a 免费卡A出
     */
    public Integer getFreeCardOutA() {
        return freeCardOutA;
    }

    /**
     * 免费卡A出
     * @param freeCardOutA 免费卡A出
     */
    public void setFreeCardOutA(Integer freeCardOutA) {
        this.freeCardOutA = freeCardOutA;
    }

    /**
     * 免费卡B入
     * @return free_card_in_b 免费卡B入
     */
    public Integer getFreeCardInB() {
        return freeCardInB;
    }

    /**
     * 免费卡B入
     * @param freeCardInB 免费卡B入
     */
    public void setFreeCardInB(Integer freeCardInB) {
        this.freeCardInB = freeCardInB;
    }

    /**
     * 免费卡B出
     * @return free_card_out_b 免费卡B出
     */
    public Integer getFreeCardOutB() {
        return freeCardOutB;
    }

    /**
     * 免费卡B出
     * @param freeCardOutB 免费卡B出
     */
    public void setFreeCardOutB(Integer freeCardOutB) {
        this.freeCardOutB = freeCardOutB;
    }

    /**
     * 储值卡A入
     * @return stored_card_in_a 储值卡A入
     */
    public Integer getStoredCardInA() {
        return storedCardInA;
    }

    /**
     * 储值卡A入
     * @param storedCardInA 储值卡A入
     */
    public void setStoredCardInA(Integer storedCardInA) {
        this.storedCardInA = storedCardInA;
    }

    /**
     * 储值卡A出
     * @return stored_card_out_a 储值卡A出
     */
    public Integer getStoredCardOutA() {
        return storedCardOutA;
    }

    /**
     * 储值卡A出
     * @param storedCardOutA 储值卡A出
     */
    public void setStoredCardOutA(Integer storedCardOutA) {
        this.storedCardOutA = storedCardOutA;
    }

    /**
     * 储值卡B入
     * @return stored_card_in_b 储值卡B入
     */
    public Integer getStoredCardInB() {
        return storedCardInB;
    }

    /**
     * 储值卡B入
     * @param storedCardInB 储值卡B入
     */
    public void setStoredCardInB(Integer storedCardInB) {
        this.storedCardInB = storedCardInB;
    }

    /**
     * 储值卡B出
     * @return stored_card_out_b 储值卡B出
     */
    public Integer getStoredCardOutB() {
        return storedCardOutB;
    }

    /**
     * 储值卡B出
     * @param storedCardOutB 储值卡B出
     */
    public void setStoredCardOutB(Integer storedCardOutB) {
        this.storedCardOutB = storedCardOutB;
    }

    /**
     * 储值卡C入
     * @return stored_card_in_c 储值卡C入
     */
    public Integer getStoredCardInC() {
        return storedCardInC;
    }

    /**
     * 储值卡C入
     * @param storedCardInC 储值卡C入
     */
    public void setStoredCardInC(Integer storedCardInC) {
        this.storedCardInC = storedCardInC;
    }

    /**
     * 储值卡C出
     * @return stored_card_out_c 储值卡C出
     */
    public Integer getStoredCardOutC() {
        return storedCardOutC;
    }

    /**
     * 储值卡C出
     * @param storedCardOutC 储值卡C出
     */
    public void setStoredCardOutC(Integer storedCardOutC) {
        this.storedCardOutC = storedCardOutC;
    }

    /**
     * 储值卡D入
     * @return stored_card_in_d 储值卡D入
     */
    public Integer getStoredCardInD() {
        return storedCardInD;
    }

    /**
     * 储值卡D入
     * @param storedCardInD 储值卡D入
     */
    public void setStoredCardInD(Integer storedCardInD) {
        this.storedCardInD = storedCardInD;
    }

    /**
     * 储值卡D出
     * @return stored_card_out_d 储值卡D出
     */
    public Integer getStoredCardOutD() {
        return storedCardOutD;
    }

    /**
     * 储值卡D出
     * @param storedCardOutD 储值卡D出
     */
    public void setStoredCardOutD(Integer storedCardOutD) {
        this.storedCardOutD = storedCardOutD;
    }

    /**
     * 储临卡A入
     * @return stored_temp_in_a 储临卡A入
     */
    public Integer getStoredTempInA() {
        return storedTempInA;
    }

    /**
     * 储临卡A入
     * @param storedTempInA 储临卡A入
     */
    public void setStoredTempInA(Integer storedTempInA) {
        this.storedTempInA = storedTempInA;
    }

    /**
     * 储临卡A出
     * @return stored_temp_out_a 储临卡A出
     */
    public Integer getStoredTempOutA() {
        return storedTempOutA;
    }

    /**
     * 储临卡A出
     * @param storedTempOutA 储临卡A出
     */
    public void setStoredTempOutA(Integer storedTempOutA) {
        this.storedTempOutA = storedTempOutA;
    }

    /**
     * 储临卡B入
     * @return stored_temp_in_b 储临卡B入
     */
    public Integer getStoredTempInB() {
        return storedTempInB;
    }

    /**
     * 储临卡B入
     * @param storedTempInB 储临卡B入
     */
    public void setStoredTempInB(Integer storedTempInB) {
        this.storedTempInB = storedTempInB;
    }

    /**
     * 储临卡B出
     * @return stored_temp_out_b 储临卡B出
     */
    public Integer getStoredTempOutB() {
        return storedTempOutB;
    }

    /**
     * 储临卡B出
     * @param storedTempOutB 储临卡B出
     */
    public void setStoredTempOutB(Integer storedTempOutB) {
        this.storedTempOutB = storedTempOutB;
    }

    /**
     * 储临卡C入
     * @return stored_temp_in_c 储临卡C入
     */
    public Integer getStoredTempInC() {
        return storedTempInC;
    }

    /**
     * 储临卡C入
     * @param storedTempInC 储临卡C入
     */
    public void setStoredTempInC(Integer storedTempInC) {
        this.storedTempInC = storedTempInC;
    }

    /**
     * 储临卡C出
     * @return stored_temp_out_c 储临卡C出
     */
    public Integer getStoredTempOutC() {
        return storedTempOutC;
    }

    /**
     * 储临卡C出
     * @param storedTempOutC 储临卡C出
     */
    public void setStoredTempOutC(Integer storedTempOutC) {
        this.storedTempOutC = storedTempOutC;
    }

    /**
     * 储临卡D入
     * @return stored_temp_in_d 储临卡D入
     */
    public Integer getStoredTempInD() {
        return storedTempInD;
    }

    /**
     * 储临卡D入
     * @param storedTempInD 储临卡D入
     */
    public void setStoredTempInD(Integer storedTempInD) {
        this.storedTempInD = storedTempInD;
    }

    /**
     * 储临卡D出
     * @return stored_temp_out_d 储临卡D出
     */
    public Integer getStoredTempOutD() {
        return storedTempOutD;
    }

    /**
     * 储临卡D出
     * @param storedTempOutD 储临卡D出
     */
    public void setStoredTempOutD(Integer storedTempOutD) {
        this.storedTempOutD = storedTempOutD;
    }

    /**
     * 入场总数
     * @return all_in_count 入场总数
     */
    public Integer getAllInCount() {
        return allInCount;
    }

    /**
     * 入场总数
     * @param allInCount 入场总数
     */
    public void setAllInCount(Integer allInCount) {
        this.allInCount = allInCount;
    }

    /**
     * 出场总数
     * @return all_out_count 出场总数
     */
    public Integer getAllOutCount() {
        return allOutCount;
    }

    /**
     * 出场总数
     * @param allOutCount 出场总数
     */
    public void setAllOutCount(Integer allOutCount) {
        this.allOutCount = allOutCount;
    }
}