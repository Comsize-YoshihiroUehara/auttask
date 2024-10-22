package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.TaskDetailDAO;
import model.entity.TaskCommentsBean;
import model.entity.TaskListBean;

/**
 * Servlet implementation class TaskDetailServlet
 */
@WebServlet("/list/detail")
public class TaskDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TaskDetailServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// リクエストのエンコーディング方式を指定
		request.setCharacterEncoding("UTF-8");

		TaskListBean taskSelected = null;
		List<TaskCommentsBean> taskComments = null;

		// セッションオブジェクトの取得
		HttpSession session = request.getSession();
		int taskId = Integer.parseInt(request.getParameter("task_id"));

		TaskDetailDAO dao = new TaskDetailDAO();
		try {
			// 選択したタスク名の一覧情報を取得する
			taskSelected = dao.selectTaskByTaskId(taskId);
			// 選択したタスク名のコメント情報を取得する
			taskComments = dao.selectCommentsByTaskId(taskId);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		//セッションに情報を格納
		session.setAttribute("taskId", taskId);
		session.setAttribute("taskSelected", taskSelected);
		session.setAttribute("taskComments", taskComments);

		// コメント閲覧画面に遷移
		RequestDispatcher rd = request.getRequestDispatcher("taskdetail.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
