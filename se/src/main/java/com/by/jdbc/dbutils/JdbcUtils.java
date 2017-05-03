package com.by.jdbc.dbutils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

public class JdbcUtils {
    private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
    private static DataSource ds;
    
    static {
//        ds = new ComboPooledDataSource();
    }
    
    public static DataSource getDataSource() {
        return ds;
    }
    
    // 当前线程绑定一个开启事务的连接
    // ThreadLocal是一个map集合 具有get() set() remove()方法 key是线程名称
    public static Connection getConnection() throws SQLException {
        Connection conn = tl.get();
        if (conn == null) {
            conn = ds.getConnection();
            tl.set(conn);            
        }
        return conn;
    }
    
    public static void startTransaction() {
        try {
            Connection conn = getConnection(); //调用
            if(conn != null) {
                conn.setAutoCommit(false);
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
    
    public static void rollback() {
        try {
            Connection conn = getConnection(); //调用
            if(conn != null) {
                conn.rollback();
                conn.commit();
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
    
    public static void commit() {
        try {
            Connection conn = getConnection(); //调用
            if(conn != null) {
                conn.commit();
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
    
    public static void close() {
        try {
            Connection conn = getConnection(); //调用
            if(conn != null) {
                try {
                    conn.close();
                } finally {
                    tl.remove();
                }                
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}