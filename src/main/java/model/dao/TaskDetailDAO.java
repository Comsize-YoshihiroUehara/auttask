package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.entity.TaskCommentsBean;

public class TaskDetailDAO {
	
	// タスク名をクリックしたときに表示するコメント情報を検索するメソッド
	public TaskCommentsBean selectTask(int taskId) throws SQLException, ClassNotFoundException {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT");
		sb.append(" t1.comment_id,");
		sb.append(" t1.task_id,");
		sb.append(" t2.task_name,");
		sb.append(" t1.user_id,");
		sb.append(" t3.user_name,");
		sb.append(" t1.comment,");
		sb.append(" t1.update_datetime");
		sb.append(" FROM");
		sb.append(" t_comment t1 JOIN t_task t2");
		sb.append(" ON t1.task_id = t2.task_id");
		sb.append(" JOIN m_user t3");
		sb.append(" ON t1.user_id = t3.user_id");
		sb.append(" WHERE");
		sb.append(" t1.task_id = ?");
		String sql = sb.toString();
		
		TaskCommentsBean taskDetail = new TaskCommentsBean();

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			// プレースホルダへの値の設定
			pstmt.setInt(1, taskId);

			// SQLステートメントの実行
			ResultSet res = pstmt.executeQuery();

			while (res.next()) {
				taskDetail.setCommentId(res.getInt("comment_id"));
				taskDetail.setTaskId(res.getInt("task_id"));
				taskDetail.setTaskName(res.getString("task_name"));
				taskDetail.setUserId(res.getString("user_id"));
				taskDetail.setUserName(res.getString("user_name"));
				taskDetail.setComment(res.getString("comment"));
				taskDetail.setUpdateDateTime(res.getTimestamp("update_datetime"));
			}
		}
		return taskDetail;
	}
}
