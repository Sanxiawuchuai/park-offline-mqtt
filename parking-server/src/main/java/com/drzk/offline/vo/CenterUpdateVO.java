package com.drzk.offline.vo;

/**
 * ClassName:CenterUpdateVO <br>
 * Date: 2018年8月6日 下午4:35:35 <br>
 * 
 * @author chenlong
 * @version
 * @since JDK 1.8
 * @see
 */
public class CenterUpdateVO extends SuperBody{
	private String type;
	private String uId;
	private String id;
	private String execType;
	
	
	public String getuId()
	{
		return uId;
	}


	public void setuId(String uId)
	{
		this.uId = uId;
	}


	/** 表主键ID  */
	public String getId()
	{
		return id;
	}

	/** 表主键ID  */
	public void setId(String id)
	{
		this.id = id;
	}

	/** 1.新增 2.修改 3.删除 */
	public String getExecType()
	{
		return execType;
	}

	/** 1.新增 2.修改 3.删除 */
	public void setExecType(String execType)
	{
		this.execType = execType;
	}


	/**
	* type.
	*
	* @return the type
	* @since JDK 1.8
	*/
	public String getType() {
		return type;
	}

	
	/**
	* type.
	*
	* @param type the type to set
	* @since JDK 1.8
	*/
	public void setType(String type) {
		this.type = type;
	}
	
	
}
