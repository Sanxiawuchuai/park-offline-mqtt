package com.drzk.offline.vo;

import com.drzk.service.entity.SuperBody;

public class CaptureMiniPhotoJpegBody extends SuperBody
{

	private String picPath;//图片路径
	
	public  void setPicPath(String picPath)
	{
		this.picPath =picPath;
	}
	public String getPicPath()
	{
		return picPath;
	}
}
