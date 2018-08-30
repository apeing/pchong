package com.nihao.pachong.oracle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;

public class OracleUtils {
	
	public static int Update(String sql) throws SQLException {
		int resCount = 0;
		Connection connection = OracleConnPool.getConnection();
		if (StringUtils.isBlank(sql)) {
			  //          System.out.println("sql语句不能为空");
			            return resCount;
      	}
        PreparedStatement ps = null;
  //    System.out.println("sql--> " + sql);
        try {
            ps = connection.prepareStatement(sql);
            resCount = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	connection.close();
        }
		return resCount;
	}
	
	public static ResultSet Query(String sql) throws SQLException{
		if (StringUtils.isBlank(sql)) {
			  //          System.out.println("sql语句不为空");
			return null;
		}
		ResultSet rs = null;
        PreparedStatement ps = null;
        Connection connection = OracleConnPool.getConnection();
  //      System.out.println("sql--> " + sql);
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
        	connection.close();
        }
        return rs;
    }
}
