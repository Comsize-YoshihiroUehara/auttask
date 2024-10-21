package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class TaskDetailServlet
 */
@WebServlet("/list/detail")
public class TaskDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TaskDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストのエンコーディング方式を指定
		request.setCharacterEncoding("UTF-8");
		
		//TaskCommentsDAO dao = new TaskCommentsDAO();
		//TaskCommentsBean taskDetail = null;
		
		// 選択したタスクのタスクIDを取得
		int taskId = Integer.parseInt(request.getParameter("task_id"));
		
		//taskDetail = dao.selectTask(taskId);
		
		
		// セッションオブジェクトの取得
		HttpSession session = request.getSession();
		// タスクIDをセッションに設定
		session.setAttribute("task_id", taskId);
		// 該当するタスクIDの詳細情報をセッションに設定
		//session.setAttribute("taskDetail", taskDetail);
		// コメント閲覧画面に遷移
		RequestDispatcher rd = request.getRequestDispatcher("taskdetail.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
