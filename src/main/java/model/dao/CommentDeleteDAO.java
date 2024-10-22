package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentDeleteDAO {
	public String selectUserIdByCommentId(int commentId) 
			throws ClassNotFoundException, SQLException {
		String userId = null;
		
		/*SQL準備********************************************/
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT");
		sb.append(" user_id ");
		sb.append("FROM");
		sb.append(" t_comment ");
		sb.append("WHERE");
		sb.append(" comment_id = ?");
		String sql = sb.toString();
		/****************************************************/
		
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, commentId);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				userId = rs.getString("user_id");
			}
		}
		return userId;
	}
	
	public int deleteCommentByCommentId(int commentId) 
			throws ClassNotFoundException, SQLException {
		int rowsAffected = -1;

		/*SQL準備********************************************/
		StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM");
		sb.append(" t_comment ");
		sb.append("WHERE ");
		sb.append(" comment_id = ?");
		String sql = sb.toString();
		/****************************************************/

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, commentId);
			
			rowsAffected = pstmt.executeUpdate();
		}

		return rowsAffected;
	}
}
