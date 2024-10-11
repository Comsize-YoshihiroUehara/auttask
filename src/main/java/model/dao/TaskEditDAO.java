package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
