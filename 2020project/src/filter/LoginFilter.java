package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class LoginFilter
 */
//@WebFilter( servletNames = { "FeedBackSearchServlet" } )
@WebFilter( servletNames = { "FeedBackSearchServlet" } )
public class LoginFilter implements Filter {

    /**
     * Default constructor.
     */
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		HttpSession session = ((HttpServletRequest)request).getSession(false);
		//String sessionCheck = (String)session.getAttribute("loginUser.nickname");

		//if ( sessionCheck == null ) {
//		if ( session == null ) {
//
//			String message = "この操作を実行するには、ログインが必要です";
//			request.setAttribute("message", message);
//
//			RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
//			rd.forward(request, response);

//		}


		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
