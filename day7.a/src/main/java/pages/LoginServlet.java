package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BookDaoImpl;
import dao.CustomerDaoImpl;
import pojos.Customer;
import utils.DBUtils;
import static utils.DBUtils.*;

/**
 * Servlet implementation class LoginServlet
 */

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CustomerDaoImpl customerDao;
	private BookDaoImpl bookDao;

	public LoginServlet() {
		System.out.println("in ctor of " + getClass().getName() + " config " + getServletConfig());
	}

	/**
	 * @see Servlet#init()
	 */
	public void init() throws ServletException {
		try {
			ServletConfig config=getServletConfig();
			System.out.println("in init of " + getClass().getName()+" config "+config);
//			//establish db conn
//			openConnection(config.getInitParameter("drvr"),config.getInitParameter("db_url"),
//					config.getInitParameter("user_name"),config.getInitParameter("pwd"));
			// instantiate dao ---
			customerDao = new CustomerDaoImpl();
			bookDao = new BookDaoImpl();
		} catch (Exception e) {
			// re throw the exception to WC , by wrapping it in ServletException instance
			// ServletException(String mesg,Throwable cause)
			// centralized exc handling in servlets
			throw new ServletException("err in init of " + getClass().getName(), e);

		}
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		System.out.println("in destroy of servlet"+getClass().getName());
		try {
			customerDao.cleanUp();
			bookDao.cleanUp();
	//		DBUtils.closeConnection();
		} catch (Exception e) {
			// System.out.println("err in destroy "+e);
			throw new RuntimeException("err in destory", e);// understanding purpose!!!!
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// set resp cont type
		response.setContentType("text/html");
		try (PrintWriter pw = response.getWriter()) {
			// read request params
			String email = request.getParameter("em");
			String password = request.getParameter("pass");
			// invoke dao's method for customer auth.
			Customer customer = customerDao.authenticateCustomer(email, password);
			if (customer == null) {
				pw.print("<h5>Invalid Login </h5>");
				pw.print("<h5>Please <a href='login.html'>Retry</a></h5>");
			} else {
				// get HttpSession object from WC
				HttpSession session = request.getSession();
				System.out.println("Imple class name " + session.getClass().getName());// imple cls name
				System.out.println("from 1st page HS is new " + session.isNew());// true
				System.out.println("session id " + session.getId());
				// add auth customer details under session
				session.setAttribute("customer_details", customer);
				// add customer dao instance under session scope
				session.setAttribute("customer_dao", customerDao);
				// add book dao instance under session scope
				session.setAttribute("book_dao", bookDao);
				// create an empty cart n add it under session scope
				session.setAttribute("shopping_cart", new ArrayList<Integer>());

				// redirect the clnt to the next page (CategoryServlet) in the NEXT request
				// API of HttpServletResponse :
				// public void sendRedirect(String urlPatternOfNextPage)
				response.sendRedirect("category");// Client Pull II
				// WC : sends temp redirect resp
				// SC 302 | location=category,set-cookie : JSESSIONID : dgafdgdf65764868 | body
				// EMPTY
				// clnt browser --> auto sends a NEW request
				// URL : http://host:port/day5.1/category
				// method=get
			}

		} catch (Exception e) {
			throw new ServletException("err in do-post of " + getClass().getName(), e);
		}
	}

}
