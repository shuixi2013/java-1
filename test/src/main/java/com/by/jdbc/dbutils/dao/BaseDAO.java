package by.jdbc.dbutils.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;


public class BaseDAO {
	/**
	 * 获取数据库连接
	 * 
	 * @return
	 */
	public Connection getConnection() {
		Connection conn = null;
		String jdbcURL = "jdbc:mysql://localhost/dbname";
		String jdbcDriver = "com.mysql.jdbc.Driver";
		String user = "root";
		String password = "root";
		try {
			DbUtils.loadDriver(jdbcDriver);
			conn = DriverManager.getConnection(jdbcURL, user, password);
		} catch (SQLException e) {
			// handle the exception
			e.printStackTrace();
		} finally {
			DbUtils.closeQuietly(conn);
		}
		return conn;
	}

	/**
	 * 查找多个对象
	 * 
	 * @param sqlString
	 * @param clazz
	 * @return
	 */
	public List query(String sqlString, Class clazz) {
		List beans = null;
		Connection conn = null;
		try {
			conn = getConnection();
			QueryRunner qRunner = new QueryRunner();
			beans = (List) qRunner.query(conn, sqlString, new BeanListHandler(
					clazz));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeQuietly(conn);
		}
		return beans;
	}

	/**
	 * 查找对象
	 * 
	 * @param sqlString
	 * @param clazz
	 * @return
	 */
	public Object get(String sqlString, Class clazz) {
		List beans = null;
		Object obj = null;
		Connection conn = null;
		try {
			conn = getConnection();
			QueryRunner qRunner = new QueryRunner();
			beans = (List) qRunner.query(conn, sqlString, new BeanListHandler(
					clazz));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeQuietly(conn);
		}
		if (beans != null && !beans.isEmpty()) { // 注意这里
			obj = beans.get(0);

		}

		return obj;
	}

	/**
	 * 执行更新的sql语句,插入,修改,删除
	 * 
	 * @param sqlString
	 * @return
	 */
	public boolean update(String sqlString) {
		Connection conn = null;
		boolean flag = false;
		try {
			conn = getConnection();
			QueryRunner qRunner = new QueryRunner();
			int i = qRunner.update(conn, sqlString);
			if (i > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeQuietly(conn);
		}
		return flag;
	}
}
