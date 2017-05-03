package com.by.jdbc.dbutils.service;

import java.sql.SQLException;

import com.by.jdbc.dbutils.JdbcUtils;
import com.by.jdbc.dbutils.bean.Account;
import com.by.jdbc.dbutils.dao.AccountDao;

public class AccountService {
    // ThreadLocal 解决方案
    public void transfer(int sourceid,int destid,double money) throws SQLException {
        try {
            JdbcUtils.startTransaction();
            AccountDao dao = new AccountDao();
            Account a = dao.find(sourceid); //select
            Account b = dao.find(destid); //select
            a.setMoney(a.getMoney()-100);
            b.setMoney(b.getMoney()+100);
            dao.update(a); //update
            dao.update(b); //update
            JdbcUtils.commit();            
        } catch (Exception e) {
            JdbcUtils.rollback();
            throw new RuntimeException();
        } finally {
            JdbcUtils.close();
        }
    }
}