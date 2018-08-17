package com.nihao.pachong.bean;

import java.io.Serializable;


public class ShopInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;//商户id

	private String shopphone;//电话

    private String shopaddress;//地址

    private String shopname;//商户名称
    
	private String lng; //经度
    
    private String lat; //维度

    private String shopinfo; //商户简介
    
    private String rank; //评价综合指数
    
    public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getShopphone() {
		return shopphone;
	}

	public void setShopphone(String shopphone) {
		this.shopphone = shopphone;
	}

	public String getShopaddress() {
		return shopaddress;
	}

	public void setShopaddress(String shopaddress) {
		this.shopaddress = shopaddress;
	}

	public String getShopname() {
		return shopname;
	}

	public void setShopname(String shopname) {
		this.shopname = shopname;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getShopinfo() {
		return shopinfo;
	}

	public void setShopinfo(String shopinfo) {
		this.shopinfo = shopinfo;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

}
