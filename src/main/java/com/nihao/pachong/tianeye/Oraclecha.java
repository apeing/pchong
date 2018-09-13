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
		ResultSet rs = MysqlHelper.executeQuery(connection,"select ID,url,infos from company where ID<=1200 and ID >=1059");
		int num = 0;
		while(rs.next()) {
			String id = rs.getString(1);
			String url = rs.getString(2);
			String infos = rs.getString(3);
    		System.out.println(id + " : " + url);
    		if(infos != null & infos != ""){
    			num = Integer.parseInt(id);
    			Connection connection2 = MysqlConnPool.getInstance().getConnection();
    			Company obj = new Tianweb().runSecond(url);
    			
    				
    		//		System.out.println("rs2 run");
    				
    			//注册资金
    	//		int znum1 = infos.indexOf("注册资本：");
    	//		int znum2 = infos.indexOf("注册时间：");
    	//		System.out.println("znum2 : " + znum2);
    	//		System.out.println("znum1 : " + znum1);
    	//		String money =infos.substring(znum1+5,znum2);
    	//		System.out.println("money : " + money);
				String updatestr = "update company set gszch = '" + obj.getGszch() + "' , industry = '" + obj.getIndustry() + "' , zzjgdm = '" ;
				updatestr += obj.getZzjgdm() + "' , tyxydm = '" + obj.getTyxydm() + "' , nsrsbh = '" + obj.getNsrsbh()+ "' , address = '" +obj.getAddress() + "' where id = " + num;
    	//		String updatestr2 = "update company set zhijing = '" + money + "' where id=" + num;
				System.out.println(updatestr);
				MysqlHelper.executeUpdate(connection2, updatestr);
    				
    			
    			connection2.close();
    		}
		}
	}
}
