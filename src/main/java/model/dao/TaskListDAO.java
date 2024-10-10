package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.entity.TaskBean;
import model.entity.UserBean;

public class TaskListDAO {

	public List<TaskBean> selectAllTask(UserBean userInfo)
			throws ClassNotFoundException, SQLException {
		//戻り値用変数
		//jsp遷移後null判別して条件分岐させたいので初期値をnullにしています。
		List<TaskBean> taskList = null;

		/*SQL準備********************************************/
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT");
		sb.append(" task_id,");
		sb.append(" task_name,");
		sb.append(" category_id,");
		sb.append(" limit_date,");
		sb.append(" user_id,");
		sb.append(" status_code,");
		sb.append(" memo,");
		sb.append(" create_datetime,");
		sb.append(" update_datetime ");
		sb.append("FROM");
		sb.append(" t_task");
		//WHEREで条件指定の必要性が今後発生する？
		//sb.append("WHERE ");
		String sql = sb.toString();
		/****************************************************/

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {

			//SQL実行
			ResultSet rs = pstmt.executeQuery();

			//取得したデータをリストに格納
			taskList = new ArrayList<>();
			while (rs.next()) {
				TaskBean bean = new TaskBean();

				bean.setTaskId(rs.getInt("task_id"));
				bean.setTaskName(rs.getString("task_name"));
				bean.setCategoryId(rs.getInt("category_id"));
				bean.setLimitDate(rs.getDate("limit_date"));
				bean.setUserId(rs.getString("user_id"));
				bean.setStatusCode(rs.getString("status_code"));
				bean.setMemo(rs.getString("memo"));
				bean.setCreateDateTime(rs.getTimestamp("create_datetime"));
				bean.setUpdateTime(rs.getTimestamp("update_datetime"));

				taskList.add(bean);
			}
		}

		return taskList; //処理終了
	}
}
