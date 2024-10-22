package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.CommentPostDAO;
import model.entity.TaskCommentsBean;
import model.entity.UserBean;

/**
 * Servlet implementation class CommentPostServlet
 */
@WebServlet("/list/detail/post")
public class CommentPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CommentPostServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.sendRedirect("list");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		UserBean userInfo = (UserBean) session.getAttribute("userInfo");
		int taskId = (Integer) session.getAttribute("taskId");

		TaskCommentsBean comment = new TaskCommentsBean();
		comment.setUserId(userInfo.getUserId());
		comment.setTaskId(taskId);
		comment.setComment(request.getParameter("comment"));

		int rowsAffected = 0;
		CommentPostDAO dao = new CommentPostDAO();
		try {
			rowsAffected = dao.insertComment(comment);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		//	コメント投稿後はコメント閲覧画面にもう一度飛ばす(サーブレットのマッピングに注意)
		response.setCharacterEncoding("UTF-8");
		response.sendRedirect("../detail?task_id=" + taskId);
	}

}
