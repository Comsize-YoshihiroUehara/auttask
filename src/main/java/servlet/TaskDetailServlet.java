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
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストのエンコーディング方式を指定
		request.setCharacterEncoding("UTF-8");
		
		TaskDetailDAO dao = new TaskDetailDAO();
		List<TaskListBean> taskSelected = null;
		TaskCommentsBean taskDetail = null;
		
		// セッションオブジェクトの取得
		HttpSession session = request.getSession();
		// 選択したタスク名のタスクIDを取得する
		int taskId = Integer.parseInt(request.getParameter("task_id"));
		
		try {
			// 選択したタスク名の一覧情報を取得する
			taskSelected = dao.selectSingleTask(taskId);
			// 選択したタスク名のコメント情報を取得する
			taskDetail = dao.selectTask(taskId);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		// タスクIDをセッションに設定
		session.setAttribute("task_id", taskId);
		// 該当するタスクIDの一覧情報をセッションに設定
		session.setAttribute("taskSelected", taskSelected);
		// 該当するタスクIDのコメント情報をセッションに設定
		session.setAttribute("taskDetail", taskDetail);
		// コメント閲覧画面に遷移
		RequestDispatcher rd = request.getRequestDispatcher("taskdetail.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
