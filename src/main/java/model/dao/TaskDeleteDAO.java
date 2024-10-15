package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TaskDeleteDAO {

	public int deleteTaskByTaskId(int[] taskIds)
			throws ClassNotFoundException, SQLException {
		int rowsAffected = 0;//戻り値用の変数

		/*SQL準備********************************************/
		StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM");
		sb.append(" t_task ");
		sb.append("WHERE task_id ");

		//IN句
		sb.append("IN(");
		for (int i = 0; i < taskIds.length; i++) {
			sb.append("?");
			if (i != (taskIds.length - 1)) {
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
			for (int i = 0; i < taskIds.length; i++) {
				pstmt.setInt(i + 1, taskIds[i]);
			}

			rowsAffected = pstmt.executeUpdate();
		}

		//削除完了した件数を返す
		return rowsAffected;

	}
}
