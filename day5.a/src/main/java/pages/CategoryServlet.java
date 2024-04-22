package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BookDaoImpl;
import pojos.Customer;

/**
 * Servlet implementation class CategoryServlet
 */
@WebServlet("/category")
public class CategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// set cont type
		response.setContentType("text/html");
		try (PrintWriter pw = response.getWriter()) {
			pw.print("<h5>Successful Login </h5>");
			// get HttpSession from WC
			HttpSession hs = request.getSession();
			System.out.println("from 2nd page HS is new " + hs.isNew());// false
			System.out.println("session id"+hs.getId());//same session Id
			// how to get session data ?
			Customer c = (Customer) hs.getAttribute("customer_details");
			if (c != null)
			{
				pw.print("<h5>Hello," + c.getName() + "</h5>");
				//get book dao
				BookDaoImpl bookDao=(BookDaoImpl) hs.getAttribute("book_dao");
				//invoke dao
				List<String> categories=bookDao.fetchAllCategories();
				//dynamic class generate
				pw.print("<h5>");
				pw.print("<form action='category_details'>");
				//drop down list
				pw.print("<select name='chossen_category'>");
				for(String s : categories)
					pw.print("<option value="+s+">"+s+"</option>");
				
				pw.print("</select>");
				//submit button
				pw.print("<br><input type='submit' value='Chosse'></input></br>");
				pw.print("</form>");
				pw.print("</h5>");
			}
				else // => no cookies rcvd from the clnt browser
				pw.print("<h5> No Cookies : Session Tracking Failed!!!!!!!!!!!</h5>");

		}catch (Exception e) {
			throw new ServletException("err in do-get of"+getClass().getName(),e);
		}
	}

}
