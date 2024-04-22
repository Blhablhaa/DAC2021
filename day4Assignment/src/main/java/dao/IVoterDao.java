package dao;

import java.sql.SQLException;

import pogo.Voter;

public interface IVoterDao {
Voter authentication(String email,String password)throws SQLException;
void changeStatus(int voterID) throws SQLException;
}
