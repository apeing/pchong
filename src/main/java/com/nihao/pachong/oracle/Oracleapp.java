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
import com.nihao.pachong.MysqlHelper;
import com.nihao.pachong.Pachong;

public class Oracleapp {
	public static void main(String[] args) throws SQLException, InterruptedException{
		Pachong app = new Pachong();
		Connection connection = OracleConnPool.getConnection();
		List<Map<String, String>> mapfirst = new ArrayList<Map<String, String>>();
		mapfirst = app.crawler("http://www.dianping.com/chengdu/education",1);
		ResultSet rs = MysqlHelper.executeQuery(connection, "select max(id) from TRAIN_SCHOOL");
		int num = 0;
		if (rs.next()) {
			String str = rs.getString(1);
    	//	System.out.println(str);
    		if(str != null & str != ""){
    			num = Integer.parseInt(str);
    		}
		}
		for(Map<String, String> fir : mapfirst){
		//	if(num > 10){
		//		connection.close();
		//		return ;
		//	}
			if(fir.get("type") == "外语培训"){
				continue;
			}
			List<Map<String, String>> urlnew = new ArrayList<Map<String, String>>();
			urlnew = app.crawler(fir.get("url"),2);
			for(Map<String, String> sec : urlnew){
				num++;
				System.out.println("num : " + num);
				Dianping obj = new Dianping();
				obj = app.runcraw(sec.get("url"),3);
				
		//		System.out.println("CONTACT_PHONE : " + obj.getShopPhone());
				String addr = obj.getShopAddress();
		//		System.out.println("ADDRESS : " + addr.substring(4,addr.length()-4));
		//		System.out.println("MERCHANT_NAME : " + obj.getshopname());
		//		System.out.println("LONGITUDE : " + obj.getLng());
		//		System.out.println("LATITUDE : " + obj.getLat());
		//		System.out.println("BANNER_URL : " + obj.getBanner());
		//		System.out.println("EFFECT_SCORE : " + sec.get("效果"));
		//		System.out.println("TEACHER_SCORE : " + sec.get("师资"));
		//		System.out.println("ENVIRONMENT_SCORE : " + sec.get("环境"));
		//		System.out.println("COMMENT_NUM : " + sec.get("commentsnum"));
				
				//String infos = "";
				Map<String, String> infos = new HashMap<String, String>();
	            for(String item : obj.getShopInfo()){
	            	System.out.println("item : " + item.trim());
	            	String tmpstr = item.trim();
	            	infos.put(tmpstr.substring(0,4),tmpstr.substring(5,tmpstr.length()));
	            	System.out.println("key : " + tmpstr.substring(0,4) + "value : " + tmpstr.substring(5,tmpstr.length()));
	            }
	      //      SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
	       //     ParsePosition pos = new ParsePosition(0);
	       //     Date strtodate = formatter.parse(infos.get("创立时间").substring(0,4), pos);
	       //     System.out.println("Date : " + strtodate);
	       //     System.out.println("infos : " + infos);
	            
				String insertstr = "insert into TRAIN_SCHOOL(ID,MERCHANT_NAME,BANNER_URL,INSTITUTION_INFO,LONGITUDE,LATITUDE,ADDRESS,CONTACT_PHONE,COMMENT_NUM,EFFECT_SCORE,TEACHER_SCORE,ENVIRONMENT_SCORE,STATE,STAR_LEVEL,TRAIN_TYPE_ID,TRAIN_TYPE_NAME) values(";
	            insertstr += num + ",'" + obj.getshopname() + "','" + obj.getBanner() + "','" + infos.get("商户介绍") + "','" + obj.getLng() + "','" + obj.getLat() + "','";
	            
	            insertstr +=addr.substring(4,addr.length()-4) +"','" +  obj.getShopPhone() + "'," + sec.get("commentsnum") +",'" + sec.get("效果") + "','" + sec.get("师资") + "','" + sec.get("环境") + "','" + "1','" + sec.get("stars") + "',1,'" + fir.get("type") + "')";
	            System.out.println("insertstr : " + insertstr);
	            int exeCount = OracleHelper.executeUpdate(connection, insertstr); 
	            Thread.sleep(2000);
			}
		}
		connection.close();
	}
}
