package com.by.jdbc.dbutils.handler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.by.jdbc.dbutils.bean.Account;


//很笨的实现类， beanListHandler<Account>(Account.class)可以很优雅的实现

public class AccountListHandler implements org.apache.commons.dbutils.ResultSetHandler<List<Account>> {
	@Override
	public List<Account> handle(ResultSet rs) throws SQLException {

		List<Account> result = new ArrayList<Account>();
		while (rs.next()) {
			Account student = new Account();
			student.setId(rs.getString("userId"));
			student.setMoney(rs.getInt("userName"));
			result.add(student);
		}
		return result;
	}

	public static void Test(DataSource ds, String sql) {
		org.apache.commons.dbutils.QueryRunner run = new org.apache.commons.dbutils.QueryRunner(ds);
		List<Account> result = null;
		try {
			result = run.query(sql, new AccountListHandler());
		} catch (SQLException e) {

		}
		if (null == result) {
			return;
		}
		for (Account account : result) {
			System.out.println(account);
		}
	}
}
