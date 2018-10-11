package com.drzk.offline.vo;

import java.io.Serializable;

public class CaptureMiniPhotoJpegBody implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 576217992646195307L;
	private String uId;
	private String picPath;//图片路径
	
	public  void setPicPath(String picPath)
	{
		this.picPath =picPath;
	}
	public String getPicPath()
	{
		return picPath;
	}
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}
}
