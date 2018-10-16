package com.nihao.pachong.tianeye;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.nihao.pachong.MysqlConnPool;
import com.nihao.pachong.MysqlHelper;
import com.nihao.pachong.oracle.OracleConnPool;
import com.nihao.pachong.oracle.OracleHelper;

public class Oraclecha {
	public static void main(String[] args) throws NumberFormatException, SQLException, InterruptedException{
		Connection connection = MysqlConnPool.getConnection();
		ResultSet rs = MysqlHelper.executeQuery(connection,"select schoolid,url,zhijing from company where ID<=1200 and ID >=1");
		int num = 0;
		while(rs.next()) {
			String schoolid = rs.getString(1);
			String url = rs.getString(2);
			String zhijing = rs.getString(3);
    		System.out.println(schoolid + " : " + url);
    		if(zhijing != null & schoolid != null){
    			num = Integer.parseInt(schoolid);
    			Connection connection2 = OracleConnPool.getConnection();
    	//		Company obj = new Tianweb().runSecond(url);
    			
    				
    		//		System.out.println("rs2 run");
    				
    			//注册资金
    	//		int znum1 = infos.indexOf("注册资本：");
    	//		int znum2 = infos.indexOf("注册时间：");
    	//		System.out.println("znum2 : " + znum2);
    	//		System.out.println("znum1 : " + znum1);
    	//		String money =infos.substring(znum1+5,znum2);
    	//		System.out.println("money : " + money);
				String updatestr = "update TRAIN_SCHOOL set REGISTER_CAPITAL = '" + zhijing + "' where id = " + num;
    	//		String updatestr2 = "update company set zhijing = '" + money + "' where id=" + num;
				System.out.println(updatestr);
				OracleHelper.executeQuery(connection2, updatestr);
    				
    			
    			connection2.close();
    		}
		}
	}
}
