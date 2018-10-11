package com.drzk.online.vo;

import java.util.List;

public class DataClass<T>
{	

	private SendHead head;
	
	private List<T> body;

	public SendHead getHead()
	{
		return head;
	}

	public void setHead(SendHead head)
	{
		this.head = head;
	}

	public List<T> getBody()
	{
		return body;
	}

	public void setBody(List<T> body)
	{
		this.body = body;
	}
	
	
}
