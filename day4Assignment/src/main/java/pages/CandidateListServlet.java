package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CandidateDaoImplementation;
import pogo.Candidate;
import pogo.Voter;

/**
 * Servlet implementation class CandidateListServlet
 */
@WebServlet(urlPatterns="/candidateList",loadOnStartup = 1)
public class CandidateListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CandidateDaoImplementation candDao;
	public void init() throws ServletException {
		try {
			candDao=new CandidateDaoImplementation();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new ServletException("error in init ",e);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		try(PrintWriter pw=response.getWriter()){
		HttpSession hs=request.getSession();
		Voter v=(Voter) hs.getAttribute("voter_details");
		/*
		 * HttpSession session=request.getSession(); session.setAttribute("votername",
		 * v);
		 */
		hs.setAttribute("candidateDaoInstance", candDao);
		
			List<Candidate> list=candDao.listOfCandidates();
			pw.print("<div style='text-align:center'>");
			pw.print("<h1>Hello "+v.getName()+"</h1><br>");
			pw.print("<h3>Candidates</h3> <br>");
			
			pw.print("<form action='ShowStatus' method='post'>");
			pw.print("<table style=\"width:200px; border: 1px solid black;margin:auto\">");

			for(Candidate c1:list)
			{
				 pw.print("<tr><td>"+c1.getName()+"</td><td><input type='radio' name='votedCandidate' value='"+c1.getId()+"' /></td></tr>");
			}
			pw.print(" <tr><td><input type='submit' value='Vote' /></td></tr>");
			pw.print(" </table></form>");
			pw.print("</div>");
		}catch(Exception e)
		{
			throw new ServletException("error to display list",e);
		}
	}

}
