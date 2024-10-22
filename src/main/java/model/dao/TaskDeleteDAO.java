package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.entity.TaskBean;

public class TaskDeleteDAO {

	// taskIdsInt(配列)内の数字とタスクIDが一致する行を検索してリストで返すメソッド
	public List<TaskBean> selectTasksByTaskID(int[] taskIds)
			throws ClassNotFoundException, SQLException {
		List<TaskBean> checkedTask = new ArrayList<>();

		StringBuilder sb = new StringBuilder();
		sb.append("SELECT");
		sb.append(" task_id,");
		sb.append(" user_id");
		sb.append(" FROM");
		sb.append(" t_task");
		sb.append(" WHERE task_id");
		sb.append(" IN(");
		for (int i = 0; i < taskIds.length; i++) {
			sb.append("?");
			if (i != (taskIds.length - 1)) {
				sb.append(", ");
			}
		}
		sb.append(")");
		String sql = sb.toString();

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {

			for (int i = 0; i < taskIds.length; i++) {
				pstmt.setInt(i + 1, taskIds[i]);
			}

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				TaskBean task = new TaskBean();
				task.setTaskId(rs.getInt("task_id"));
				task.setUserId(rs.getString("user_id"));
				checkedTask.add(task);
			}
		}
		return checkedTask;
	}

	// ログインユーザIDと登録ユーザIDが一致するレコードを削除するメソッド
	public int deleteTaskByTaskId(List<TaskBean> checkedTask)
			throws ClassNotFoundException, SQLException {
		if (checkedTask.size() == 0 || checkedTask == null) {
			return 0;
		}

		int rowsAffected = 0;//戻り値用の変数

		/*SQL準備********************************************/
		StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM");
		sb.append(" t_task ");
		sb.append("WHERE task_id ");

		//IN句
		sb.append("IN(");
		for (int i = 0; i < checkedTask.size(); i++) {
			sb.append("?");
			if (i != (checkedTask.size() - 1)) {
				sb.append(", ");
			}
		}
		sb.append(")");
		String sql = sb.toString();
		/****************************************************/

		//SQL文実行
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {

			//プレースホルダに値をセット
			for (int i = 0; i < checkedTask.size(); i++) {
				TaskBean matchedTask = checkedTask.get(i);
				pstmt.setInt(i + 1, matchedTask.getTaskId());
			}

			rowsAffected = pstmt.executeUpdate();
		}

		//削除完了した件数を返す
		return rowsAffected;

	}

	public int deleteComments(List<TaskBean> checkedTask) 
			throws ClassNotFoundException, SQLException {
		if (checkedTask.size() == 0 || checkedTask == null) {
			return 0;
		}
		int rowsAffected = 0;

		/*SQL準備********************************************/
		StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM");
		sb.append(" t_comment ");
		sb.append("WHERE task_id ");

		//IN句
		sb.append("IN(");
		for (int i = 0; i < checkedTask.size(); i++) {
			sb.append("?");
			if (i != (checkedTask.size() - 1)) {
				sb.append(", ");
			}
		}
		sb.append(")");
		String sql = sb.toString();
		/****************************************************/

		//SQL文実行
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {

			//プレースホルダに値をセット
			for (int i = 0; i < checkedTask.size(); i++) {
				TaskBean matchedTask = checkedTask.get(i);
				pstmt.setInt(i + 1, matchedTask.getTaskId());
			}

			rowsAffected = pstmt.executeUpdate();
		}

		return rowsAffected;
	}

}
