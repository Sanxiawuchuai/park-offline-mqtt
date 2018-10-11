package com.drzk.online.vo;

import java.io.Serializable;

public class ObjectBody implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -139554859355387066L;
	private String objectId;

	public String getObjectId()
	{
		return objectId;
	}

	public void setObjectId(String objectId)
	{
		this.objectId = objectId;
	}
}
