package com.nihao.pachong;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

public class MysqlConnPool {
	public static final String url = "jdbc:mysql://localhost:3306/pachong";
    public static final String username = "root";
    public static final String password = "123456";
 
    private static final MysqlConnPool instance = new MysqlConnPool();
    private static ComboPooledDataSource comboPooledDataSource;
 
    static {
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            comboPooledDataSource = new ComboPooledDataSource();
            comboPooledDataSource.setDriverClass("com.mysql.jdbc.Driver");
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
    private MysqlConnPool() {
    }
 
    public static MysqlConnPool getInstance() {
        return instance;
    }

}
