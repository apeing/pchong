package com.nihao.pachong.model;

public class Teacher {
	private String name ; //当前价格
	private String year ; //教龄
	private String type ; //教学语言
	private String img ; //封面图片地址
	private String detail ; //资历
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getYear(){
		return year;
	}
	
	public void setYear(String year){
		this.year = year;
	}
	
	public String getType(){
		return type;
	}
	
	public void setType(String type){
		this.type = type;
	}
	
	public String getImg(){
		return img;
	}
	
	public void setImg(String img){
		this.img = img;
	}
	
	public String getDetail(){
		return detail;
	}
	
	public void setDetail(String detail){
		this.detail = detail;
	}
}
