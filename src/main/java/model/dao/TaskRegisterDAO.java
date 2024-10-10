package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.entity.TaskBean;

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
}
