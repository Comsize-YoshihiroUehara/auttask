package filter;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.dao.UserDAO;
import model.entity.UserBean;

/**
 * Servlet Filter implementation class Filter
 */
@WebFilter("/*")
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

		String userId = request.getParameter("user_id");
		String password = request.getParameter("password");
		UserDAO dao = new UserDAO();
		UserBean bean = null;
		HttpSession session = ((HttpServletRequest) request).getSession();
		UserBean userInfo = (UserBean) session.getAttribute("userInfo");

		try {
			bean = dao.login(userId, password);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		if (userInfo != null) {
			//nullでなければ通常の遷移をする
			chain.doFilter(request, response);
		} else {
			//userInfoがnullであればログインページに飛ぶ
			RequestDispatcher rd = request.getRequestDispatcher("login");
			rd.forward(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
