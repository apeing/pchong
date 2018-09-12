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
		Connection connection = OracleConnPool.getConnection();
		ResultSet rs = OracleHelper.executeQuery(connection,"select ID,SCHOOL_NAME from TRAIN_SCHOOL where ID<=3900 and ID >=3801");
		int num = 0;
		while(rs.next()) {
			String id = rs.getString(1);
			String schoolname = rs.getString(2);
    		System.out.println(id + " : " + schoolname);
    		if(id != null & id != ""){
    			num = Integer.parseInt(id);
    			Connection connection2 = MysqlConnPool.getInstance().getConnection();
    			List<Company> list = new Tianweb().runfirst("https://www.tianyancha.com/search?key=" + schoolname);
    			if(list.size() >= 1){
    				
    				ResultSet rs2 = MysqlHelper.executeQuery(connection2,"select schoolid from company where schoolid=" + num);
    				System.out.println("rs2 run");
    				
    				if(!rs2.next()){
    					System.out.println(" list" + num + " : " + list.get(0).getName() + " " + list.get(0).getStatus() + list.get(0).getUrl());
        				String insertstr = "insert into company(schoolid,companyname,status,score,boss,url,infos,contact)";
    					insertstr +=" value(" + num + ",'" + list.get(0).getName() + "','" + list.get(0).getStatus() + "','" + list.get(0).getScore() + "','";
    					insertstr +=list.get(0).getBoss()  + "','" + list.get(0).getUrl() + "','" +list.get(0).getInfos()+ "','" +list.get(0).getContact() + "')";
    					MysqlHelper.executeUpdate(connection2, insertstr);
    				}
    			}
    			connection2.close();
    		}
		}
	}
}
