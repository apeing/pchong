package com.nihao.pachong;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class oracleapp {
	public static void main(String[] args) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        //产生JDBC Driver对象，三种方法
        Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
    //    new oracle.jdbc.driver.OracleDriver();
    //    Class.forName("oracle.jdbc.driver.OracleDriver");//这个Driver的实例化在构建过程中会自动向DriverManager自动注册
        //连接数据库
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.1.190:1521:orcl","c##schoolFees2","SCHOOLFEES2");//拿到数据库的链接
        
        //创建语句对象statement，作用：调用方法向数据库传递SQL语句，并接受数据库返回的结果集
        java.sql.Statement sts = conn.createStatement();
        ResultSet rs = sts.executeQuery("select * from TRAIN_TYPE"); //在第一条记录的前一位，
     //   rs.next();
        
        //循环取得结果集
        
        
        while (rs.next()) {
            //转化数据类型
            System.out.print(rs.getInt("id") + " ");
            System.out.print(rs.getString("TRAIN_TYPE_NAME") + " ");
            
        }
        System.out.print("rs : ");
        //关闭资源
        rs.close();
        sts.close();
        conn.close();
    }
}
