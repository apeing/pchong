package com.nihao.pachong.oracle;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.nihao.pachong.MysqlHelper;
import com.nihao.pachong.Pachong;



public class Oracledemo {
	public static void main(String[] args) throws SQLException{
		/*Connection connection = OracleConnPool.getConnection();
		ResultSet rs = OracleHelper.executeQuery(connection, "select * from TRAIN_TYPE");
		while (rs.next()) {
            //转化数据类型
            System.out.print(rs.getInt("id") + " ");
            System.out.print(rs.getString("TRAIN_TYPE_NAME") + " ");
            
        }*/
		Pachong app = new Pachong();
		Connection connection = OracleConnPool.getConnection();
		List<Map<String, String>> urlnew = app.crawler("http://www.dianping.com/chengdu/education",1);
		int num = 501 ;
    //	ResultSet rs = MysqlHelper.executeQuery(connection, "select max(id) from TRAIN_TYPE");
    //	
    //	if (rs.next()){
   // 		String str = rs.getString(1);
   // 		System.out.println(str);
    //		int num = 1 ;
    //		if(str != null & str != ""){
    //			num = Integer.parseInt(str) + 1;
    //		}
   //     //    System.out.println(str);
            System.out.println(num);
	    	for (Map<String, String> v : urlnew){
	    		String insertstr1 = "insert into TRAIN_TYPE(ID,TRAIN_TYPE_NAME,IS_LEAF,STATE) values(";
            	insertstr1 +=num +",'"+ v.get("type")+ "','" + v.get("topclass") + "','1')";
            	MysqlHelper.executeUpdate(connection, insertstr1);
	    		System.out.println("url= " + v.get("url"));
	    		System.out.println("type= " + v.get("type"));
	    		num++;
	//    	}
    		
    	}
		connection.close();
	}
}
