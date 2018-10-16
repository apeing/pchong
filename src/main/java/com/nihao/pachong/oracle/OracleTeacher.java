package com.nihao.pachong.oracle;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.nihao.pachong.MysqlConnPool;
import com.nihao.pachong.MysqlHelper;

public class OracleTeacher {
	public static void main(String[] args) throws SQLException, InterruptedException{
	
		Connection connection = OracleConnPool.getConnection();
		
		ResultSet rs = OracleHelper.executeQuery(connection,"select ID,SCHOOL_NAME from TRAIN_SCHOOL where id >= 3500 and id <4000");
	//	int idnumber = 2230;
		while (rs.next()) {
			
			String id = rs.getString(1);
			String school_name = rs.getString(2);
			System.out.println("schoolid : " + id);
			Connection connection1 = MysqlConnPool.getConnection();
			Connection connection2 = OracleConnPool.getConnection();
			ResultSet rs1 = MysqlHelper.executeQuery(connection1,"select shopname,name,year,type,img,detail from teacher where shopname = '" + school_name+"'");
			
			while(rs1.next()){
				String sqlid = "select SEQ_TRAIN_Teacher.nextval from dual";
				String shopname = rs1.getString(1);
				String teachername = rs1.getString(2);
				String special = rs1.getString(3) + "|" + rs1.getString(4);
				String logo = rs1.getString(5);
				String info = rs1.getString(6);
				ResultSet rs2 = MysqlHelper.executeQuery(connection2,sqlid);
				if(rs2.next()){
					String tid = rs2.getString(1);
					String sqlstr = "insert into TRAIN_TEACHER(ID,SCHOOL_ID,SCHOOL_NAME,TEACHER_NAME,TEACHER_SPECIAL,LOGO_URL,TEACHER_INFO,OPERATOR,STATE) values(";
					sqlstr += tid + "," + id + ",'" + shopname + "','" + teachername + "','" + special + "','" + logo + "','" + info + "',1,'1')";
					OracleHelper.executeUpdate(connection2, sqlstr);
					System.out.println(sqlstr);
					Thread.sleep(100);
				}
			}
			connection1.close();
			connection2.close();
		}
		connection.close();
		
	}
}
