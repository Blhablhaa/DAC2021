package dao;
import static DBUtils.DBConnectionUtils.*;
import java.sql.*;

import pogo.Voter;

public class VoterDaoImplementation implements IVoterDao{

	Connection cn;
	PreparedStatement ptr1,ptr2;
	public VoterDaoImplementation() throws SQLException {
		cn=getConnection();
		ptr1=cn.prepareStatement("select * from voters where email=? and password=?");
		ptr2=cn.prepareStatement("update voters set status=1 where id=?");
	}
	@Override
	public Voter authentication(String email, String password) throws SQLException {
		ptr1.setString(1, email);
		ptr1.setString(2, password);
		
		try(ResultSet res=ptr1.executeQuery()){
			if(res.next())
			{
				Voter v=new Voter(res.getInt(1),res.getString(2),res.getString(3),res.getString(4),res.getBoolean(5),res.getString(6));
				return v;
			}else {
				return null;
			}
		}catch(Exception e) {}
		return null;
	}
public void cleanUp() throws SQLException
{
	if(ptr1!=null)
		ptr1.close();
	if(cn!=null)
		cn.close();
}
@Override
public void changeStatus(int voterID) throws SQLException {
	ptr2.setInt(1, voterID);
	
	int checkstatus=ptr2.executeUpdate();
}
}
