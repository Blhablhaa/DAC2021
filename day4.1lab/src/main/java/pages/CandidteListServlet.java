package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import dao.CandidateDaoImpl;
import dao.ICandidateDao;
import pojo.Candidate;
import pojo.Voter;

/**
 * Servlet implementation class CandidteListServlet
 */
@WebServlet("/candidateList")
public class CandidteListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ICandidateDao candidateDao;
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		try {
    		candidateDao = new CandidateDaoImpl();
		} catch (SQLException | ClassNotFoundException e) {
			throw new ServletException("Error in init() of " + getClass().getName());
		}	
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		try(PrintWriter pw = response.getWriter()) {
			HttpSession session = request.getSession();
			List<Candidate> candidates = candidateDao.listCandidates();
			Voter v = (Voter)session.getAttribute("voter_details");
			if (candidates !=null) {
				pw.print("<h5> Hello, " + v.getName() + ". Please vote here.</h5>");
				pw.print("<form action='status'>");
				pw.print("<table border=1>");
				pw.print("<thead>");
				pw.print("<th>Select</th>");
				pw.print("<th>Name</th>");
				pw.print("<th>Party</th>");
				pw.print("</thead>");
				pw.print("<tbody>");
				candidates.forEach(c->{
					pw.print("<tr>");
					pw.print("<td><input type='radio' name='candidate' value="+c.getId()+"></td>");
					pw.print("<td>"+c.getName()+"</td>");
					pw.print("<td>"+c.getParty()+"</td>");
					pw.print("</tr>");
				});
				pw.print("</tbody>");
				pw.print("</table>");
				pw.print("<input type='submit' value='Submit'>");
				pw.print("</form>");
			} else
				pw.print("Candidates list is empty!");
		} catch (SQLException e) {
			throw new ServletException("Error in doGet() of candidateList");
		}
	}

}
