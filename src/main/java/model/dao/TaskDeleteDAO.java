package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.entity.TaskListBean;

public class TaskDeleteDAO {

	public int deleteTask(TaskListBean task) throws ClassNotFoundException, SQLException {
		//削除件数
		int rowsAffected = 0;
		
		String sql = "DELETE FROM t_task WHERE task_name = ?,category_id = ?,limit_date = ?,user_name= ?,status_name=?,memo=?";
		//SQL文実行
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {

			pstmt.setString(1, task.getTaskName());
			pstmt.setInt(2, task.getCategoryId());
			pstmt.setDate(3, task.getLimitDate());
			pstmt.setString(4, task.getStatusCode());

			rowsAffected = pstmt.executeUpdate();
		}
		//削除件数を返す
		return rowsAffected;

	}
}
