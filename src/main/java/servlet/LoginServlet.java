package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.UserDAO;
import model.entity.UserBean;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(name = "loginservlet", urlPatterns = { "/loginservlet" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//DAOの生成
		UserDAO dao = new UserDAO();
		
		UserBean bean = new UserBean();
		
		//変数名にリクエストで送られて来た物を入れる
		String UserId = request.getParameter("user_id");
		String PassWord = request.getParameter("password");
		
		try {
			bean = dao.login(UserId, PassWord);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		String url;
		
		
		
		HttpSession session = request.getSession();
		
		if (bean == null) {

			url = "login.jsp";
			
			session.invalidate();
			
		} else {

			session.setAttribute("user_name", bean.getUserName());

			url = "menu.jsp";


			
		}

		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);

	}
}
