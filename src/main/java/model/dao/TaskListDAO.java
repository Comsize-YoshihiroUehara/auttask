package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.entity.CategoryBean;
import model.entity.StatusBean;
import model.entity.TaskBean;
import model.entity.UserBean;

public class TaskListDAO {

	public List<TaskBean> selectAllTask(UserBean userInfo)
			throws ClassNotFoundException, SQLException {
		//戻り値用変数
		//jsp遷移後null判別して条件分岐させたいので初期値をnullにしています。
		List<TaskBean> taskList = null;

		/*SQL準備********************************************/
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT");
		sb.append(" t1.task_id,");
		sb.append(" t1.task_name,");
		sb.append(" t2.category_name,");
		sb.append(" t1.limit_date,");
		sb.append(" t3.user_id,");
		sb.append(" t4.status_code,");
		sb.append(" t1.memo,");
		sb.append(" t1.create_datetime,");
		sb.append(" t1.update_datetime ");
		sb.append("FROM");
		sb.append(" t_task t1 ");
		sb.append("JOIN");
		sb.append(" m_category t2 ");
		sb.append("ON");
		sb.append(" t1.category_id = t2.category_id, ");
		sb.append("JOIN");
		sb.append(" m_user t3 ");
		sb.append("ON");
		sb.append(" t1.user_id = t3.user_id ");
		sb.append("JOIN");
		sb.append(" m_status t4 ");
		sb.append("ON");
		sb.append(" t1.status_code = t4.status_code ");
		sb.append("ORDER BY t1.task_id");
		//WHEREで条件指定の必要性が今後発生する？
		//sb.append("WHERE ");
		String sql = sb.toString();
		/****************************************************/

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {

			//SQL実行
			ResultSet rs = pstmt.executeQuery();

			//取得したデータをリストに格納
			taskList = new ArrayList<>();
			while (rs.next()) {
				TaskBean bean = new TaskBean();

				bean.setTaskId(rs.getInt("task_id"));
				bean.setTaskName(rs.getString("task_name"));
				bean.setCategoryId(rs.getInt("category_id"));
				bean.setLimitDate(rs.getDate("limit_date"));
				bean.setUserId(rs.getString("user_id"));
				bean.setStatusCode(rs.getString("status_code"));
				bean.setMemo(rs.getString("memo"));
				bean.setCreateDateTime(rs.getTimestamp("create_datetime"));
				bean.setUpdateTime(rs.getTimestamp("update_datetime"));

				taskList.add(bean);
			}
		}

		return taskList; //処理終了
	}

	public List<CategoryBean> selectAllCategory()
			throws SQLException, ClassNotFoundException {
		List<CategoryBean> categoryList = null;
		String sql = "SELECT category_id, category_name FROM m_category";

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {

			ResultSet res = pstmt.executeQuery();

			categoryList = new ArrayList<>();
			while (res.next()) {
				CategoryBean category = new CategoryBean();

				int categoryId = res.getInt("category_id");
				String categoryName = res.getString("category_name");

				category.setCategoryId(categoryId);
				category.setCategoryName(categoryName);
				categoryList.add(category);
			}
		}
		return categoryList;
	}
	
	public List<UserBean> selectAllUser() throws ClassNotFoundException, SQLException {
		List<UserBean> userList = null;
		String sql = "SELECT user_id, user_name FROM m_user";

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {

			ResultSet res = pstmt.executeQuery();

			userList = new ArrayList<>();
			while (res.next()) {
				UserBean user = new UserBean();

				String userId = res.getString("user_id");
				String userName = res.getString("user_name");

				user.setUserId(userId);
				user.setUserName(userName);
				userList.add(user);
			}
		}
		return userList;

	}
	
	public List<StatusBean> selectAllStatus()
			throws SQLException, ClassNotFoundException {
		List<StatusBean> statusList = null;

		String sql = "SELECT status_code, status_name FROM m_status";

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {

			ResultSet res = pstmt.executeQuery();

			statusList = new ArrayList<>();
			while (res.next()) {
				StatusBean status = new StatusBean();

				String statusCode = res.getString("status_code");
				String statusName = res.getString("status_name");

				status.setStatusCode(statusCode);
				status.setStatusName(statusName);
				statusList.add(status);
			}
		}
		return statusList;

	}	
	
}
