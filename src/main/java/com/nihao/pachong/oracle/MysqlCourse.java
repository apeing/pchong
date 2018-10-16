package com.nihao.pachong.oracle;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.nihao.pachong.Course;
import com.nihao.pachong.Dianping;
import com.nihao.pachong.Pachong;

public class MysqlCourse {
	public static void main(String[] args) throws SQLException, InterruptedException{
		Pachong app = new Pachong();
		@SuppressWarnings("static-access")
		List<Map<String, String>> mapfirst = new ArrayList<Map<String, String>>();
		int num2 = 8791;
		mapfirst = app.crawler("http://www.dianping.com/chengdu/education",1);
		
		for(Map<String, String> fir : mapfirst){
			Thread.sleep(2000);
			System.out.println("url= " + fir.get("url"));
    		List<Map<String, String>> mapsecond = new ArrayList<Map<String, String>>();
    		mapsecond = app.crawler(fir.get("url"),2);
    		
    		for(Map<String, String> sec : mapsecond){
    			Thread.sleep(2000);
    			Dianping obj = new Dianping();
    			Connection connection = OracleConnPool.getConnection();
    			obj = app.runcraw(sec.get("url"),3);
    			String sqlstr = "select id from TRAIN_SCHOOL where SCHOOL_NAME='" + obj.getshopname() + "'";
    			ResultSet rs2 = OracleHelper.executeQuery(connection,sqlstr);
    			if (rs2.next()){
    				String rsid = rs2.getString(1);
    				int num = Integer.parseInt(rsid);
    				//课程
	                for( Course item : obj.getShopCourses()){
	                	Thread.sleep(2000);
	                	com.nihao.pachong.bean.Course coursetmp = new com.nihao.pachong.bean.Course();
	                	coursetmp = app.runcourse(item.getAddress());
	                	String insertstr3 = "insert into TRAIN_COURSE(id,SCHOOL_ID,SCHOOL_NAME,COURSE_DISCOUNT_PRICE,COURSE_NAME,COURSE_TYPE,FIT_TARGET,COURSE_TIME,COURSE_SPECIAL) values(";
	                	insertstr3 += num2+ "," + num + ",'"+obj.getshopname() +"','"+ item.getCur() + "','" + item.getTitle() +"','" +coursetmp.getInfos().get("课程类型");
	                	insertstr3 += "','" + coursetmp.getInfos().get("适合年龄段")+"','" +coursetmp.getInfos().get("每次课时长") + "','" + coursetmp.getInfos().get("课程亮点") +	"')";
	                	int exeCount = OracleUtils.Update(insertstr3);
	                	num2++;
	                }
    			}
    			System.out.println("num2= " + num2);
    			connection.close();
    			
    		}
		}
	}
}
