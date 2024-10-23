package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.entity.TaskCommentsBean;
import model.entity.TaskListBean;

public class TaskDetailDAO {
	// クリックしたタスク名に該当する一覧情報を取得するメソッド
	public TaskListBean selectTaskByTaskId(int taskId) 
			throws SQLException, ClassNotFoundException {
		TaskListBean taskSelected = null; //戻り値用変数
		
		/* SQL作成 ***********************************************/
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT");
		sb.append(" t1.task_id,");
		sb.append(" t1.task_name,");
		sb.append(" t1.category_id,");
		sb.append(" t2.category_name,");
		sb.append(" t1.limit_date,");
		sb.append(" t1.user_id,");
		sb.append(" t3.user_name,");
		sb.append(" t1.status_code,");
		sb.append(" t4.status_name,");
		sb.append(" t1.memo,");
		sb.append(" t1.create_datetime,");
		sb.append(" t1.update_datetime ");
		sb.append("FROM");
		sb.append(" t_task t1 ");
		sb.append("JOIN");
		sb.append(" m_category t2 ");
		sb.append("ON");
		sb.append(" t1.category_id = t2.category_id ");
		sb.append("JOIN");
		sb.append(" m_user t3 ");
		sb.append("ON");
		sb.append(" t1.user_id = t3.user_id ");
		sb.append("JOIN");
		sb.append(" m_status t4 ");
		sb.append("ON");
		sb.append(" t1.status_code = t4.status_code ");
		sb.append(" WHERE");
		sb.append(" t1.task_id = ?");
		String sql = sb.toString();
		/* SQL作成 ***********************************************/
		
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			// プレースホルダへの値の設定
			pstmt.setInt(1, taskId);
			// SQL実行
			ResultSet rs = pstmt.executeQuery();
			
			
			while (rs.next()) {
				taskSelected = new TaskListBean();
				taskSelected.setTaskId(rs.getInt("task_id"));
				taskSelected.setTaskName(rs.getString("task_name"));
				taskSelected.setCategoryId(rs.getInt("category_id"));
				taskSelected.setCategoryName(rs.getString("category_name"));
				taskSelected.setLimitDate(rs.getDate("limit_date"));
				taskSelected.setUserId(rs.getString("user_id"));
				taskSelected.setUserName(rs.getString("user_name"));
				taskSelected.setStatusCode(rs.getString("status_code"));
				taskSelected.setStatusName(rs.getString("status_name"));
				taskSelected.setMemo(rs.getString("memo"));
				taskSelected.setCreateDateTime(rs.getTimestamp("create_datetime"));
				taskSelected.setUpdateTime(rs.getTimestamp("update_datetime"));
				
				return taskSelected;
			}
		}
		return taskSelected;
		
	}
	
	// タスク名をクリックしたときに表示するコメント情報を検索するメソッド
	public List<TaskCommentsBean> selectCommentsByTaskId(int taskId) 
			throws SQLException, ClassNotFoundException {
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
		
		List<TaskCommentsBean> taskComments = null;

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			// プレースホルダへの値の設定
			pstmt.setInt(1, taskId);

			// SQLステートメントの実行
			ResultSet res = pstmt.executeQuery();

			taskComments = new ArrayList<>();
			while (res.next()) {
				TaskCommentsBean bean = new TaskCommentsBean();
				
				bean.setCommentId(res.getInt("comment_id"));
				bean.setTaskId(res.getInt("task_id"));
				bean.setTaskName(res.getString("task_name"));
				bean.setUserId(res.getString("user_id"));
				bean.setUserName(res.getString("user_name"));
				bean.setComment(res.getString("comment"));
				bean.setUpdateDateTime(res.getTimestamp("update_datetime"));
				
				taskComments.add(bean);
			}
		}
		return taskComments;
	}
}
