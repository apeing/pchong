package com.nihao.pachong.oracle;

import java.sql.SQLException;

import com.nihao.pachong.Pachong;
import com.nihao.pachong.bean.Course;

public class Coursedemo {
	public static void main(String[] args) throws SQLException, InterruptedException{
		Pachong app = new Pachong();
		Course obj = new Course();
		obj = app.runcourse("http://www.dianping.com/edu/sku/3917335/98320272");
		System.out.println("课程性质 : " + obj.getInfos().get("课程性质"));
	}
}
