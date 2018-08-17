package com.nihao.pachong.model;

import java.util.ArrayList;

public class Comment {
	private String username;//用户名称
	
	private String rank;//星数等级
	
	private String rst; //各项评分
	
	private String remark; //评语
	
	private ArrayList<String> imgurls; //照片集

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getRst() {
		return rst;
	}

	public void setRst(String rst) {
		this.rst = rst;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public ArrayList<String> getImgurls() {
		return imgurls;
	}

	public void setImgurls(ArrayList<String> imgurls) {
		this.imgurls = imgurls;
	}
}
