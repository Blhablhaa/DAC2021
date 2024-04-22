package dao;

import java.sql.SQLException;
import java.util.List;

import pojo.Candidates;

public interface ICandidateDao {
	String registerCandidate(String name, String party) throws SQLException;
	   
	   List<Candidates> displayCandidate()throws SQLException;
	   
	   void incrementCount(int candidateId) throws SQLException;
}
