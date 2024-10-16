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
		int rowsAffected = -1;

		request.setCharacterEncoding("UTF-8");

		// htmlのdateからリクエストを受け取ってTaskBeanの「期限」にセットする方法
		// 補足資料の「日付と時刻の形式」を参照すること
		// java側でDateクラスで扱うのはいろいろと問題があるのでLocalDateクラスを使う
		// LocalDateクラスはsql.dateに変換することができる

		//タスク期限の妥当性チェック
		Date limitDate = null;
		String limitDateString = request.getParameter("limit_date");

		if (limitDateString != null && !limitDateString.isEmpty()) {
			if (TaskUtils.isValidDate(Date.valueOf(limitDateString))) {
				limitDate = Date.valueOf(limitDateString);
			}
		}

		//データベース登録の処理
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
		String url = "register-failure.jsp";
		if (rowsAffected > 0) {
			//登録成功時
			url = "register-success.jsp";
		}

		//フォワード
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
