package by.jdbc.dbutils.dao;

import by.jdbc.dbutils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import by.jdbc.dbutils.bean.Account;

public class AccountDao {
	public Account find(int id) {
		try {
			QueryRunner qr = new QueryRunner();
			String sql = "select * from account where id=?";
			return  (Account) qr.query(JdbcUtils.getConnection(), sql,  new BeanHandler(Account.class),id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	public void update(Account a) {
		try {
			QueryRunner qr = new QueryRunner();
			String sql = "update account set money=? where id=?";
			Object[] params = { a.getMoney(), a.getId() };
			qr.update(JdbcUtils.getConnection(), sql, params);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
