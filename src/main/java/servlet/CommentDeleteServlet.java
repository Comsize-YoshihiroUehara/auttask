package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.CommentDeleteDAO;
import model.entity.UserBean;

/**
 * Servlet implementation class CommentDeleteServlet
 */
@WebServlet("/list/detail/delete")
public class CommentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CommentDeleteServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int rowsAffected = 0;//SQLの処理件数を格納する用
		String url;

		//リクエストを処理
		request.setCharacterEncoding("UTF-8");
		int commentId = Integer.parseInt(request.getParameter("comment_id"));

		//セッションを取得
		HttpSession session = request.getSession();
		UserBean userInfo = (UserBean) session.getAttribute("userInfo");
		int taskId = (Integer)session.getAttribute("taskId");
		
		CommentDeleteDAO dao = new CommentDeleteDAO();
		try {
			String userIdOnComment = dao.selectUserIdByCommentId(commentId);
			
			if(userIdOnComment.equals(userInfo.getUserId())) {
				rowsAffected = dao.deleteCommentByCommentId(commentId);
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		if(rowsAffected > 0) {
			url = "../../list/detail?task_id=" + taskId;
			response.setCharacterEncoding("UTF-8");
			response.sendRedirect(url);
		} else {
			url = "../../list/detail?task_id=" + taskId;
			response.setCharacterEncoding("UTF-8");
			response.sendRedirect(url);
		}
	}

}
