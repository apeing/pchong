package com.nihao.pachong;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.nihao.pachong.model.Shopmedia;
import com.nihao.pachong.model.Shoppicture;
import com.nihao.pachong.model.Teacher;

public class Poolapp {
	public static void main(String[] args) throws SQLException, InterruptedException {
		Pachong app = new Pachong();
		@SuppressWarnings("static-access")
		Connection connection = MysqlConnPool.getInstance().getConnection();
		List<Map<String, String>> mapfirst = new ArrayList<Map<String, String>>();
		mapfirst = app.crawler("http://www.dianping.com/chengdu/education",1);
		for(Map<String, String> fir : mapfirst){
			System.out.println("url= " + fir.get("url"));
    		List<Map<String, String>> mapsecond = new ArrayList<Map<String, String>>();
    		mapsecond = app.crawler(fir.get("url"),2);
    		for(Map<String, String> sec : mapsecond){
    			System.out.println("stars= " + sec.get("stars"));
    			System.out.println("师资= " + sec.get("师资"));
    			System.out.println("效果= " + sec.get("效果"));
    			System.out.println("环境= " + sec.get("环境"));
    			Dianping obj = new Dianping();
    			obj = app.runcraw(sec.get("url"),3);
    			ResultSet rs = MysqlHelper.executeQuery(connection, "select max(id) from shopinfo");
    	        try {
    	            if (rs.next()) {
    	                String str = rs.getString(1);
    	                System.out.println(str);
    	                int num = Integer.parseInt(str) + 1;
    	                System.out.println(num);
    	                int exeCount = 0;
    	                
    	                //官方相册
    	                for( Shoppicture item : obj.getShopPictures()){
    	                	String insertstr1 = "insert into pictures(shopid,img) values(";
    	                	insertstr1 += num + ",'" + item.getImg() + "')";
    	                	exeCount = MysqlHelper.executeUpdate(connection, insertstr1);
    	                }
    	               
    	                //商户信息
    	                String insertstr = "insert into shopinfo(shopphone,shopaddress,shopname,lng,lat,shopinfo,rank,tscores,xscores,hscores,stars) values(";
    	                insertstr += "'" + obj.getShopPhone() + "','" + obj.getShopAddress() + "','" + obj.getshopname() + "','" + obj.getLng() + "','" + obj.getLat() + "','";
    	                String infos = "";
    	                for(String item : obj.getShopInfo()){
    	                	infos += item;
    	                }
    	                insertstr += infos +"','"+ obj.getRank() + "','" + sec.get("师资") + "','" + sec.get("效果") + "','" + sec.get("环境") + "','" + sec.get("stars") + "')";
    	                exeCount = MysqlHelper.executeUpdate(connection, insertstr);
    	                
    	                //教师
    	                for(Teacher item : obj.getShopTeachers()){
    	                	String insertstr2 = "insert into teacher(shopid,name,year,type,img,detail) values(";
    	                	insertstr2 += num + ",'" +item.getName() + "','" + item.getYear() + "','" + item.getType() + "','";
    	                	insertstr2 += item.getImg() + "','" + item.getDetail() + "')";
    	                	exeCount = MysqlHelper.executeUpdate(connection, insertstr2);
    	                }
    	                
    	                //课程
    	                for( Course item : obj.getShopCourses()){
    	                	String insertstr3 = "insert into tcourse(shopid,cur,title,cdesc,img,address) values(";
    	                	insertstr3 += num + ",'" + item.getCur() + "','" + item.getTitle() + "','" + item.getDesc() + "','";
    	                	insertstr3 += item.getImg() + "','" + item.getAddress() + "')";
    	                	exeCount = MysqlHelper.executeUpdate(connection, insertstr3);
    	                }
    	                
    	                //官方视频
    	                for(Shopmedia item : obj.getShopmedias()){
    	                	String insertstr4 = "insert into vedios(shopid,url,title) values(";
    	                	insertstr4 += num + ",'" + item.getUrl() + "','" + item.getTitle() + "')";
    	                	exeCount = MysqlHelper.executeUpdate(connection, insertstr4);
    	                }
    	            }
    	            
    	        } catch (SQLException e) {
    	            e.printStackTrace();
    	        } finally {
    	        	Thread.sleep(5000);
    	        }
    		}
	    }
		connection.close();
    }

}
