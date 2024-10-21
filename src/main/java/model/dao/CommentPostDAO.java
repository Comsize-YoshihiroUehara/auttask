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
		sb.append("");
		sb.append("");
		sb.append("");
		sb.append("");
		sb.append("");
		sb.append("");
		String sql = sb.toString();

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {

		}
		return rowsAffected;
	}
}
