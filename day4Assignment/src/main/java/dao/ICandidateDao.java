package dao;

import java.sql.*;
import java.util.*;

import pogo.Candidate;

public interface ICandidateDao {
String authenticate(String name,String party)throws SQLException;
List<Candidate> listOfCandidates()throws SQLException;
void updateVote(int candidateID)throws SQLException;
}
