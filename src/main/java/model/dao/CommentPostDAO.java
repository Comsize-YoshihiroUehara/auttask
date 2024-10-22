package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.entity.TaskCommentsBean;

public class CommentPostDAO {
	public int insertComment(TaskCommentsBean comment)
			throws ClassNotFoundException, SQLException {
		if (comment == null) {
			return 0;
		}

		int rowsAffected = 0;

		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO");
		sb.append(" t_comment (");
		sb.append(" task_id,");
		sb.append(" user_id,");
		sb.append(" comment)");
		sb.append(" VALUES");
		sb.append(" (?,?,?)");
		String sql = sb.toString();

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			
			pstmt.setInt(1, comment.getTaskId());
			pstmt.setString(2, comment.getUserId());
			pstmt.setString(3, comment.getComment());
			
			rowsAffected = pstmt.executeUpdate();
		}
		return rowsAffected;
	}
}
