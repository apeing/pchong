package com.nihao.pachong;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;


public class Mysqldemo {
	public static void main( String[] args ) throws SQLException{
	   	//写入相应的文件
		Pachong app = new Pachong();
    //	app.runcraw("http://www.dianping.com/shop/9038889",3);
    	List<Map<String, String>> urlnew = app.crawler("http://www.dianping.com/chengdu/education",1);
    	Connection connection = MysqlConnPool.getInstance().getConnection();
    	ResultSet rs = MysqlHelper.executeQuery(connection, "select max(id) from traintype");
    	if (rs.next()){
    	//	String str = rs.getString(1);
        //    System.out.println(str);
        //    int num = Integer.parseInt(str) + 1;
        //    System.out.println(num);
	    	for (Map<String, String> v : urlnew){
	    		String insertstr1 = "insert into traintype(name,topclass) values('";
            	insertstr1 +=  v.get("type")+ "'," + v.get("topclass") + ")";
            	MysqlHelper.executeUpdate(connection, insertstr1);
	    		System.out.println("url= " + v.get("url"));
	    		System.out.println("type= " + v.get("type"));
	    		
	    	}
    	}
    	connection.close();
        System.out.println("ok");
	}
	
/*	public static void main( String[] args ) throws SQLException{
		System.out.println( "Hello World!" );
        Connection conn = null;
        String sql;
        
        String conn_str = "jdbc:mysql://localhost:3306/pachong?"
                + "user=root&password=123456&useUnicode=true&characterEncoding=UTF8";
        try {
            Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动 
            System.out.println("成功加载MySQL驱动程序");
            
            // 一个Connection代表一个数据库连接
            conn = DriverManager.getConnection(conn_str);
            Statement stmt = conn.createStatement();
            sql = "show tables;";
            ResultSet result = stmt.executeQuery(sql);
            if (result != null) {
                while (result.next()) {
                    System.out.println(result.getString(1) + "\t");
                }
            }
        } catch (SQLException e) {
            System.out.println("MySQL操作错误");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }

	}*/
}
