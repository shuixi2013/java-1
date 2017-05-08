package by.jdbc.basic;


import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
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
public class JdbcRowSetTest
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

	public void update(String sql)throws Exception
	{
		// ��������
		Class.forName(driver);
//		try(
			// ��ȡ���ݿ�����
			Connection conn = DriverManager.getConnection(url 
				, user , pass);
			// ����JdbcRowSetImpl����
//			JdbcRowSet jdbcRs = new JdbcRowSetImpl(conn))    //��
//		{
//			// ����SQL��ѯ���
//			jdbcRs.setCommand(sql);
//			// ִ�в�ѯ
//			jdbcRs.execute();
//			jdbcRs.afterLast();
//			// ��ǰ���������
//			while (jdbcRs.previous())
//			{
//				System.out.println(jdbcRs.getString(1)
//					+ "\t" + jdbcRs.getString(2)
//					+ "\t" + jdbcRs.getString(3));
//				if (jdbcRs.getInt("student_id") == 3)
//				{
//					// �޸�ָ����¼��
//					jdbcRs.updateString("student_name", "�����");
//					jdbcRs.updateRow();
//				}
//			}
//		}
	}
	public static void main(String[] args)throws Exception
	{
		JdbcRowSetTest jt = new JdbcRowSetTest();
		jt.initParam("mysql.ini");
		jt.update("select * from student_table");
	}
}
