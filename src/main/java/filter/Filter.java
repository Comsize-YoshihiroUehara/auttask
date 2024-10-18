package filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.entity.UserBean;

/**
 * Servlet Filter implementation class Filter
 */
@WebFilter({ "/menu", "/list/delete","/list","/logout.jsp","/taskregister","/register-success.jsp","/register-failure.jsp","/error.jsp"})
public class Filter extends HttpFilter implements javax.servlet.Filter {

	/**
	 * @see HttpFilter#HttpFilter()
	 */
	public Filter() {
		super();
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
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest)request).getSession();
		UserBean userInfo = (UserBean) session.getAttribute("userInfo");
		String url = "list";

		if (userInfo != null) {
			//nullでなければ通常の遷移をする
			chain.doFilter(request, response);
		} else {
			//userInfoがnullであればログインページに飛ぶ
			url = "login";
			response.setCharacterEncoding("UTF-8");
			((HttpServletResponse)response).sendRedirect(url);
		}
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
