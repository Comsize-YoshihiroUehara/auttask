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
		int rowsAffected = 0;//SQLの処理件数を格納する用

		//リクエストを処理
		request.setCharacterEncoding("UTF-8");

		//セッションを取得
		HttpSession session = request.getSession();
		/* 後でユーザごとに削除出来るタスクかどうか確認する為に使えそう */
		//UserBean userInfo = (UserBean) session.getAttribute("userInfo");

		TaskDeleteDAO dao = new TaskDeleteDAO();
		try {
			if (request.getParameterValues("task_id_checkbox") != null) {
				String[] taskIdsString = request.getParameterValues("task_id_checkbox");
				int[] taskIdsInt = new int[taskIdsString.length];
				for (int i = 0; i < taskIdsString.length; i++) {
					taskIdsInt[i] = Integer.parseInt(taskIdsString[i]);
				}

				rowsAffected = dao.deleteTaskByTaskId(taskIdsInt);
			}

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException(e);

		}

		//処理件数が0以上であれば成功画面、0以下であればエラー画面
		String url;
		if (rowsAffected > 0) {
			url = "taskdelete-success.jsp";
		} else {
			url = "taskdelete-failed.jsp";
		}

		//フォワード
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}
}
