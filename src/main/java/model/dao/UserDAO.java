package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.entity.UserBean;

public class UserDAO {

	public UserBean login(String Userid, String Password) throws ClassNotFoundException, SQLException {

		UserBean bean = new UserBean();

		String sql = "SELECT user_id,user_name,updateDatetime FROM m_user WHERE user_id = ? AND password = ?";

		//データベース接続
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con
						.prepareStatement(sql)) {
			//プレースホルダーにユーザーID・パスワードを格納
			pstmt.setString(1, Userid);
			pstmt.setString(2, Password);
			//SQL実行
			ResultSet rs = pstmt.executeQuery();
			
			//リクエストで飛んできたユーザーID・パスワードと同じになるものを探す
			if (rs.next()) {
				bean.setUserId(rs.getString("user_id"));
				bean.setUserName(rs.getString("user_name"));
				bean.setUpdateDatetime(rs.getTimestamp("updateDatetime"));

				
				return bean;

			} else {
				return null;
			}

		}
	}
}