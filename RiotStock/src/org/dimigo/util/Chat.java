package org.dimigo.util;

import java.util.Date;

public class Chat {
	private Date date;
	private String userId;
	private String text;
	public Chat() {
		
	}
	public Chat(Date date, String userId, String text) {
		super();
		this.date = date;
		this.userId = userId;
		this.text = text;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
}
