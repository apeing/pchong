package com.nihao.pachong;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.nihao.pachong.model.Shoppicture;

public class trainstype {
/*	public static void main(String[] args) throws SQLException, InterruptedException{
		Pachong app = new Pachong();
		Connection connection = MysqlConnPool.getInstance().getConnection();
		List<Map<String, String>> mapfirst = new ArrayList<Map<String, String>>();
		mapfirst = app.crawler("http://www.dianping.com/chengdu/education",1);
		for(Map<String, String> fir : mapfirst){
			System.out.println("url= " + fir.get("url"));
		
        	String insertstr1 = "insert into traintype(name,topclass,url,pname) values('";
        	insertstr1 += fir.get("type") + "','" + fir.get("topclass") + "','" + fir.get("url") + "','" + fir.get("ptype") + "')";
        	int exeCount = MysqlHelper.executeUpdate(connection, insertstr1);
        	Thread.sleep(1000);
		}
		connection.close();
	} */
	public static void main(String[] args) throws InterruptedException{
		Pachong app = new Pachong();
		Connection connection = MysqlConnPool.getInstance().getConnection();
		List<Map<String, String>> mapfirst = new ArrayList<Map<String, String>>();
		ResultSet rs = MysqlHelper.executeQuery(connection, "select * from traintype");
		try {
			if (rs.next()){
			//	 String str = rs.getString(1);
	             System.out.println(rs.getString(3));
				
				connection.close();
			}
		}catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	Thread.sleep(5000);
        	
        }
		
		
	}
}
