package com.nihao.pachong.bean;

import java.io.Serializable;
import java.util.Map;

public class Course implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;//课程id
	private int shopid;//商户id
	private String cur ; //当前价格
	private String title ; //课程名称
	private String desc ; //描述
	private String img ; //封面图片地址
	private String address ; //课程地址
	private Map<String, String> infos; //课程简介列表
	public Map<String, String> getInfos() {
		return infos;
	}
	public void setInfos(Map<String, String> infos) {
		this.infos = infos;
	}
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
	public String getCur() {
		return cur;
	}
	public void setCur(String cur) {
		this.cur = cur;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
