package by.jdbc.basic;


import java.util.*;
import java.io.*;
import java.sql.*;
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
public class ResultSetTest
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
	public void query(String sql)throws Exception
	{
		// ��������
		Class.forName(driver);
		try(
			// ��ȡ���ݿ�����
			Connection conn = DriverManager.getConnection(url
				, user , pass);
			// ʹ��Connection������һ��PreparedStatement����
			// ������ƽ�����ɹ������ɸ��µĲ�����
			PreparedStatement pstmt = conn.prepareStatement(sql 
				, ResultSet.TYPE_SCROLL_INSENSITIVE
				, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = pstmt.executeQuery())
		{
			rs.last();
			int rowCount = rs.getRow();
			for (int i = rowCount; i > 0 ; i-- )
			{
				rs.absolute(i);
				System.out.println(rs.getString(1) + "\t"
					+ rs.getString(2) + "\t" + rs.getString(3));
				// �޸ļ�¼ָ�����м�¼����2�е�ֵ
				rs.updateString(2 , "ѧ����" + i);
				// �ύ�޸�
				rs.updateRow();
			}
		}
	}
	public static void main(String[] args) throws Exception
	{
		ResultSetTest rt = new ResultSetTest();
		rt.initParam("mysql.ini");
		rt.query("select * from student_table");
	}
}
