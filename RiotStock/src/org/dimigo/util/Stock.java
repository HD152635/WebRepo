package org.dimigo.util;

import java.util.Date;

public class Stock {
	private Date date;
	private String userID;
	private String stockID;
	private double price;	//1주당
	private double share;
	
	public Stock() {
		
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getStockID() {
		return stockID;
	}
	public void setStockID(String stockID) {
		this.stockID = stockID;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getShare() {
		return share;
	}
	public void setShare(double share) {
		this.share = share;
	}
	@Override
	public String toString() {
		return "Stock [date=" + date + ", userID=" + userID + ", stockID=" + stockID + ", price=" + price + ", share="
				+ share + "]";
	}
	public Stock(String userID, String stockID, double price, double share) {
		super();
		this.date = new Date();
		this.userID = userID;
		this.stockID = stockID;
		this.price = price;
		this.share = share;
	}
	public Stock(Date date, String userID, String stockID, double price, double share) {
		super();
		this.date = date;
		this.userID = userID;
		this.stockID = stockID;
		this.price = price;
		this.share = share;
	}
}
