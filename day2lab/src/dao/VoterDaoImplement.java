package dao;

import static utils.DBUtils.fetchConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import pojo.Voter;

public class VoterDaoImplement implements IVoterDao {
 //state
	private Connection cn;
	private PreparedStatement pst,pst1;
	//const
	public VoterDaoImplement() throws ClassNotFoundException,SQLException{
		
		//get cn from DBUtils
		cn=fetchConnection();
		
		pst=cn.prepareStatement("select * from voters where email=? and password=?");
		pst1=cn.prepareCall("update voters set status=1 where id=?");
		System.out.println("Voterdao create...");
	}
	@Override
	public Voter authenticateVoter(String email, String password) throws SQLException{
		String mesg="Invalid EmailId and pass";
		pst.setString(1, email);
		pst.setString(2, password);
		
		try(ResultSet rst = pst.executeQuery()){
		
		if(rst.next())
		       return new Voter(rst.getInt(1),rst.getString(2),email,password,rst.getBoolean(5),rst.getString(6));
		else
		return null;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;	
	}
	public void updateVotingStatus(int id) throws SQLException{
		pst1.setInt(1, id);
		
		int count = pst1.executeUpdate();
		
		if(count == 1)
			System.out.println("status updated...");
		else
			System.out.println("Status doesnot updated...");
	}
    
	 public void cleanUp() throws SQLException{
	    	if(pst != null)
	    		pst.close();
	    	if(pst1 != null)
	    		pst1.close();
	    	
	    	
	    	System.out.println("Voter Dao clean...");
	    }
}
