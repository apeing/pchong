package com.nihao.pachong;

import java.util.ArrayList;

import com.nihao.pachong.model.Comment;
import com.nihao.pachong.model.Shopmedia;
import com.nihao.pachong.model.Shoppicture;
import com.nihao.pachong.model.Teacher;


@SuppressWarnings("serial")
public class Dianping implements java.io.Serializable{
	private Long id;//商户id
	
    private String shopphone;//电话

    private String shopaddress;//地址

    private String shopname;//商户名称
    
    private String lng; //经度
    
    private String lat; //维度

    private ArrayList<String> shopinfo; //商户简介
    
    private ArrayList<Course> shopcourses;  //课程
    
    private ArrayList<Teacher> shopteachers; //师资
    
    private ArrayList<Shoppicture> shoppictures; //官方图片
    
    private ArrayList<Shopmedia> shopmedias; //官方视频
    
    private ArrayList<Comment> shopcomments; //用户点评
    
    private String rank; //评价综合指数

    public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public ArrayList<Comment> getShopcomments() {
		return shopcomments;
	}

	public void setShopcomments(ArrayList<Comment> shopcomments) {
		this.shopcomments = shopcomments;
	}

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
    
    public String getLng(){
    	return lng;
    }
    
    public void setLng(String lng){
    	this.lng = lng;
    }
    
    public String getLat(){
    	return lat;
    }
    
    public void setLat(String lat){
    	this.lat = lat;
    }
    public ArrayList<Course> getShopCourses(){
    	return shopcourses;
    }
    
    public void setShopCourses(ArrayList<Course> shopcourses){
    	this.shopcourses = shopcourses;
    }
    
    public ArrayList<Teacher> getShopTeachers(){
    	return shopteachers;
    }
    
    public void setShopTeachers(ArrayList<Teacher> shopteachers){
    	this.shopteachers = shopteachers;
    }
    
    public ArrayList<Shoppicture> getShopPictures(){
    	return shoppictures;
    }
    
    public void setShopPictures(ArrayList<Shoppicture> shoppictures){
    	this.shoppictures = shoppictures;
    }

	public ArrayList<Shopmedia> getShopmedias() {
		return shopmedias;
	}

	public void setShopmedias(ArrayList<Shopmedia> shopmedias) {
		this.shopmedias = shopmedias;
	}
    
}
