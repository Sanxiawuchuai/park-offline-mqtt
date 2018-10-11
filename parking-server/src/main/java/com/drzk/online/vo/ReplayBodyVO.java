package com.drzk.online.vo;

import java.io.Serializable;
import java.util.List;

public class ReplayBodyVO implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1028101676545780663L;
	private List<ObjectBody>  body;

	public List<ObjectBody> getBody() {
		return body;
	}

	public void setBody(List<ObjectBody> body) {
		this.body = body;
	}

	

	
}
