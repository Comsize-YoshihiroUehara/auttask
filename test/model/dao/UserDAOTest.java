package model.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import model.entity.UserBean;

class UserDAOTest {

	@Test
	public void ログイン失敗テスト() {
		UserDAO dao = new UserDAO();
		UserBean loginFalse = null;
		
		try {
			loginFalse = dao.login("ad","root");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		assertNull(loginFalse);
	}
	@Test
	public void ログイン失敗テスト２() {
		UserDAO dao = new UserDAO();
		UserBean loginFalse = null;
		
		try {
			loginFalse = dao.login("admin","rot");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		assertNull(loginFalse);
	}
	@Test
	public void ログイン成功テスト() {
		UserDAO dao = new UserDAO();
		UserBean loginSuccess = null;
		
		try {
			loginSuccess  = dao.login("admin","root");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		assertNotNull(loginSuccess);
	}
}
