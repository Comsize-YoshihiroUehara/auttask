package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

	public boolean login(String userid, String password) throws ClassNotFoundException, SQLException {

		String sql = "SELECT user_name FROM m_user WHERE user_id = ?,password = ?";

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, userid);
			pstmt.setString(2, password);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				return true;

			} else {
				return false;
			}

		}
	}
}
