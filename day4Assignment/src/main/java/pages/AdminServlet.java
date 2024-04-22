package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CandidateDaoImplementation;
import pogo.Candidate;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet(urlPatterns="/adminServlet",loadOnStartup = 1)
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		try(PrintWriter pw=response.getWriter()) {
		HttpSession hs=request.getSession();
		CandidateDaoImplementation cand=(CandidateDaoImplementation) hs.getAttribute("candDaoForAdmin");
		
			List<Candidate> list=cand.topCandidate();
			pw.print("Top 2 member in the list<br>");
			for(Candidate c:list)
				pw.print("Candidate name :"+c.getName()+" Party :"+c.getParty()+"<br>");
			
		
			LinkedHashMap<String,Integer> map=cand.candidatesMap();
			pw.print("<br><br>Party wise analysis :<br>");
			for(Map.Entry<String,Integer> m:map.entrySet())
				pw.print("Party name :"+m.getKey()+" Total votes :"+m.getValue()+"<br>");
			hs.invalidate();
			pw.print("<h3><a href='login.html'>go to login page</a></h3>");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
