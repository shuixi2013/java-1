package work.productDefinition.utils;

import db.senior.MetaDataInfoProvider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.*;

public class Csv2DB extends Thread {
	private static final String user = "monetdb";
	private static final String pwd = "monetdb";
	private static final String url = "jdbc:monetdb://localhost/demo";
	private static final String driver = "nl.cwi.monetdb.jdbc.MonetDriver";
	private static String DELIMITERS = ",";

	public static String getDELIMITERS() {
		return DELIMITERS;
	}

	public static void setDELIMITERS(String delimiters) {
		DELIMITERS = delimiters;
	}

	public static Connection getCon() {
		Connection con = null;

		try {
			Class.forName(driver).newInstance();
			con = DriverManager.getConnection(url, user, pwd);
			if (con != null) {
				System.out.println("你已连接到数据库：" + con.getCatalog());
			}
		} catch (Exception e) {
			System.out.println("连接数据库失败！");
			e.printStackTrace();
		}
		return con;
	}

	public boolean insertDB(String tablename, long rc) {
		if (rc < 1) {
			rc = 100;
		}
		Connection con = null;
		Statement stm = null;
		boolean flag = false;
		Statement pre;

		String sql = "";
		MetaDataInfoProvider mdip = new MetaDataInfoProvider();
		try {
			con = getCon();
			stm = con.createStatement();
			pre = con.createStatement();
			int colCount = mdip.getMetaDataName(tablename, con);
			int rowCount = 0;
			File raf = new File("f:/dim_customer_new.csv");
			BufferedReader buf = null;
			buf = new BufferedReader(new InputStreamReader(new FileInputStream(
					raf)));
			// FileWriter fw = new FileWriter("f:/dim_customer_new.sql", true);
			// //以文件方式输出
			// BufferedWriter bw = new BufferedWriter(fw);
			String line_record = buf.readLine();

			long sqlstart = System.currentTimeMillis(); // 开始计时

			while (line_record != null) {
				// 解析每一条记录
				sql = "insert into " + tablename + " values('";

				String[] fields = line_record.split(DELIMITERS);

				// 对Insert语句的合法性进行判断

				if (fields.length != colCount) {
					System.out.println("要插入的数据列数和表的数据列不相匹配，停止执行");
					break;
				}

				for (int i = 0; i < fields.length; i++) {
					sql += fields[i];
					if (i < fields.length - 1) {
						sql += "','";
					}
				}

				sql += "');";

				// 执行SQL语句
				// stm.executeUpdate(sql); //直接执行效率比较低
				pre.addBatch(sql);

				rowCount++;
				line_record = buf.readLine();
				if (rowCount >= rc)
					break;
			}
			pre.executeBatch();
			pre.close();
			// bw.flush(); // 将数据更新至文件
			// bw.close();
			// fw.close();

			// bw.close();
			// fw.close();

			System.out.println("共写入行数：" + rowCount);

			long sqlend = System.currentTimeMillis(); // 停止计时

			System.out.println("执行时间为:" + (sqlend - sqlstart) + " ms");
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		} finally {
			close(null, stm, con);
		}
		return flag;
	}

	// 关闭相关连接
	public void close(ResultSet rs, Statement stm, Connection con) {
		if (rs != null)
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		if (stm != null)
			try {
				stm.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		if (con != null)
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	public void run() {
		this.insertDB("dim_customer_batch", 500000);
	}

	public static void main(String[] args) {
		Csv2DB insertDB1 = new Csv2DB();
		insertDB1.start();
		Csv2DB insertDB2 = new Csv2DB();
		insertDB2.start();
	}
	
	public void cvs2DbDemo(){
		String filePath = "D:\\bbb.csv";
        BufferedReader bufferedReader = null;
        Connection  conn = null;
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/test";
        try {
            Class.forName(driver);
             conn =  DriverManager.getConnection(url, "root", "123456");
     
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                String[] columns = line.split(",");
                if(columns[0].trim().equalsIgnoreCase("序号")){
                    continue;
                }
                 PreparedStatement pstmt = conn.prepareStatement("insert into test(xh,kaohao,name,sex,sfz,zy,xf)values(?,?,?,?,?,?,?)");
                 pstmt.setString(1, columns[0]);
                 pstmt.setString(2, columns[1]);
                 pstmt.setString(3, columns[2]);
                 pstmt.setString(4, columns[3]);
                 pstmt.setString(5, columns[4]);
                 pstmt.setString(6, columns[5]);
                 pstmt.setString(7, columns[6]);
                 pstmt.executeUpdate();
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            try {
                conn.close();
                bufferedReader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
	}
}
