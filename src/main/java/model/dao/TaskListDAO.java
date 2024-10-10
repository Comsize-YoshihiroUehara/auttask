package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.entity.TaskListBean;
import model.entity.UserBean;

public class TaskListDAO {

	public List<TaskListBean> selectAllTask(UserBean userInfo)
			throws ClassNotFoundException, SQLException {
		//戻り値用変数
		//jsp遷移後null判別して条件分岐させたいので初期値をnullにしています。
		List<TaskListBean> taskList = null;

		/*SQL準備********************************************/
		//複数テーブルの内部結合をする場合、フィールドを増やさなければTaskBean型のリストに代入できない
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT");
		sb.append(" t1.task_id,");
		sb.append(" t1.task_name,");
		sb.append(" t2.category_name,");
		sb.append(" t1.limit_date,");
		sb.append(" t3.user_name,");
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
		sb.append("ORDER BY t1.task_id ");
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
				TaskListBean bean = new TaskListBean();

				bean.setTaskId(rs.getInt("task_id"));
				bean.setTaskName(rs.getString("task_name"));
				bean.setCategoryId(rs.getInt("category_id"));
				bean.setCategoryName(rs.getString("category_name"));
				bean.setLimitDate(rs.getDate("limit_date"));
				bean.setUserId(rs.getString("user_id"));
				bean.setUserName(rs.getString("user_name"));
				bean.setStatusCode(rs.getString("status_code"));
				bean.setStatusName(rs.getString("status_name"));
				bean.setMemo(rs.getString("memo"));
				bean.setCreateDateTime(rs.getTimestamp("create_datetime"));
				bean.setUpdateTime(rs.getTimestamp("update_datetime"));

				taskList.add(bean);
			}
		}

		return taskList; //処理終了
	}
	
}
