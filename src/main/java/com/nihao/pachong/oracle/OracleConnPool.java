package com.nihao.pachong.oracle;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class OracleConnPool {
	public static final String url = "jdbc:oracle:thin:@192.168.1.190:1521:orcl";
    public static final String username = "c##schoolFees2";
    public static final String password = "SCHOOLFEES2";
    
    private static final OracleConnPool instance = new OracleConnPool();
    private static ComboPooledDataSource comboPooledDataSource;
    
    static {
        try {
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            comboPooledDataSource = new ComboPooledDataSource();
            comboPooledDataSource.setDriverClass("oracle.jdbc.driver.OracleDriver");
            comboPooledDataSource.setJdbcUrl(url);
            comboPooledDataSource.setUser(username);
            comboPooledDataSource.setPassword(password);
            //下面是设置连接池的一配置
            comboPooledDataSource.setMaxPoolSize(20);
            comboPooledDataSource.setMinPoolSize(5);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
    }
    @SuppressWarnings("finally")
	public synchronized static Connection getConnection() {
        Connection connection = null;
        try {
            connection = comboPooledDataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return connection;
        }
    }
    private OracleConnPool() {
    	
    }
    public static OracleConnPool getInstance() {
        return instance;
    }
}
