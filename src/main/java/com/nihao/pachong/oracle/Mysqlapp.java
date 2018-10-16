package com.nihao.pachong.oracle;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nihao.pachong.Dianping;
import com.nihao.pachong.MysqlConnPool;
import com.nihao.pachong.MysqlHelper;
import com.nihao.pachong.Pachong;
import com.nihao.pachong.Course;

public class Mysqlapp {

	public static void main(String[] args) throws SQLException, InterruptedException{
		Pachong app = new Pachong();
		Connection connection0 = MysqlConnPool.getConnection();
		ResultSet rs0 = MysqlHelper.executeQuery(connection0,"select id,TRAIN_TYPE_NAME,url from pachong_type where IS_LEAF = 0 and id <= 570 and id > 560");
		while(rs0.next()){
			String str1 = rs0.getString(1);
			String str2 = rs0.getString(2);
			String str3 = rs0.getString(3);
			System.out.println( str1 + " " + str2 + " " + str3);
			List<Map<String, String>> urlnew = new ArrayList<Map<String, String>>();
			urlnew = app.crawler(rs0.getString(3),2);
			
			for(Map<String, String> sec : urlnew){
			//	num++;
				Dianping obj = new Dianping();
				obj = app.runcraw(sec.get("url"),3);
				String tmptypeid = rs0.getString(1);
				String tmptypename = rs0.getString(2);
				System.out.println("name : " + obj.getshopname());
				String sqlstr = "select id,TRAIN_TYPE_ID,TRAIN_TYPE_NAME from train_school where SCHOOL_NAME='" + obj.getshopname() + "'";
				ResultSet rs2 = MysqlHelper.executeQuery(connection0,sqlstr);
				if (rs2.next()) {
//					String numstr = rs2.getString(1);
//					String namestr = rs2.getString(3);
//					String num3str = rs2.getString(2);
//		    		if(numstr != null & numstr != ""){
//		    			System.out.println("str : " + numstr);
//		    			int num2 = Integer.parseInt(numstr);
//		    			num3str +=tmptypeid + ",";
//		    			namestr +=tmptypename + ",";
//		                String updatestr = "UPDATE train_school SET TRAIN_TYPE_ID = '" + num3str + "', TRAIN_TYPE_NAME = '"+ namestr +"' WHERE ID = " + num2;
//		                System.out.println("UPDATE : " + updatestr);
//		                int exeCount = MysqlHelper.executeUpdate(connection0, updatestr); 
		                Thread.sleep(2000);
//		    		}
				}else{
					String addr = obj.getShopAddress();
	    			
	    			Map<String, String> infos = new HashMap<String, String>();
	    			//商户简介
	                for(String item : obj.getShopInfo()){
	                	System.out.println("item : " + item.trim());
	                	String tmpstr = item.trim();
	                	infos.put(tmpstr.substring(0,4),tmpstr.substring(5,tmpstr.length()));
	                	System.out.println("key : " + tmpstr.substring(0,4) + "value : " + tmpstr.substring(5,tmpstr.length()));
	                }
	                //商户课程
	                String coursestr = "";
	                for(Course objcourse : obj.getShopCourses()){
	                	coursestr += objcourse.getTitle() + ":" + objcourse.getCur() + ";";
	                }
	    			String insertstr = "insert into train_school(SCHOOL_NAME,BANNER_URL,INSTITUTION_INFO,LONGITUDE,LATITUDE,ADDRESS,CONTACT_PHONE,COMMENT_NUM,EFFECT_SCORE,TEACHER_SCORE,ENVIRONMENT_SCORE,STATE,STAR_LEVEL,TRAIN_TYPE_ID,TRAIN_TYPE_NAME,COURSE_INFO,CAMPUS_SITUATION,url) values(";
	                insertstr += "'" + obj.getshopname() + "','" + obj.getBanner() + "','" + infos.get("商户介绍") + "','" + obj.getLng() + "','" + obj.getLat() + "','";
	                
	                insertstr +=addr.substring(4,addr.length()) +"','" +  obj.getShopPhone() + "'," + sec.get("commentsnum") +",'" + sec.get("效果") + "','" + sec.get("师资") + "','" + sec.get("环境") + "','" + "1','" + sec.get("stars") + "','," + tmptypeid + ",','," + tmptypename + ",','" + coursestr +"','" + obj.getBranch()+ "','" +sec.get("url") + "')";
	                System.out.println("insertstr : " + insertstr);
	                int exeCount = MysqlHelper.executeUpdate(connection0, insertstr); 
	                Thread.sleep(2000);
				}
		}
		/*	List<Map<String, String>> urlnew = new ArrayList<Map<String, String>>();
		urlnew = app.crawler("http://www.dianping.com/chengdu/ch75/g2910",2);
		Connection connection = MysqlConnPool.getConnection();
		ResultSet rs = MysqlHelper.executeQuery(connection,"select max(id) from TRAIN_SCHOOL");
		int num = 0;
		if (rs.next()) {
			String str = rs.getString(1);
    	//	System.out.println(str);
    		if(str != null & str != ""){
    			num = Integer.parseInt(str);
    		}
		}
		
		for(Map<String, String> sec : urlnew){
			num++;
			Dianping obj = new Dianping();
			obj = app.runcraw(sec.get("url"),3);
			String tmptypeid = "558";
			String tmptypename = "其他院校";
			System.out.println("name : " + obj.getshopname());
			String sqlstr = "select id,TRAIN_TYPE_ID,TRAIN_TYPE_NAME from TRAIN_SCHOOL where SCHOOL_NAME='" + obj.getshopname() + "'";
			ResultSet rs2 = MysqlHelper.executeQuery(connection,sqlstr);
			if (rs2.next()) {
				String numstr = rs2.getString(1);
				String namestr = rs2.getString(3);
				String num3str = rs2.getString(2);
	    		if(numstr != null & numstr != ""){
	    			System.out.println("str : " + numstr);
	    			int num2 = Integer.parseInt(numstr);
	    			num3str +=tmptypeid + ",";
	    			namestr +=tmptypename + ",";
	                String updatestr = "UPDATE TRAIN_SCHOOL SET TRAIN_TYPE_ID = '" + num3str + "', TRAIN_TYPE_NAME = '"+ namestr +"' WHERE ID = " + num2;
	                System.out.println("UPDATE : " + updatestr);
	                int exeCount = OracleUtils.Update(updatestr); 
	                Thread.sleep(2000);
	    		}
			}else{
				String addr = obj.getShopAddress();
    			
    			Map<String, String> infos = new HashMap<String, String>();
    			//商户简介
                for(String item : obj.getShopInfo()){
                	System.out.println("item : " + item.trim());
                	String tmpstr = item.trim();
                	infos.put(tmpstr.substring(0,4),tmpstr.substring(5,tmpstr.length()));
                	System.out.println("key : " + tmpstr.substring(0,4) + "value : " + tmpstr.substring(5,tmpstr.length()));
                }
                //商户课程
                String coursestr = "";
                for(Course objcourse : obj.getShopCourses()){
                	coursestr += objcourse.getTitle() + ":" + objcourse.getCur() + ";";
                }
    			String insertstr = "insert into TRAIN_SCHOOL(ID,SCHOOL_NAME,BANNER_URL,INSTITUTION_INFO,LONGITUDE,LATITUDE,ADDRESS,CONTACT_PHONE,COMMENT_NUM,EFFECT_SCORE,TEACHER_SCORE,ENVIRONMENT_SCORE,STATE,STAR_LEVEL,TRAIN_TYPE_ID,TRAIN_TYPE_NAME,COURSE_INFO,CAMPUS_SITUATION) values(";
                insertstr += num + ",'" + obj.getshopname() + "','" + obj.getBanner() + "','" + infos.get("商户介绍") + "','" + obj.getLng() + "','" + obj.getLat() + "','";
                
                insertstr +=addr.substring(4,addr.length()) +"','" +  obj.getShopPhone() + "'," + sec.get("commentsnum") +",'" + sec.get("效果") + "','" + sec.get("师资") + "','" + sec.get("环境") + "','" + "1','" + sec.get("stars") + "','," + tmptypeid + ",','," + tmptypename + ",','" + coursestr +"','" + obj.getBranch()+ "')";
                System.out.println("insertstr : " + insertstr);
                int exeCount = OracleUtils.Update(insertstr); 
                Thread.sleep(2000);
			}
		} */
		}
		connection0.close();
	}
}
