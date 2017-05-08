package by.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionManager {
	
	//普通加载
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://ip:port/dbName",
					"userName", "passWord");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	/**
     * DButils获取数据库连接
     * @return
     */
//    public Connection getConnection4() {
//        Connection conn = null;
//        String jdbcURL ="jdbc:mysql://localhost/dbname";
//        String jdbcDriver ="com.mysql.jdbc.Driver";
//        String user = "root";
//        String password = "root";
//        try {
//           DbUtils.loadDriver(jdbcDriver);
//            conn =DriverManager.getConnection(jdbcURL, user, password);
//        } catch (SQLException e) {
//            // handle the exception
//            e.printStackTrace();
//        } finally {
//            DbUtils.closeQuietly(conn);
//        }
//        return conn;
//    }
    
    @SuppressWarnings("unused")
	private static DataSource dataSource3;
	
	//c3p0数据源获取Connection
//	public static ComboPooledDataSource dataSource;
//	static {
//		try {
//			dataSource = new ComboPooledDataSource();
//			dataSource.setUser("userName");
//			dataSource.setPassword("passWord");
//			dataSource.setJdbcUrl("jdbc:mysql://ip:port/dbName");
//			dataSource.setDriverClass("com.mysql.jdbc.Driver");
//			dataSource.setInitialPoolSize(10);
//			dataSource.setMinPoolSize(5);
//			dataSource.setMaxPoolSize(50);
//			dataSource.setMaxStatements(100);
//			dataSource.setMaxIdleTime(60);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * 从连接池中获取数据源链接
	 * @author gaoxianglong
	 * @return Connection 数据源链接
	 */
//	public static Connection getConnection2() {
//		Connection conn = null;
//		if (null != dataSource) {
//			try {
//				conn = dataSource.getConnection();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		return conn;
//	}
	
	//dbcp数据源获取Connection
	private static Properties properties;
	private static DataSource dataSource2;
//	static {
//		try {
//			properties = new Properties();
//			properties.load(ConnectionManager.class.getResourceAsStream("/dbcpconfig.properties"));
//			BasicDataSourceFactory b = new BasicDataSourceFactory();
//			dataSource2 = b.createDataSource(properties);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	public static DataSource getdataSource() {
		return dataSource2;
	}
	
	//jndi
    public Connection getConnection5() throws NamingException{
    	String jndi = "MyPool";
        Context initContext=new InitialContext();
    	Context tContext = (Context) initContext.lookup("java:comp/env");
    	DataSource ds = (DataSource) tContext.lookup(jndi);
//		con = ds.getConnection();
		return null;
//	 //如果在web.xml中声明了引用对象，则采用下面的方法
//	 // DataSource tDataSource = (DataSource) tContext.lookup(
//	 // "java:comp/env/" + JUrl.getDBName());
//	 DataSource tDataSource = (DataSource) tContext.lookup(
//	 "java:comp/env/jdbc/aams");
    }
}
