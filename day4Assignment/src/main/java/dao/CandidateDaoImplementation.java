package dao;
import static DBUtils.DBConnectionUtils.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import pogo.Candidate;

public class CandidateDaoImplementation implements ICandidateDao{

	Connection cn;
	PreparedStatement ptr1,ptr2,ptr3,ptr4,ptr5,ptr6;
	public CandidateDaoImplementation() throws SQLException {
		cn=getConnection();
		ptr1=cn.prepareStatement("insert into candidates values(default,?,?,0)");
		ptr2=cn.prepareStatement("select * from candidates order by votes desc");
		ptr3=cn.prepareStatement("update candidates set votes=votes+1 where id=?");
		ptr4=cn.prepareStatement("select * from candidates where id=?");
		ptr5=cn.prepareStatement("select * from candidates order by votes desc limit 2");
		ptr6=cn.prepareStatement("select party,COUNT(votes) from candidates GROUP By party");
	}
	@Override
	public String authenticate(String name, String party) throws SQLException {
		ptr1.setString(1, name);
		ptr1.setString(2, party);
		
		int statusUpdate=ptr1.executeUpdate();
		
		if(statusUpdate==1)
		return "Candidate updated successfully";
		else
		return "Updation failed";
	}
	@Override
	public List<Candidate> listOfCandidates() throws SQLException {
		List<Candidate> list=new ArrayList<>();
		
		try(ResultSet res=ptr2.executeQuery()){
			while(res.next())
				list.add(new Candidate(res.getInt(1), res.getString(2), res.getString(3), res.getInt(4)));
		}
		return list;
	}
	@Override
	public void updateVote(int candidateID) throws SQLException {
		ptr3.setInt(1, candidateID);
		ptr3.executeUpdate();
	}
	public Candidate getCandidate(int id) throws SQLException {
		System.out.println("To get Candidate details");
		ptr4.setInt(1, id);
		System.out.println("Before ResultSet");
		try(ResultSet res=ptr4.executeQuery()){
			
			System.out.println("executed properly");
			/*
			 * if(res!=null) { return new Candidate(res.getInt(1), res.getString(2),
			 * res.getString(3), res.getInt(4)); }
			 */
			if(res.next())
				return new Candidate(res.getInt(1), res.getString(2),res.getString(3), res.getInt(4));
			return null;
		}
	}
	
	public List<Candidate> topCandidate() throws SQLException
	{
		List<Candidate> list=new ArrayList<>();
		try(ResultSet res=ptr5.executeQuery()){
			while(res.next())
				list.add(new Candidate(res.getInt(1), res.getString(2),res.getString(3), res.getInt(4)));
			return list;
		}
	}
	
	public LinkedHashMap<String,Integer> candidatesMap() throws SQLException
	{
		List<Candidate> list=this.listOfCandidates();
		LinkedHashMap<String,Integer> map=new LinkedHashMap();
	//	try(ResultSet res=ptr6.executeQuery())
		for(Candidate c:list)
			map.put(c.getParty(), c.getVotes());
		
		return map;
		
	}
	
	void registerCandidate(Candidate c) {
		
	}

}
