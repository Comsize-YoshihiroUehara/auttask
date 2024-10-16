package servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
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
import utils.TaskUtils;

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
		UserBean userInfo = null;
		String url = null;
		List<CategoryBean> categoryList = null;
		List<UserBean> userList = null;
		List<StatusBean> statusList = null;

		//リクエストを受信
		request.setCharacterEncoding("UTF-8");
		int taskId = Integer.parseInt(request.getParameter("task_id"));

		//ログインチェック
		HttpSession session = request.getSession();
		if (session.getAttribute("userInfo") != null) {
			userInfo = (UserBean) session.getAttribute("userInfo");
		} else {
			url = "login";
			response.setCharacterEncoding("UTF-8");
			response.sendRedirect(url);
			session.invalidate();
			return;
		}

		TaskEditDAO dao = new TaskEditDAO();
		TaskEditForm defaultForm = null; //編集フォーム画面のデフォルト入力内容格納用
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

		// 編集ボタンを押下したときsessionに格納されているユーザIDと
		// 編集ボタンのあるレコードのユーザIDが一致すれば編集画面への遷移を認める
		if (userInfo.getUserId().equals(defaultForm.getUserId())) {
			session.setAttribute("defaultForm", defaultForm);
			session.setAttribute("categoryList", categoryList);
			session.setAttribute("userList", userList);
			session.setAttribute("statusList", statusList);

			url = "taskeditform.jsp"; // 編集化の画面jsp
		} else {
			url = "taskedit-failed.jsp"; // 編集不可のエラー画面jsp
		}

		//フォワード
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);

	}

	/**
	 * タスク編集フォーム画面から遷移してきた時の処理
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = null;
		Date limitDate = null;

		HttpSession session = request.getSession();

		//リクエスト処理
		request.setCharacterEncoding("UTF-8");
		String taskName = (String) request.getParameter("task_name");
		String limitDateString = (String) request.getParameter("limit_date");
		String memo = (String) request.getParameter("memo");
		int taskId = Integer.parseInt(request.getParameter("task_id"));

		//フォーム入力内容のバリデーション
		List<String> errorMsg = new ArrayList<>();
		if (TaskUtils.isValidTaskName(taskName) != null) {
			errorMsg.add(TaskUtils.isValidTaskName(taskName));
		}
		if (TaskUtils.isValidMemo(memo) != null) {
			errorMsg.add(TaskUtils.isValidMemo(memo));
		}
		if (TaskUtils.isValidDate(limitDateString) != null) {
			errorMsg.add(TaskUtils.isValidDate(limitDateString));
		} else if(!limitDateString.isEmpty()){
			limitDate = Date.valueOf(limitDateString);
		}

		if (errorMsg.size() > 0) {
			session.setAttribute("errorMsg", errorMsg);

			url = "edit?task_id=" + taskId;
			response.setCharacterEncoding("UTF-8");
			response.sendRedirect(url);
			return;
		}

		//UPDATE文を実行
		TaskEditDAO dao = new TaskEditDAO();
		int rowsAffected = -1; /* SQLで取得したレコード数を格納する変数 */
		try {
			TaskEditForm newTask = new TaskEditForm();

			newTask.setTaskId(taskId);
			newTask.setTaskName(taskName);
			newTask.setCategoryId(Integer.parseInt(request.getParameter("category_id")));
			newTask.setLimitDate(limitDate);
			newTask.setUserId(request.getParameter("user_id"));
			newTask.setStatusCode(request.getParameter("status_code"));
			newTask.setMemo(memo);

			rowsAffected = dao.updateTask(newTask);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException(e);

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);

		}

		//遷移先の分岐
		if (rowsAffected > 0) {
			url = "taskedit-success.jsp";
		} else {
			url = "taskedit-failed.jsp";
		}

		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}