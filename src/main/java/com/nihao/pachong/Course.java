package com.nihao.pachong;

public class Course {
	private String cur ; //当前价格
	private String title ; //课程名称
	private String desc ; //描述
	private String img ; //封面图片地址
	private String address ; //课程地址
	
	public String getCur() {
		return cur;
	}
	public void setCur(String cur){
		this.cur = cur;
	}
	
	public String getTitle(){
		return title;
	}
	public void setTitle(String title){
		this.title = title;
	}
	
	public String getDesc(){
		return desc;
	}
	public void setDesc(String desc){
		this.desc = desc;
	}
	
	public String getImg(){
		return img;
	}
	public void setImg(String img){
		this.img = img;
	}
	
	public String getAddress(){
		return address;
	}
	public void setAddress(String address){
		this.address = address;
	}
}
