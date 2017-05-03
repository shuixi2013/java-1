/**
 * 对数据库操作的类
 * 对数据库的操作，就是crud
 * 调用存储过程
 */
package com.by.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SqlHelper {
	Connection ct = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	String driver = "com.microsoft.jdbc.sqlserver.SQLServerDriver";
	String url = "jdbc:sqlserver://127.0.0.1:1433;databaseName=myshop";
	String user = "sa";
	String passwd = "418218";

	public SqlHelper() {
		try {
			Class.forName(driver);
			ct = DriverManager.getConnection(url, user, passwd);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("驱动没有加载成功,原因是没有导入驱动！请检查");
		} catch (NullPointerException e) {
			e.printStackTrace();
			System.out.println("数据库服务没有开启，请打开数据库服务，再重试");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 关闭资源的方法
	public void close() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (ct != null) {
				ct.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ResultSet query(String sql, String[] paras) {
		try {
			ps = ct.prepareStatement(sql);
			for (int i = 0; i < paras.length; i++) {
				ps.setString(i + 1, paras[i]);
			}
			rs = ps.executeQuery();// 执行查询
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;// 返回结果集
	}
	// 增删改方法
	public boolean update(String sql, String[] paras) {
		boolean b = true;
		try {
			ps = ct.prepareStatement(sql);
			// 循环的对paras赋值，？赋值法
			for (int i = 0; i < paras.length; i++) {
				ps.setString(i + 1, paras[i]);
			}
			// 执行操作
			ps.execute();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("SqlHelper中增删改中出错啦，请检查！");
			b = false;
		}
		return b;
	}

}
