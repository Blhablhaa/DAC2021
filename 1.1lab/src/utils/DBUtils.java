package utils;

import java.sql.*;

import java.util.Scanner;

public class DBUtils {
//singleton instance of DB connection
	private static Connection cn;

	public static Connection fetchConnection() throws ClassNotFoundException, SQLException {
		if (cn == null) {
			// class loading of JDBC driver :optional currently in Java SE
			Class.forName("com.mysql.cj.jdbc.Driver");
			// get fixed DB connection
			// test : DB name
			String url = "jdbc:mysql://localhost:3306/student?useSSL=false&allowPublicKeyRetrieval=true";
//url userName password
			cn = DriverManager.getConnection(url, "root", "1111");
		}
		return cn;

	}
}
