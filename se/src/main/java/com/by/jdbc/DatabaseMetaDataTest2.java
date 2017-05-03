package com.by.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseMetaDataTest2 {
	public static void main(String args[]) {
		DatabaseMetaDataTest2 mdip = new DatabaseMetaDataTest2();
		mdip.getMetaDataName("dim_customer", null);
	}

	public int getMetaDataName(String m_TableName, Connection m_Connection) {
		int colCount = 0;
		try {
			if (m_Connection == null) {
				Class.forName("nl.cwi.monetdb.jdbc.MonetDriver").newInstance();
				m_Connection = DriverManager
						.getConnection("jdbc:monetdb://localhost/demo?user=monetdb&password=monetdb");
			}
			DatabaseMetaData m_DBMetaData = m_Connection.getMetaData();
			ResultSet tableRet = m_DBMetaData.getTables(null, "%", m_TableName,
					new String[] { "TABLE" });
			while (tableRet.next())
				System.out.println("Table name is:"
						+ tableRet.getString("TABLE_NAME"));
			String columnName;
			String columnType;
			ResultSet colRet = m_DBMetaData.getColumns(null, "%", m_TableName,
					"%");
			while (colRet.next()) {
				columnName = colRet.getString("COLUMN_NAME");
				columnType = colRet.getString("TYPE_NAME");
				int datasize = colRet.getInt("COLUMN_SIZE");
				int digits = colRet.getInt("DECIMAL_DIGITS");
				int nullable = colRet.getInt("NULLABLE");
				String nullFlag;
				if (nullable == 1) {
					nullFlag = "Null";
				} else {
					nullFlag = "Not Null";
				}
				System.out.println(columnName + " " + columnType + "("
						+ datasize + "," + digits + ") " + nullFlag);
				colCount++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("The number of column is: " + colCount);
		return colCount;
	}
}
