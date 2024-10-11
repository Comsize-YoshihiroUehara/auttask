package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.entity.CategoryBean;
import model.entity.StatusBean;
import model.entity.UserBean;
import model.form.TaskEditForm;

public class TaskEditDAO {

	public TaskEditForm selectTaskByTaskId(int taskId)
			throws ClassNotFoundException, SQLException {
		TaskEditForm taskEditForm = new TaskEditForm();//戻り値用インスタンス

		/* SQL作成 ***********************************************/
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT");
		sb.append(" t1.task_id,");
		sb.append(" t1.task_name,");
		sb.append(" t1.category_id,");
		sb.append(" t2.category_name,");
		sb.append(" t1.limit_date,");
		sb.append(" t1.user_id,");
		sb.append(" t3.user_name,");
		sb.append(" t1.status_code,");
		sb.append(" t4.status_name,");
		sb.append(" t1.memo,");
		sb.append(" t1.create_datetime,");
		sb.append(" t1.update_datetime ");
		sb.append("FROM");
		sb.append(" t_task t1 ");
		sb.append("JOIN");
		sb.append(" m_category t2 ");
		sb.append("ON");
		sb.append(" t1.category_id = t2.category_id ");
		sb.append("JOIN");
		sb.append(" m_user t3 ");
		sb.append("ON");
		sb.append(" t1.user_id = t3.user_id ");
		sb.append("JOIN");
		sb.append(" m_status t4 ");
		sb.append("ON");
		sb.append(" t1.status_code = t4.status_code ");
		sb.append("WHERE");
		sb.append(" task_id = ?");
		String sql = sb.toString();
		/*********************************************************/

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			//プレースホルダに値をセット
			pstmt.setInt(1, taskId);

			//SQL実行
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				taskEditForm.setTaskId(rs.getInt("task_id"));
				taskEditForm.setTaskName(rs.getString("task_name"));
				taskEditForm.setCategoryId(rs.getInt("category_id"));
				taskEditForm.setCategoryName(rs.getString("category_name"));
				taskEditForm.setLimitDate(rs.getDate("limit_date"));
				taskEditForm.setUserId(rs.getString("user_id"));
				taskEditForm.setUserName(rs.getString("user_name"));
				taskEditForm.setStatusCode(rs.getString("status_code"));
				taskEditForm.setStatusName(rs.getString("status_name"));
				taskEditForm.setMemo(rs.getString("memo"));
				taskEditForm.setUpdateTime(rs.getTimestamp("update_datetime"));
			}

		}

		return taskEditForm;
	}

	/*
	 * フォームで入力された情報を受け取ってデータベースを更新するメソッド
	 */
	public int updateTask(TaskEditForm taskEditForm)
			throws ClassNotFoundException, SQLException {
		
		int rowsAffected = 0; //戻り値用変数
		/* SQL作成 ***********************************************/
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE");   
		sb.append(" t_task ");
		sb.append("SET");
		sb.append(" task_name = ?,");
		sb.append(" category_id = ?,");
		sb.append(" user_id = ?,");
		sb.append(" status_code = ?,");
		//以下2つNOT NULLではないカラム
		sb.append(" limit_date = ?,");
		sb.append(" memo = ?");
		sb.append("WHERE");
		sb.append(" task_id = ?");
		String sql = sb.toString();
		/*********************************************************/

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			
			//プレースホルダに値をセット
			pstmt.setString();//task_name
			pstmt.setInt();//category_id
			pstmt.setString();//user_id
			pstmt.setString();//status_code
			
			//SQL実行
			rowsAffected = pstmt.executeUpdate();
		}
		return rowsAffected;
	}

	// タスク編集画面でカテゴリ情報をプルダウンから選択できるようにするメソッド
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

	// タスク登録画面でユーザー名をプルダウンから選択できるようにするメソッド
	public List<UserBean> selectAllUser()
			throws ClassNotFoundException, SQLException {
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

	// タスク登録画面でステータス情報(ex.未着手、着手、完了)をプルダウンから選択できるようにするメソッド
	public List<StatusBean> selectAllStatus()
			throws SQLException, ClassNotFoundException {
		List<StatusBean> statusList = null;

		String sql = "SELECT * FROM m_status";

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
