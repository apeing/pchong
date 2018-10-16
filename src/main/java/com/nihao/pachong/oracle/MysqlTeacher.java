package com.nihao.pachong.oracle;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nihao.pachong.Course;
import com.nihao.pachong.Dianping;
import com.nihao.pachong.MysqlConnPool;
import com.nihao.pachong.MysqlHelper;
import com.nihao.pachong.Pachong;
import com.nihao.pachong.model.Teacher;

public class MysqlTeacher {
	public static void main(String[] args) throws SQLException, InterruptedException{
		Pachong app = new Pachong();
		Connection connection0 = MysqlConnPool.getConnection();
		ResultSet rs0 = MysqlHelper.executeQuery(connection0,"select id,SCHOOL_NAME,url from train_school where id <=1074  and id > 1047");
		while(rs0.next()){
			String id = rs0.getString(1);
			String schoolname = rs0.getString(2);
			String url = rs0.getString(3);
		
			System.out.println( schoolname + " " + url);

				Dianping obj = new Dianping();
			//	obj = app.runcraw(url,3);
				System.out.println("name : " + obj.getshopname());
				String sqlstr = "select shopname,name,year,type,img,detail from teacher where shopname='" + schoolname + "'";
				ResultSet rs2 = MysqlHelper.executeQuery(connection0,sqlstr);
				if (rs2.next()) {
 
		                Thread.sleep(2000);

				}else{
					obj = app.runcraw(url,3);
					ArrayList<Teacher> tobjs = obj.getShopTeachers();
					System.out.println("name : " + obj.getshopname());
					for(Teacher item : tobjs){
	    			String insertstr = "insert into teacher(shopid,shopname,name,year,type,img,detail) values(";
	                insertstr += "'"+id +"','" + obj.getshopname() + "','" + item.getName() + "','" + item.getYear() + "','" + item.getType() + "','" + item.getImg() + "','" + item.getDetail() + "')";
	                
	                System.out.println("insertstr : " + insertstr);
	                int exeCount = MysqlHelper.executeUpdate(connection0, insertstr); 
					}
					Thread.sleep(2000);
				}
		}
		connection0.close();
	}
}
