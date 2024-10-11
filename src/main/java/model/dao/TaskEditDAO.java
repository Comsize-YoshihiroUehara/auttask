package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.entity.CategoryBean;
import model.entity.UserBean;
import model.form.TaskEditForm;

public class TaskEditDAO {
	public TaskEditForm selectTaskByTaskId(int taskId)
			throws ClassNotFoundException, SQLException {
		TaskEditForm taskEditForm = new TaskEditForm();

		return taskEditForm;
	}

	public int updateTask(TaskEditForm taskEditForm)
			throws ClassNotFoundException, SQLException {
		int rowsAffected = 0;

		StringBuilder sb = new StringBuilder();
		String sql = sb.toString();

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {

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
}
