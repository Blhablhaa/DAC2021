package tester;
import static utils.DBUtils.fetchConnection;
import java.sql.*;
import java.util.Scanner;
public class UserRegistration {

	public static void main(String[] args) {
		String sql = "insert into customers values(default,?,?,?,?,?,?)";
		try(Scanner sc = new Scanner(System.in);
				Connection cn = fetchConnection();
				PreparedStatement pst=cn.prepareStatement(sql); )
		{
			System.out.println("Enter the Details for the Customer name, email, password, registertion amount, registration date, role ");
		    
			pst.setString(1, sc.next());
			pst.setString(2, sc.next());
			pst.setString(3, sc.next());
			pst.setDouble(4, sc.nextDouble());
			pst.setDate(5,Date.valueOf(sc.next()));
			pst.setString(6, sc.next());
			
			int exec = pst.executeUpdate();
			if(exec == 0)
				System.out.println("Please enter Data in Correct manner, Can't accept");
			else
				System.out.println("Data Inserted Successfully");
			
		}
     catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}

	}

}
//shubham  shu@gmail.com 12345 1200 2018-2-12- hr