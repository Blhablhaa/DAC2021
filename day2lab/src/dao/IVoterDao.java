package dao;

import java.sql.SQLException;

import pojo.Voter;

public interface IVoterDao {
   Voter authenticateVoter(String email,String password) throws SQLException;
   
   void updateVotingStatus(int voterId) throws SQLException;
   
}
