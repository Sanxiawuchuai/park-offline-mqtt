package com.drzk.vo;

import java.util.Date;

public class SysLoginUser {
    /**
     * 
     */
    private Integer id;

    /**
     * 登录名/工号
     */
    private String loginName;

    /**
     * 名称
     */
    private String userName;

    /**
     * 密码
     */
    private String pssWord;

    /**
     * 操作卡卡号
     */
    private String operaCarno;

    /**
     * 权限序列(1一卡通/2停车场/3门禁/4消费/5考勤/6巡更/7访客
     */
    private String perSeq;

    /**
     * 菜单权限序列（一卡通300个，子系统50个）
     */
    private String popedomList;

    /**
     * 操作员级别(0操作组1财务组2管理组100系统管理员)
     */
    private Byte rank;

    /**
     * 登录方式（0、密码；1、刷卡登录；2、密码+刷卡）
     */
    private Byte loginType;

    /**
     * 进入方式（1监控界面）
     */
    private Byte loadType;

    /**
     * 是否停用
     */
    private Byte isStop;

    /**
     * 备注
     */
    private String memo;

    /**
     * 用户类型
     */
    private Integer userType;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 创建人
     */
    private String createUserName;

    /**
     * 修改时间
     */
    private Date modifyDate;

    /**
     * 修改人
     */
    private String modifyUserName;

    /**
     * 0未上传1已上传
     */
    private Byte isLoad;
    
    private Integer isLoginBox;

    /**
     * 
     * @return id 
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 登录名/工号
     * @return login_name 登录名/工号
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * 登录名/工号
     * @param loginName 登录名/工号
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    /**
     * 名称
     * @return user_name 名称
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 名称
     * @param userName 名称
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * 密码
     * @return pss_word 密码
     */
    public String getPssWord() {
        return pssWord;
    }

    /**
     * 密码
     * @param pssWord 密码
     */
    public void setPssWord(String pssWord) {
        this.pssWord = pssWord == null ? null : pssWord.trim();
    }

    /**
     * 操作卡卡号
     * @return opera_carNo 操作卡卡号
     */
    public String getOperaCarno() {
        return operaCarno;
    }

    /**
     * 操作卡卡号
     * @param operaCarno 操作卡卡号
     */
    public void setOperaCarno(String operaCarno) {
        this.operaCarno = operaCarno == null ? null : operaCarno.trim();
    }

    /**
     * 权限序列(1一卡通/2停车场/3门禁/4消费/5考勤/6巡更/7访客
     * @return per_seq 权限序列(1一卡通/2停车场/3门禁/4消费/5考勤/6巡更/7访客
     */
    public String getPerSeq() {
        return perSeq;
    }

    /**
     * 权限序列(1一卡通/2停车场/3门禁/4消费/5考勤/6巡更/7访客
     * @param perSeq 权限序列(1一卡通/2停车场/3门禁/4消费/5考勤/6巡更/7访客
     */
    public void setPerSeq(String perSeq) {
        this.perSeq = perSeq == null ? null : perSeq.trim();
    }

    /**
     * 菜单权限序列（一卡通300个，子系统50个）
     * @return Popedom_list 菜单权限序列（一卡通300个，子系统50个）
     */
    public String getPopedomList() {
        return popedomList;
    }

    /**
     * 菜单权限序列（一卡通300个，子系统50个）
     * @param popedomList 菜单权限序列（一卡通300个，子系统50个）
     */
    public void setPopedomList(String popedomList) {
        this.popedomList = popedomList == null ? null : popedomList.trim();
    }

    /**
     * 操作员级别(0操作组1财务组2管理组100系统管理员)
     * @return rank 操作员级别(0操作组1财务组2管理组100系统管理员)
     */
    public Byte getRank() {
        return rank;
    }

    /**
     * 操作员级别(0操作组1财务组2管理组100系统管理员)
     * @param rank 操作员级别(0操作组1财务组2管理组100系统管理员)
     */
    public void setRank(Byte rank) {
        this.rank = rank;
    }

    /**
     * 登录方式（0、密码；1、刷卡登录；2、密码+刷卡）
     * @return login_type 登录方式（0、密码；1、刷卡登录；2、密码+刷卡）
     */
    public Byte getLoginType() {
        return loginType;
    }

    /**
     * 登录方式（0、密码；1、刷卡登录；2、密码+刷卡）
     * @param loginType 登录方式（0、密码；1、刷卡登录；2、密码+刷卡）
     */
    public void setLoginType(Byte loginType) {
        this.loginType = loginType;
    }

    /**
     * 进入方式（1监控界面）
     * @return load_type 进入方式（1监控界面）
     */
    public Byte getLoadType() {
        return loadType;
    }

    /**
     * 进入方式（1监控界面）
     * @param loadType 进入方式（1监控界面）
     */
    public void setLoadType(Byte loadType) {
        this.loadType = loadType;
    }

    /**
     * 是否停用
     * @return is_stop 是否停用
     */
    public Byte getIsStop() {
        return isStop;
    }

    /**
     * 是否停用
     * @param isStop 是否停用
     */
    public void setIsStop(Byte isStop) {
        this.isStop = isStop;
    }

    /**
     * 备注
     * @return memo 备注
     */
    public String getMemo() {
        return memo;
    }

    /**
     * 备注
     * @param memo 备注
     */
    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    /**
     * 用户类型
     * @return user_type 用户类型
     */
    public Integer getUserType() {
        return userType;
    }

    /**
     * 用户类型
     * @param userType 用户类型
     */
    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    /**
     * 创建时间
     * @return create_date 创建时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 创建时间
     * @param createDate 创建时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 创建人
     * @return create_user_name 创建人
     */
    public String getCreateUserName() {
        return createUserName;
    }

    /**
     * 创建人
     * @param createUserName 创建人
     */
    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName == null ? null : createUserName.trim();
    }

    /**
     * 修改时间
     * @return modify_date 修改时间
     */
    public Date getModifyDate() {
        return modifyDate;
    }

    /**
     * 修改时间
     * @param modifyDate 修改时间
     */
    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    /**
     * 修改人
     * @return modify_user_name 修改人
     */
    public String getModifyUserName() {
        return modifyUserName;
    }

    /**
     * 修改人
     * @param modifyUserName 修改人
     */
    public void setModifyUserName(String modifyUserName) {
        this.modifyUserName = modifyUserName == null ? null : modifyUserName.trim();
    }

    /**
     * 0未上传1已上传
     * @return is_load 0未上传1已上传
     */
    public Byte getIsLoad() {
        return isLoad;
    }

    /**
     * 0未上传1已上传
     * @param isLoad 0未上传1已上传
     */
    public void setIsLoad(Byte isLoad) {
        this.isLoad = isLoad;
    }

	/**
	 * @return the isLoginBox
	 */
	public Integer getIsLoginBox() {
		return isLoginBox;
	}

	/**
	 * @param isLoginBox the isLoginBox to set
	 */
	public void setIsLoginBox(Integer isLoginBox) {
		this.isLoginBox = isLoginBox;
	}
    
    
    
}