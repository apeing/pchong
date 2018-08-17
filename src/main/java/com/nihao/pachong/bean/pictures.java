package com.nihao.pachong.bean;

import java.io.Serializable;

public class pictures implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private int shopid;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getShopid() {
		return shopid;
	}
	public void setShopid(int shopid) {
		this.shopid = shopid;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	private String img;

}
