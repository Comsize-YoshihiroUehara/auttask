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

import model.dao.TaskRegisterDAO;
import model.entity.CategoryBean;
import model.entity.StatusBean;
import model.entity.TaskBean;
import model.entity.UserBean;
import utils.TaskUtils;

/**
 * Servlet implementation class TaskAddServlet
 */
@WebServlet("/taskregister")
public class TaskAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//TaskRegisterDAOを用いて登録画面で表示する一覧情報(カテゴリ情報、担当者情報、ステータス情報)をDBから取得する
		TaskRegisterDAO dao = new TaskRegisterDAO();

		List<CategoryBean> categoryList = null;
		List<UserBean> userList = null;
		List<StatusBean> statusList = null;

		try {
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

		//DAOで取得したカテゴリ情報、ユーザー情報、ステータス情報をセッションオブジェクトに代入
		HttpSession session = request.getSession();
		session.setAttribute("categoryList", categoryList);
		session.setAttribute("userList", userList);
		session.setAttribute("statusList", statusList);

		//登録画面への転送を行う
		RequestDispatcher rd = request.getRequestDispatcher("task-register.jsp");
		rd.forward(request, response);
	}

	/**
	 * 入力されたタスクを登録するサーブレット
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Date limitDate = null;
		String url;

		HttpSession session = request.getSession();

		//リクエスト処理
		request.setCharacterEncoding("UTF-8");
		String taskName = (String) request.getParameter("task_name");
		String limitDateString = (String) request.getParameter("limit_date");
		String memo = (String) request.getParameter("memo");

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
		} else if (!limitDateString.isEmpty()) {
			limitDate = Date.valueOf(limitDateString);
		}

		if (errorMsg.size() > 0) {
			session.setAttribute("errorMsg", errorMsg);

			url = "taskregister";
			response.setCharacterEncoding("UTF-8");
			response.sendRedirect(url);
			return;
		}

		//データベース登録の処理
		int rowsAffected = -1;
		TaskRegisterDAO dao = new TaskRegisterDAO();
		try {
			//タスク登録用のデータをセット
			TaskBean task = new TaskBean();
			task.setTaskName(request.getParameter("task_name"));
			task.setCategoryId(Integer.parseInt(request.getParameter("category_id")));
			task.setLimitDate(limitDate);
			task.setUserId(request.getParameter("user_id"));
			task.setStatusCode(request.getParameter("status_code"));
			task.setMemo(request.getParameter("memo"));

			//SQL実行
			rowsAffected = dao.registerTask(task);

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException(e);

		}

		//遷移先の分岐
		if (rowsAffected > 0) {
			//登録成功時
			url = "register-success.jsp";
		} else {
			url = "register-failure.jsp";
		}

		//フォワード
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
