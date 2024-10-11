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
@WebServlet("/list/delete")
public class TaskDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.sendRedirect("../list");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//TODO サーブレット書き換え
		HttpSession session = request.getSession();
		UserBean userInfo = (UserBean) session.getAttribute("userInfo");
		
		TaskListBean taskList = new TaskListBean();
	
		TaskDeleteDAO dao = new TaskDeleteDAO();
		
		String url;
		//削除完了した件数
		int rowsAffected = 0;
		try {
			
			rowsAffected = dao.deleteTask((TaskListBean) (request.getAttribute("tasklist")));
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		//処理件数が0以上であれば成功画面、0以下であればエラー画面
		
		if (rowsAffected >= 0) {
			url = "taskdelete-sucess.jsp";
		} else {
			url = "error.jsp";
		}
		
		//セッションにタスクリストをセットする
		session.setAttribute("taskList",taskList);
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}
}
