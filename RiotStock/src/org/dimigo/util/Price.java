package org.dimigo.util;

import java.util.Date;

public class Price {
	double price;
	Date date;
	int volume;
	double win;
	double pick;
	public double getWin() {
		return win;
	}
	public void setWin(double win) {
		this.win = win;
	}
	public double getPick() {
		return pick;
	}
	public void setPick(double pick) {
		this.pick = pick;
	}
	public int getVolume() {
		return volume;
	}
	public void setVolume(int volume) {
		this.volume = volume;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "Price [price=" + price + ", date=" + date + "]";
	}
	public Price() {
		
	}
	public Price(double price, Date date) {
		super();
		this.price = price;
		this.date = date;
	}
	
	
}
