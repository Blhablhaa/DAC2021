package tester;
import static utils.DBUtils.fetchConnection;
import java.sql.*;
import java.util.Scanner;

public class UserLogin {

	public static void main(String[] args) {
		String sql = "select * from customers where email=? and password=?"; 
		try (Scanner sc=new java.util.Scanner(System.in);
				Connection cn = fetchConnection();
				PreparedStatement pst=cn.prepareStatement(sql);
				)
				{
			System.out.println("Enter the EmailID and valid password");
			pst.setString(1, sc.next());
			pst.setString(2, sc.next());
			try(ResultSet rst=pst.executeQuery())
			{
				if(rst.next())
				{
					System.out.println("Your ID password is right, Now You Login");
				    System.out.printf("Id %d, Name %s, Register amount %d, reg Date %s,Role %s",
				    		                    rst.getInt(1),rst.getString(2), rst.getInt(5),rst.getDate(6),rst.getString(7));
				}
				 else
				    	System.out.println("Your Id Pass is Wrong,Please enter the Right ID password");
			}
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}

	}

}
