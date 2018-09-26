package com.drzk.common;

public class InOutRealTimeBase {

	public static final int MONTH_CAR = 1;     //月租车
	public static final int MONTH_TEMP_CAR = 2; //月临车
	public static final int TEMP_CAR = 3;       //临时车
	public static final int FREE_CAR = 4;       // 免费车
	public static final int STORED_CAR =5;      // 储值车
	public static final int STORED_TEMP_CAR=6;   //储临车

	// 入场方式0正常入场，1校正入场，2手工入场 3,扫码入场 4,脱机记录，5，相机异常记录 6，异常开闸'
	public static final int IN_OUT_NORMAL = 0;
	public static final int IN_OUT_CORRECTING = 1;
	public static final int IN_OUT_HAND = 2;
	public static final int IN_OUT_SCAN = 3;
	public static final int IN_OUT_OFFLINE = 4;
	public static final int IN_OUT_CAMERAANOMALY=5;
	public static final int IN_OUT_ABNORMALOPENING=6;

	/** 黑名单 */
	public static final int CAR_BLACK_LIST = 1;

	/** 特种车 */
	public static final int CAR_SPECIAL = 2;

	/** 手动开闸记录 */
	public static final int HAND_OPEN_GATE = 1;
	
	/** 月租车A */
	public static final int MONTH_CAR_A = 11;
	/** 月租车B */
	public static final int MONTH_CAR_B = 12;
	/** 月租车C */
	public static final int MONTH_CAR_C = 13;
	/** 月租车D */
	public static final int MONTH_CAR_D = 14;
	/** 月租车E */
	public static final int MONTH_CAR_E = 15;
	/** 月租车F */
	public static final int MONTH_CAR_F = 16;
	/** 月租车G */
	public static final int MONTH_CAR_G = 17;
	/** 月租车H */
	public static final int MONTH_CAR_H = 18;

	/** 月临车A */
	public static final int MONTH_TEMP_CAR_A = 21;
	/** 月临车B */
	public static final int MONTH_TEMP_CAR_B = 22;
	/** 月临车C */
	public static final int MONTH_TEMP_CAR_C = 23;
	/** 月临车D */
	public static final int MONTH_TEMP_CAR_D = 24;
	/** 月临车E */
	public static final int MONTH_TEMP_CAR_E = 25;
	/** 月临车F */
	public static final int MONTH_TEMP_CAR_F = 26;
	/** 月临车G */
	public static final int MONTH_TEMP_CAR_G = 27;
	/** 月临车H */
	public static final int MONTH_TEMP_CAR_H = 28;

	/** 临车A */
	public static final int TEMP_CAR_A = 31;
	/** 临车B */
	public static final int TEMP_CAR_B = 32;
	/** 临车C */
	public static final int TEMP_CAR_C = 33;
	/** 临车D */
	public static final int TEMP_CAR_D = 34;
	/** 临车E */
	public static final int TEMP_CAR_E = 35;
	/** 临车F */
	public static final int TEMP_CAR_F = 36;
	/** 临车G */
	public static final int TEMP_CAR_G = 37;
	/** 临车H */
	public static final int TEMP_CAR_H = 38;

	/** 临免车 */
	public static final int TEMP_FREE_CAR_A = 40;
	/** 免费车A */
	public static final int FREE_CAR_A = 41;
	/** 免费车B */
	public static final int FREE_CAR_B = 42;
	/** 免费车C */
	public static final int FREE_CAR_C = 43;
	/** 免费车D */
	public static final int FREE_CAR_D = 44;
	/** 免费车E */
	public static final int FREE_CAR_E = 45;
	/** 免费车F */
	public static final int FREE_CAR_F = 46;
	/** 免费车G */
	public static final int FREE_CAR_G = 47;
	/** 免费车H */
	public static final int FREE_CAR_H = 48;

	/** 储值车A */
	public static final int STORED_CAR_A = 51;
	/** 储值车B */
	public static final int STORED_CAR_B = 52;
	/** 储值车C */
	public static final int STORED_CAR_C = 53;
	/** 储值车D */
	public static final int STORED_CAR_D = 54;
	/** 储值车E */
	public static final int STORED_CAR_E = 55;
	/** 储值车F */
	public static final int STORED_CAR_F = 56;
	/** 储值车G */
	public static final int STORED_CAR_G = 57;
	/** 储值车H */
	public static final int STORED_CAR_H = 58;

	/** 储临车A */
	public static final int STORED_TEMP_CAR_A = 51;
	/** 储临车B */
	public static final int STORED_TEMP_CAR_B = 52;
	/** 储临车C */
	public static final int STORED_TEMP_CAR_C = 53;
	/** 储临车D */
	public static final int STORED_TEMP_CAR_D = 54;
	/** 储临车E */
	public static final int STORED_TEMP_CAR_E = 55;
	/** 储临车F */
	public static final int STORED_TEMP_CAR_F = 56;
	/** 储临车G */
	public static final int STORED_TEMP_CAR_G = 57;
	/** 储临车H */
	public static final int STORED_TEMP_CAR_H = 58;

	/**
	 * 下一步的标记
	 */
	public enum Step {
		START, // 开始
		GETCARTYPE, // 获取车辆类型
		BLACKLIST, // 黑名单
		SPECIALCAR, // 特种车
		FREECAR, // 免费车
		NULL, // 不属于黑名单，特种车，免费车

		FINDINREORD,  //查询入场记录
		SENDSIMILAR, // 发送相似车牌
		NOINREORD,  //没有唯一的入场记录

		ISSMALLBIG, // 获取大小车场
		SMALLPARK,  //小车场
		BIGPARK,   //大车场

		ISFULLIN, FULLALLOWIN, // 满位允许入场
		FULLNOTALLOWIN, // 满位不允许入场

		ISREADSUCCESS, // 是否识别成功
		READFAIL, // 识别失败

		CARDTYPE, // 卡片类型
		ISREGULARCAR, // 判断是否为固定车
		REGULARCAR, // 固定车
		TEMPCAR, // 临时车
		MONTHCAR, // 月租车
		MONTHTEMPCAR, // 月临车
		FERRCAR,   // 免费车
		STORECAR, // 储值车
		STORETEMPCAR, // 储临车
        
		MONTHMOREOUT, //固定车多出
		
		HASGULARPOWER, // 有固定车通道权限
		NOGULARPOWER, // 没有通道权限

		CHECKEFFECT, // 检查有效期
		EXPIRE, // 固定车过期
		UNDUE, // 未到有效期
		TIMENOTALLOW, // 时间段禁止进入
		EFFECTIVE, // 有效期内
		UNEFFECTIVE, // 不在有效期内

		FAMILYTEMP, // 家组临时卡
		FAMILYMONTH, // 家庭组月卡
		FAMILYFULL, // 家庭组满位

		FAMILY, // 家庭组
		NOTFAMILY, // 非家庭组

		CHECKTEMPPOWER, // 检查临时车通道权限
		HASTEMPPOWER, // 临时车通道权限
		NOTEMPPOWER, // 没有临时车通道权限

		PRESETCARTYPE, // 入场临时车赋值

		ISARREARS, // 是否欠费
		ARREARS, // 欠费
		NOPAY,   //无需缴费

		ISNEEDCONFIRM, // 判断开闸是否需要确认

		NEEDCONFIRM, // 开闸需要确认

		ISLOCK, // 是否锁车
		LOCK, // 锁车
		UNLOCK, // 锁车

		BEFORECHARGE,
		
		NOCARNO, //无牌车

		SAVE, // 保存

		VOICE, // 报语音
		OPEN, // 开闸

		END // 结束
	}
}
