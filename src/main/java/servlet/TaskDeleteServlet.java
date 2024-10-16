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

import model.dao.TaskDeleteDAO;
import model.entity.TaskBean;
import model.entity.UserBean;

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
		UserBean userInfo = (UserBean) session.getAttribute("userInfo");

		String url = null;

		TaskDeleteDAO dao = new TaskDeleteDAO();
		try {
			if (request.getParameterValues("task_id_checkbox") != null) {
				String[] taskIdsString = request.getParameterValues("task_id_checkbox");// 中身はtask_id
				int[] taskIdsInt = new int[taskIdsString.length];
				for (int i = 0; i < taskIdsString.length; i++) {
					taskIdsInt[i] = Integer.parseInt(taskIdsString[i]);// チェックボックスで選んだ行のタスクIDが(0,2,3)などと入っている配列
				}

				// ログイン中のユーザIDと担当者情報のユーザIDが一致する場合のみレコードの削除を認める
				// taskIdsIntの数字とタスクIDが一致する行をList<TaskBean>にして持ってくる
				List<TaskBean> checkedTask = dao.selectTasksByTaskID(taskIdsInt);
				// その(複数)行についてuserInfoのユーザIDとTaskBean型のgetUserId()で得られるユーザIDが一致するものをリストにする
				int tasksSelected = checkedTask.size();
				int tasksDeleted = 0;
				for (int i = 0; i < tasksSelected; i++) {
					TaskBean task = checkedTask.get(i - tasksDeleted);
					// ログインユーザIDと一致しないユーザIDがある行は除外する
					if (!userInfo.getUserId().equals(task.getUserId())) {
						checkedTask.remove(i - tasksDeleted);
						tasksDeleted++;
					}
				}
				rowsAffected = dao.deleteTaskByTaskId(checkedTask);
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		//処理件数が0以上であれば成功画面、0以下であればエラー画面
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
