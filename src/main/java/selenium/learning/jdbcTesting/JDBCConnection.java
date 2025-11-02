package selenium.learning.jdbcTesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCConnection {

	public static void main(String[] args) {
		String databaseURL = "jdbc:oracle:thin:@//localhost:1521/orclpdb";
		String username = "hr";
		String password = "hr";
		try {
			Connection connection =DriverManager.getConnection(databaseURL, username, password);
			System.out.println("Connected to Oracle Database!");
			Statement statement = connection.createStatement();
			String query="select * from employees where employee_id=100";
			ResultSet result = statement.executeQuery(query);
			while(result.next()) {
				System.out.println(result.getString("employee_id"));
				System.out.println(result.getString("first_name"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
