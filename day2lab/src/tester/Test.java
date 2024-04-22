package tester;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import dao.CandidateImpl;
import dao.VoterDaoImplement;
import pojo.Candidates;
import pojo.Voter;

public class Test {

	public static void main(String[] args) throws ClassNotFoundException, SQLException{
		
		
		try(Scanner sc =new Scanner(System.in))
		{
			boolean exit=false;
			VoterDaoImplement dao = new VoterDaoImplement();
			CandidateImpl dao1 = new CandidateImpl();
			while(!exit) {
				try {
					System.out.println("Option \n1. Voter login \n2. Candidate registration \n3. List all candidates \n4. Cast a Vote \n100. Exit ");
					switch (sc.nextInt()) {
					case 1:
						System.out.println("Enter emailId and password ");
						Voter v=dao.authenticateVoter(sc.next(), sc.next());
						if(v == null)
							System.out.println("voter login Failed");
						System.out.println(v);
						break;

					case 2:
						
						 System.out.println("Enter candidate detail (name,party) ");
						 System.out.println(dao1.registerCandidate(sc.next(),sc.next()));
						break;
						
					case 3:
						
						System.out.println("Enter candidate details name,party");
						List<Candidates> candidatList = dao1.displayCandidate();
						candidatList.stream().forEach(System.out::println);
						break;
						
					case 4:
						System.out.println("Enter candidate id and voter id ");
						dao1.incrementCount(sc.nextInt());
						dao.updateVotingStatus(sc.nextInt());
						break;
						
					case 100:
						break;
					}
				}catch(Exception e) {
					e.getStackTrace();
				}
		}
	}catch(Exception e) {
		e.getStackTrace();
	}
 }
}
