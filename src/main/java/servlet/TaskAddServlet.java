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

import model.dao.TaskRegisterDAO;
import model.entity.CategoryBean;
import model.entity.StatusBean;
import model.entity.TaskBean;
import model.entity.UserBean;

/**
 * Servlet implementation class TaskAddServlet
 */
@WebServlet("/taskregister")
public class TaskAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TaskAddServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

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

		} catch (SQLException e) {
			e.printStackTrace();

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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	// 入力されたタスクを登録するサーブレット
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		int rowsAffected = 0;
		String url = null;

		TaskRegisterDAO dao = new TaskRegisterDAO();
		try {
			// htmlのdateからリクエストを受け取ってTaskBeanの「期限」にセットする方法
			// 補足資料の「日付と時刻の形式」を参照すること
			// java側でDateクラスで扱うのはいろいろと問題があるのでLocalDateクラスを使う
			// LocalDateクラスはsql.dateに変換することができる
			Date date = Date.valueOf((String) request.getParameter("limit_date"));

			// タスク登録用のデータをTaskBeanにセットする
			TaskBean task = new TaskBean();
			task.setTaskName(request.getParameter("task_name"));
			task.setCategoryId(Integer.parseInt(request.getParameter("category_id")));
			task.setLimitDate(date);
			task.setUserId(request.getParameter("user_id"));
			task.setStatusCode(request.getParameter("status_code"));
			task.setMemo(request.getParameter("memo"));

			rowsAffected = dao.registerTask(task);

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();

		}

		if (rowsAffected > 0) {
			//登録成功時
			url = "register-success.jsp";

		} else {
			//登録失敗時
			url = "register-failure.jsp";

		}

		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
