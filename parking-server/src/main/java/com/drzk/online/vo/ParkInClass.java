package com.drzk.online.vo;

import java.io.Serializable;
import java.util.List;

public class ParkInClass implements Serializable
{	 
		/**
	 * 
	 */
	private static final long serialVersionUID = 6578694013519737928L;
		private List<ParkCarInVO> data;

		public List<ParkCarInVO> getData()
		{
			return data;
		}

		public void setData(List<ParkCarInVO> data)
		{
			this.data = data;
		}			 
}
