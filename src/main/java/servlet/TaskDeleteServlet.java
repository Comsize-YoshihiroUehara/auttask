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

import model.dao.TaskDeleteDAO;
import model.entity.TaskListBean;
import model.entity.UserBean;

/**
 * Servlet implementation class TaskDeleteServlet
 */
@WebServlet(name = "/list/delete", urlPatterns = { "/taskdeleteservlet" })
public class TaskDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TaskDeleteServlet() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		UserBean userInfo = (UserBean) session.getAttribute("userInfo");
		
		TaskListBean tasklist = new TaskListBean();
	
		TaskDeleteDAO dao = new TaskDeleteDAO();
		//削除件数
		int rowsAffected = 0;
		String url;

		try {
			
			rowsAffected = dao.deleteTask((TaskListBean) (request.getAttribute("tasklist")));
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		//処理件数が0以上であれば成功画面、0以下であればエラー画面
		if (rowsAffected >= 0) {
			url = "delete-success.jsp";
			
		} else {
			url = "error.jsp";
		}
		
		//セッションにタスクリストをセットする
		session.setAttribute("tasklist",tasklist);
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}
}
