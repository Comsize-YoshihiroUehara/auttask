package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.TaskListDAO;
import model.entity.TaskBean;
import model.entity.UserBean;

/**
 * Servlet implementation class TaskListServlet
 */
@WebServlet("/list")
public class TaskListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TaskListServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//セッション情報を取得
		HttpSession session = request.getSession();
		UserBean userInfo = (UserBean) session.getAttribute("userInfo");

		//タスク一覧格納用の変数
		List<TaskBean> taskList = null;

		//データベースからタスク一覧を取得
		TaskListDAO dao = new TaskListDAO();
		try {
			taskList = dao.selectAllTask(userInfo);

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);

		}

		session.setAttribute("taskList", taskList);

		//フォワード処理
		String url = "tasklist.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);

		return; //処理終了
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
