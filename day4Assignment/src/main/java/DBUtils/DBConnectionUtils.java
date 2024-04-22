package DBUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionUtils {
private static Connection cn=null;

public static Connection getConnection() throws SQLException
{
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		if(cn==null)
		{
			String url = "jdbc:mysql://localhost:3306/student?useSSL=false&allowPublicKeyRetrieval=true";
						
			 cn = DriverManager.getConnection(url, "root", "1111"); 
		}
		
	} catch (ClassNotFoundException e) {
		
		e.printStackTrace();
	}
	return cn;
}
//Add static method to close the DB connection
public static void closeConnection() throws SQLException
{
	if(cn!=null)
		cn.close();
	System.out.println("DB connection closed successfully");
}
}
