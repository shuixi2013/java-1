package by.jdbc.basic;


import java.sql.*;
import java.io.*;
import java.util.*;
/**
 * Description:
 * <br/>��վ: <a href="http://www.crazyit.org">���Java����</a> 
 * <br/>Copyright (C), 2001-2012, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author Yeeku.H.Lee kongyeeku@163.com
 * @version 1.0
 */
public class TransactionTest
{
	private String driver;
	private String url;
	private String user;
	private String pass;
	public void initParam(String paramFile)throws Exception
	{
		// ʹ��Properties�������������ļ�
		Properties props = new Properties();
		props.load(new FileInputStream(paramFile));
		driver = props.getProperty("driver");
		url = props.getProperty("url");
		user = props.getProperty("user");
		pass = props.getProperty("pass");
	}
	public void insertInTransaction(String[] sqls) throws Exception
	{
		// ��������
		Class.forName(driver);
		try(
			Connection conn = DriverManager.getConnection(url
				, user , pass))
		{
			// �ر��Զ��ύ����������
			conn.setAutoCommit(false);
			try(
				// ʹ��Connection������һ��Statment����
				Statement stmt = conn.createStatement())
			{
				// ѭ�����ִ��SQL���
				for (String sql : sqls)
				{
					stmt.executeUpdate(sql);
				}
			}
			// �ύ����
			conn.commit();
		}
	}
	public static void main(String[] args) throws Exception
	{
		TransactionTest tt = new TransactionTest();
		tt.initParam("mysql.ini");
		String[] sqls = new String[]{
			"insert into student_table values(null , 'aaa' ,1)",
			"insert into student_table values(null , 'bbb' ,1)",
			"insert into student_table values(null , 'ccc' ,1)",
			// ��������SQL��佫��Υ�����Լ����
			// ��Ϊteacher_table��û��IDΪ5�ļ�¼��
			"insert into student_table values(null , 'ccc' ,5)" //��
		};
		tt.insertInTransaction(sqls);
	}
}
