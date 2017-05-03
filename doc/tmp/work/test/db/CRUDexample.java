package work.test.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CRUDexample {
	static GUISQL sql = null;
	private String username, password;
	private static String query;

	public CRUDexample() {

	}

	public CRUDexample(String inUsername, String inPassword) {
		username = inUsername;
		password = inPassword;
		createConnection(username, password);
	}

	public static void createConnection(String inUsername, String inPassword) {

		ConnectToDB db = new ConnectToDB(inUsername, inPassword);

		try (Connection con = db.getConnection()) {
			try (Statement stmt = con.createStatement()) {
				createQuery(con, stmt);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		//
		// try (Connection con = db.getConnection()) {
		// try (Statement stmt = con.createStatement()) {
		// create(con, stmt);
		// } catch (SQLException e) {
		// e.printStackTrace();
		// }
		// } catch (SQLException e1) {
		// e1.printStackTrace();
		// }

	}

	public static void createQuery(Connection con, Statement stmt) {
		sql = new GUISQL(true);
//		sql.disposeWindow();
//		try {
//			while (query != null) {
//				query = sql.getQuery();
//				stmt.executeUpdate(query);				
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}

		/*
		 * try { while (rs.next()) { System.out.printf("%-10s",
		 * rs.getString(1)); System.out.printf("%-10s%", rs.getString(2));
		 * System.out.printf("%-10s%", rs.getString(3));
		 * System.out.printf("%-10s%", rs.getString(4));
		 * System.out.printf("%-10s%", rs.getString(5)); } } catch (SQLException
		 * e) { e.printStackTrace(); } finally { if (rs != null) rs.close(); }
		 */

	}

}
