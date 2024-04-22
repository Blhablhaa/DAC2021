package pages;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CandidateDaoImplementation;
import dao.VoterDaoImplementation;
import pogo.Voter;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(urlPatterns ="/authenticate",loadOnStartup = 1)
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private VoterDaoImplementation voterdao;
	private CandidateDaoImplementation candDao;

	/**
	 * @see Servlet#init()
	 */
	public void init() throws ServletException {
		try {
		System.out.println("in init of "+getClass().getName());
		//instantiate dao ---getConn ---establish db conn
		voterdao=new VoterDaoImplementation();
		candDao=new CandidateDaoImplementation();
		} catch (Exception e) {
			//re throw the exception to WC , by wrapping it in ServletException instance
			//ServletException(String mesg,Throwable cause)
			//centralized exc handling in servlets
			throw new ServletException("err in init of "+getClass().getName(),e);
	
		}
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		try {
			voterdao.cleanUp();
		} catch (Exception e) {
		//	System.out.println("err in destroy "+e);
			throw new RuntimeException("err in destory", e);//understanding purpose!!!!
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// set resp cont type
		response.setContentType("text/html");
		try(PrintWriter pw=response.getWriter())
		{
			//read request params
			String email=request.getParameter("em");
			String password=request.getParameter("pass");
			//invoke dao's method for customer auth.
			Voter voter = voterdao.authentication(email, password);
			if(voter == null || voter.isStatus()==true || voter.getRole().equals("admin")) {
				if(voter.isStatus()==true) {
					System.out.println("You have voted alredy");
					pw.print("You have voted already");
					pw.print("<h3><a href='login.html'>go to login page</a></h3>");
				}
				else if(voter==null){
				pw.print("<h5>Invalid Login </h5>");
				pw.print("<h5>Please <a href='login.html'>Retry</a></h5>");
				}else {
					HttpSession hs=request.getSession();
					hs.setAttribute("candDaoForAdmin", candDao);
					response.sendRedirect("adminServlet");
				}
			}
			else {
				HttpSession session=request.getSession();
				session.setAttribute("voter_details", voter);
				session.setAttribute("voterDaoInstance", voterdao);
				//redirect the clnt to the next page (CategoryServlet) in the NEXT request
				//API of HttpServletResponse :
				//public void sendRedirect(String urlPatternOfNextPage)
				response.sendRedirect("candidateList");//Client Pull II
				//WC : sends temp redirect resp
				//SC 302 | location=category,set-cookie : customer_details : customer's toString  | body EMPTY
				//clnt browser --> auto sends a NEW request
				//URL : http://host:port/day4.1/category
				//method=get
		}
			
		}catch (Exception e) {
			throw new ServletException("err in do-post of "+getClass().getName(),e);
		}
	}

}
