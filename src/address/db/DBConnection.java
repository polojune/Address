package address.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	

	public static Connection getConnection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","cos","bitc5600");
			return conn;
		} catch (Exception e) {
			System.out.println("DB���� ����:" + e.getMessage());
		}
		return null;
	}
}
