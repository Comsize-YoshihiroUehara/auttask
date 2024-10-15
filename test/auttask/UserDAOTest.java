package auttask;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import model.dao.UserDAO;
import model.entity.UserBean;

class UserDAOTest {

	@Test
	public void ログイン成功テスト() {
		UserDAO dao = new UserDAO();
		UserBean loginsucses = null;

		try {
			loginsucses = dao.login("admin", "root");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		assertNotNull(loginsucses);
	}

	@Test
	public void ログイン失敗テスト() {
		UserDAO dao = new UserDAO();
		UserBean loginfalse = null;

		try {
			loginfalse = dao.login("ad", "rot");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		assertNull(loginfalse);

	}
}
