package listners;

import static utils.DBUtils.openConnection;

import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import utils.DBUtils;

/**
 * Application Lifecycle Listener implementation class DBConnectionMgr
 *
 */
@WebListener
public class DBConnectionMgr implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public DBConnectionMgr() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
       System.out.println("in ctx destroy");
    	try {
        	DBUtils.closeConnection();
		} catch (Exception e) {
			System.out.println("err in destroy "+e);
		}
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
    	System.out.println("in init ctx");
    
     //get servletContext from event object
    	ServletContext ctx = sce.getServletContext();
    	//open connection
    	try {
			openConnection(ctx.getInitParameter("drvr"),ctx.getInitParameter("db_url"),
					ctx.getInitParameter("user_name"),ctx.getInitParameter("pwd"));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("err in ctx-iit "+e);
    }
	
}
}
