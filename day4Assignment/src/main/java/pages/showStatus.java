package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CandidateDaoImplementation;
import dao.VoterDaoImplementation;
import pogo.Candidate;
import pogo.Voter;

/**
 * Servlet implementation class showStatus
 */
@WebServlet(urlPatterns="/ShowStatus",loadOnStartup = 1)
public class showStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		try(PrintWriter pw=response.getWriter()){
		HttpSession session=request.getSession();
		Voter voter=(Voter) session.getAttribute("voter_details");
		int  candidateID=Integer.parseInt(request.getParameter("votedCandidate"));
		CandidateDaoImplementation canDao=(CandidateDaoImplementation) session.getAttribute("candidateDaoInstance");
		
			
			System.out.println("Out get candidate details");
			canDao.updateVote(candidateID);
			Candidate candidate=canDao.getCandidate(candidateID);
		VoterDaoImplementation voterDao=(VoterDaoImplementation) session.getAttribute("voterDaoInstance");
		
			voterDao.changeStatus(voter.getId());
		
		
		
		
			pw.print("Hello "+voter.getName()+"<br>");
			pw.print("You have voted successsfully to Candidate :<br>");
			pw.print(candidate.toString());
			
			session.invalidate();
			pw.print("<h3><a href='login.html'>go to login page</a></h3>");
	}catch(Exception e) {
	throw new ServletException("Exception in status",e);
	}
	}

}
