package dao;

import static utils.DBUtils.fetchConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import pojo.Candidates;

public class CandidateImpl implements ICandidateDao {
	private Connection cn;
	private PreparedStatement pst1,pst2,pst3;
	
	public CandidateImpl() throws ClassNotFoundException,SQLException{
		cn=fetchConnection();
		
		pst1=cn.prepareStatement("insert into candidates  values(default,?,?,0");
		pst2=cn.prepareStatement("select * from candidates");
		pst3=cn.prepareStatement("update candidates set votes=votes+1 where id=?");
		
		System.out.println("CandidateDeoImpl constructor...");
	}
	@Override
	public String registerCandidate(String name, String party) throws SQLException {
		String msg="Registration failed...";
		//set IN parameter
		pst1.setString(1, name);
		pst1.setString(2, party);
		
		int count = pst1.executeUpdate();
		if(count == 1)
			msg = "Registration Successful...";
		return msg;
		
	}

	@Override
	public List<Candidates> displayCandidate() throws SQLException {
		ArrayList<Candidates> candiList = new ArrayList<>();
		
		try(ResultSet rst = pst2.executeQuery()){
			while(rst.next()) {
				candiList.add(new Candidates(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getInt(4)));
			}
		}catch(Exception e) {
				e.printStackTrace();
			}
		
		
		return candiList;
	}

	@Override
	public void incrementCount(int candidateId) throws SQLException {
		pst3.setInt(1, candidateId);
		
		int count = pst3.executeUpdate();
		if(count == 1)
			System.out.println("Vote added..");
		else
			System.out.println("Vote does not added...");
	}
    public void cleanUp() throws SQLException{
    	if(pst1 != null)
    		pst1.close();
    	if(pst2 != null)
    		pst2.close();
    	if(pst3 != null)
    		pst3.close();
    	
    	System.out.println("Candidate Dao clean...");
    }
}
