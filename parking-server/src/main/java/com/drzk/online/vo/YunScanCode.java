package com.drzk.online.vo;


public class YunScanCode<H,B>
{
	private H head;
	
	private B body;
	
	public YunScanCode(H head,B body)
	{
		this.head = head;
		this.body = body;
	}

	public H getHead()
	{
		return head;
	}

	public void setHead(H head)
	{
		this.head = head;
	}

	public B getBody()
	{
		return body;
	}

	public void setBody(B body)
	{
		this.body = body;
	}
}
