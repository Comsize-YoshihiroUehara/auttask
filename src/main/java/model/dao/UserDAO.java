package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.entity.UserBean;

public class UserDAO {

	public UserBean login(String userid, String password) throws ClassNotFoundException, SQLException {

		UserBean bean = new UserBean();

		String sql = "SELECT user_name FROM m_user WHERE user_id = ? AND password = ?";

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con
						.prepareStatement(sql)) {

			pstmt.setString(1, userid);
			pstmt.setString(2, password);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				bean.setUserID(rs.getString("user_id"));
				bean.setPassWord(rs.getString("pasword"));
				bean.setUserName(rs.getString("user_name"));
				bean.setUpdateDataTime(rs.getInt("update_datetime"));

				return bean;

			} else {
				return null;
			}

		}
	}
}