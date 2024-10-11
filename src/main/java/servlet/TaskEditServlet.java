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

import model.dao.TaskEditDAO;
import model.form.TaskEditForm;

/**
 * Servlet implementation class TaskEditServlet
 */
@WebServlet("/list/edit")
public class TaskEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/* タスク一覧表示画面から遷移してきた時の処理 */
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//現状GETでタスクIDを指定しているが、ユーザごとに表示分けをするなら
		//セッション内のユーザ情報を判別して処理を分ける必要性がある
		
		//リクエストを処理
		request.setCharacterEncoding("UTF-8");
		int taskId = Integer.parseInt(request.getParameter("task_id"));

		//タスクIDを基にビーンを取得
		TaskEditDAO dao = new TaskEditDAO();
		TaskEditForm taskEditForm = null;
		try {
			taskEditForm = dao.selectTaskByTaskId(taskId);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException(e);

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);

		}

		//タスク編集画面へ遷移
		HttpSession session = request.getSession();
		session.setAttribute("defaultForm", taskEditForm);

	}

	/* タスク編集画面から遷移してきた時の処理 */
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//リクエスト処理
		request.setCharacterEncoding("UTF-8");
		
		/*
		request.getParameter("");
		 */
		
		//
		TaskEditForm taskEditForm = new TaskEditForm();

		//UPDATE文を実行
		TaskEditDAO dao = new TaskEditDAO();
		int rowsAffected = 0; /* SQLで取得したレコード数を格納する */
		try {
			rowsAffected = dao.updateTask(taskEditForm);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException(e);

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);

		}

		//編集完了画面とエラー画面に分岐
		String url = "taskedit-failed.jsp";
		if(rowsAffected > 0) {
			url = "taskedit-success.jsp";
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
