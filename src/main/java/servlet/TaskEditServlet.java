package servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.TaskEditDAO;
import model.entity.CategoryBean;
import model.entity.StatusBean;
import model.entity.UserBean;
import model.form.TaskEditForm;

/**
 * Servlet implementation class TaskEditServlet
 */
@WebServlet("/list/edit")
public class TaskEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * タスク一覧表示画面から遷移してきた時の処理
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*******************************************************************/
		//今後ログイン中のユーザごとに表示分けをするなら
		//セッション内のユーザ情報を判別して処理を分ける必要性がある
		/*******************************************************************/

		//リクエストを処理
		request.setCharacterEncoding("UTF-8");
		int taskId = Integer.parseInt(request.getParameter("task_id"));

		TaskEditDAO dao = new TaskEditDAO();
		TaskEditForm defaultForm = null; //編集フォーム画面のデフォルト入力内容格納用

		List<CategoryBean> categoryList = null;
		List<UserBean> userList = null;
		List<StatusBean> statusList = null;
		try {
			defaultForm = dao.selectTaskByTaskId(taskId);
			categoryList = dao.selectAllCategory();
			userList = dao.selectAllUser();
			statusList = dao.selectAllStatus();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException(e);

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);

		}

		//セッションにオブジェクトを設定
		HttpSession session = request.getSession();
		session.setAttribute("defaultForm", defaultForm);
		session.setAttribute("categoryList", categoryList);
		session.setAttribute("userList", userList);
		session.setAttribute("statusList", statusList);

		//フォワード
		String url = "taskeditform.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);

	}

	/**
	 * タスク編集フォーム画面から遷移してきた時の処理
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//リクエスト処理
		request.setCharacterEncoding("UTF-8");

		//UPDATE文を実行
		TaskEditDAO dao = new TaskEditDAO();
		int rowsAffected = 0; /* SQLで取得したレコード数を格納する変数 */
		try {
			TaskEditForm newTask = new TaskEditForm();

			Date date = Date.valueOf((String) request.getParameter("limit_date"));

			newTask.setCategoryId(Integer.parseInt(request.getParameter("task_id")));
			newTask.setTaskName(request.getParameter("task_name"));
			newTask.setCategoryId(Integer.parseInt(request.getParameter("category_id")));
			newTask.setLimitDate(date);
			newTask.setUserId(request.getParameter("user_id"));
			newTask.setStatusCode(request.getParameter("status_code"));
			newTask.setMemo(request.getParameter("memo"));

			rowsAffected = dao.updateTask(newTask);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException(e);

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);

		}

		//遷移先の分岐を設定
		String url = "taskedit-failed.jsp";
		if (rowsAffected > 0) {
			url = "taskedit-success.jsp";
		}

		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
