package com.nihao.pachong.bean;

import java.io.Serializable;

public class Teacher implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;//id
	private int shopid ; //商户id
	private String name ; //当前价格
	private String year ; //教龄
	private String type ; //教学语言
	private String img ; //封面图片地址
	private String detail ; //资历
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
