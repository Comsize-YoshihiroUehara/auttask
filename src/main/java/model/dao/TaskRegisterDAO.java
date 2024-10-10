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

// タスク登録画面で入力された内容をDBに登録する
// カテゴリ情報、担当者情報、ステータスコードはt_taskに紐づいている別のマスタから表示しないといけない
public class TaskRegisterDAO {
	public int registerTask(TaskBean task) throws SQLException, ClassNotFoundException {
		int count = 0;
		
		String sql = "INSERT INTO m_task (task_name, category_id, limit_date, user_id, status_code,"
				+ " memo) VALUES(?,?,?,?,?,?)";
		
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			
			pstmt.setString(1, task.getTaskName());
			pstmt.setInt(2, task.getCategoryId());
			pstmt.setDate(3, task.getLimitDate());
			pstmt.setString(4, task.getUserId());
			pstmt.setString(5, task.getStatusCode());
			pstmt.setString(6, task.getMemo());
			count = pstmt.executeUpdate();
		}
		return count;
		
	}
	
	// タスク登録画面でユーザー名をプルダウンから選択できるようにするメソッド
	public List<UserBean> displayUser() throws ClassNotFoundException, SQLException{
		List<UserBean> userList = new ArrayList<UserBean>();
		String sql = "SELECT * FROM m_user";
		
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {

			ResultSet res = pstmt.executeQuery();

			while (res.next()) {
				String userId = res.getString("user_id");
				String userName = res.getString("user_name");
				UserBean user = new UserBean();
				user.setUserId(userId);
				user.setUserName(userName);
				userList.add(user);
			}
		}
		return userList;
		
	}
	
	// タスク登録画面でカテゴリ情報(ex.「新商品A:開発プロジェクト」)をプルダウンから選択できるようにするメソッド
	public List<CategoryBean> displayCategory() throws SQLException, ClassNotFoundException{
		List<CategoryBean> categoryList = new ArrayList<CategoryBean>();
		String sql = "SELECT * FROM m_category";
		
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {

			ResultSet res = pstmt.executeQuery();

			while (res.next()) {
				int categoryId = res.getInt("category_id");
				String categoryName = res.getString("category_name");
				CategoryBean category = new CategoryBean();
				category.setCategoryId(categoryId);
				category.setCategoryName(categoryName);
				categoryList.add(category);
			}
		}
		return categoryList;
	}
	
	// タスク登録画面でステータス情報(ex.未着手、着手、完了)をプルダウンから選択できるようにするメソッド
	public List<StatusBean> displayStatus() throws SQLException, ClassNotFoundException{
		List<StatusBean> statusList = new ArrayList<StatusBean>();
		String sql = "SELECT * FROM m_status";
		
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {

			ResultSet res = pstmt.executeQuery();

			while (res.next()) {
				String statusCode = res.getString("status_code");
				String statusName = res.getString("status_name");
				StatusBean status = new StatusBean();
				status.setStatusCode(statusCode);
				status.setStatusName(statusName);
				statusList.add(status);
			}
		}
		return statusList;
		
	}
}
