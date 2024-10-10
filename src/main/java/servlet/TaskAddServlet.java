package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.TaskRegisterDAO;
import model.entity.TaskBean;

/**
 * Servlet implementation class TaskAddServlet
 */
@WebServlet("/task-add-servlet")
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	// 入力されたタスクを登録するサーブレット
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		int count = 0;
		String url = null;
		
		TaskRegisterDAO dao = new TaskRegisterDAO();
		TaskBean task = new TaskBean();
		
		task.setTaskName(request.getParameter("task_name"));
		task.setCategoryId(Integer.parseInt(request.getParameter("category_id")));
		//task.setLimitDate(request.getParameter("limit_date"));
		request.getParameter("user_name");
		task.setStatusCode(request.getParameter("status_code"));
		task.setMemo(request.getParameter("memo"));
		
		try {
			count = dao.registerTask(task);
		} catch (SQLException | ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		if(count>0) {
			url = "register-success.jsp";
		}else {
			url = "register-failure.jsp";
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
