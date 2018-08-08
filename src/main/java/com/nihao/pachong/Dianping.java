package com.nihao.pachong;

import java.math.BigDecimal;
import java.util.ArrayList;


@SuppressWarnings("serial")
public class Dianping implements java.io.Serializable{
	private Long id;//商户id
	
    private String shopphone;//电话

    private String shopaddress;//地址

    private String shopname;//商户名称


    private ArrayList<String> shopinfo;
    
    private ArrayList<Course> shopcourses;

    public String getShopPhone() {
        return shopphone;
    }

    public void setShopPhone(String shopphone) {
        this.shopphone = shopphone;
    }

    public String getShopAddress() {
        return shopaddress;
    }

    public void setShopAddress(String shopaddress) {
        this.shopaddress = shopaddress;
    }

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getshopname() {
        return shopname;
    }

    public void setshopname(String shopname) {
        this.shopname = shopname;
    }
    
    public ArrayList<String> getShopInfo(){
    	return shopinfo;
    }
    
    public void setShopInfo(ArrayList<String> shopinfo){
    	this.shopinfo = shopinfo;
    }
    
    public ArrayList<Course> getShopCourses(){
    	return shopcourses;
    }
    
    public void setShopCourses(ArrayList<Course> shopcourses){
    	this.shopcourses = shopcourses;
    }
}
