package model.dao;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import model.entity.UserBean;

class UserDAOTest {

	@InjectMocks
	UserDAO userDAO;

	@Mock
	Connection con;

	@Mock
	PreparedStatement pstmt;

	@Mock
	ResultSet rs;

	private AutoCloseable closeable;

	@BeforeEach
	void setUp() throws ClassNotFoundException, SQLException {
		Mockito.clearAllCaches(); // キャッシュをクリア
		closeable = MockitoAnnotations.openMocks(this);
		Mockito.mockStatic(ConnectionManager.class);
		when(ConnectionManager.getConnection()).thenReturn(con);
	}

	@Test
	void ログイン成功テスト() throws SQLException {
		userDAO = new UserDAO();
		UserBean bean = null;

		when(con.prepareStatement(anyString())).thenReturn(pstmt);
		when(pstmt.executeQuery()).thenReturn(rs);
		when(rs.next()).thenReturn(true);
		when(rs.getString("user_id")).thenReturn("admin");
		when(rs.getString("user_name")).thenReturn("test");
		when(rs.getTimestamp("update_datetime")).thenReturn(null);

		try {
			bean = userDAO.login("admin", "root");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		assertNotNull(bean);
		assertEquals("admin", bean.getUserId());
		assertEquals("test", bean.getUserName());
	}
	
	@Test
	void ログイン失敗時の動作確認テスト() throws SQLException {
		userDAO = new UserDAO();
		UserBean bean = null;
		
		when(con.prepareStatement(anyString())).thenReturn(pstmt);
		when(pstmt.executeQuery()).thenReturn(rs);
		when(rs.next()).thenReturn(false);
		
		try {
			bean = userDAO.login("ad", "root");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		assertNull(bean);
	}
}
