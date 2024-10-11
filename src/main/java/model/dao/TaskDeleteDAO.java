package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.entity.TaskListBean;

public class TaskDeleteDAO {

	public int deleteTask(TaskListBean task) throws ClassNotFoundException, SQLException {

		int deleteNumber = 0;

		String sql = "DELETE FROM t_task WHERE task_name = ?,category_id = ?, limit_date = ?,staus_code = ?";

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {

			pstmt.setString(1, task.getTaskName());
			pstmt.setInt(2, task.getCategoryId());
			pstmt.setDate(3, task.getLimitDate());
			pstmt.setString(4, task.getStatusCode());

			deleteNumber = pstmt.executeUpdate();
		}
		return deleteNumber;

	}
}
