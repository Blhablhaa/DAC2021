package filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class Filter1
 */
//@WebFilter("/*")
public class Filter2 implements Filter {

    

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		System.out.println("in destroy of "+getClass().getName());
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//pre-processing before servicing the req
		System.out.println("pre-processing done by "+getClass().getName());
		// pass the request along the filter chain
		chain.doFilter(request, response);
		//post process after servicing reg
		System.out.println("post-processing done by "+getClass().getName());
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("in init of "+getClass().getName());
	}

}
