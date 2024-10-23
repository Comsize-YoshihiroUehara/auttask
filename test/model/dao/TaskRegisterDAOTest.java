package model.dao;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import model.entity.CategoryBean;
import model.entity.StatusBean;
import model.entity.TaskBean;
import model.entity.UserBean;

class TaskRegisterDAOTest {

	@InjectMocks
	TaskRegisterDAO taskregisterdao;

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

	private TaskBean bean;
	private UserBean user;
	private CategoryBean category;
	private StatusBean status;
	private List<UserBean> userList;
	private List<CategoryBean> categoryList;
	private List<StatusBean> statusList;

	@Test
	public void 登録成功テスト() throws SQLException {
		int rowsAffected = 0;
		bean = new TaskBean();

		bean.setTaskName("タスク１");
		bean.setCategoryId(1);
		bean.setLimitDate(null);
		bean.setUserId("test2");
		bean.setStatusCode("50");
		bean.setMemo("未着手");
		

		when(con.prepareStatement(anyString())).thenReturn(pstmt);
		when(pstmt.executeUpdate()).thenReturn(1);

		try {
			rowsAffected = taskregisterdao.registerTask(bean);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		assertEquals(1, rowsAffected);

		verify(pstmt, times(1)).setString(1, bean.getTaskName());
		verify(pstmt, times(1)).setInt(2, bean.getCategoryId());
		verify(pstmt, times(1)).setDate(3, bean.getLimitDate());
		verify(pstmt, times(1)).setString(4, bean.getUserId());
		verify(pstmt, times(1)).setString(5, bean.getStatusCode());
		verify(pstmt, times(1)).setString(6, bean.getMemo());
	}

	@Test
	public void 登録失敗例外テスト() {
		bean.setTaskName("タスク１");
		bean.setCategoryId(1);
		bean.setUserId("test3");
		bean.setStatusCode("50");
		bean.setMemo("未着手");
		// ClassNotFoundExceptionやSQLExceptionが発生することを確認する
		assertThrows(SQLException.class, () -> {
			taskregisterdao.registerTask(bean);
		});
	}

	@Test
	public void ユーザーリスト取得テスト() {

		try {
			userList = taskregisterdao.selectAllUser();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		assertNotNull(userList);

	}

	@Test
	public void カテゴリーリスト取得テスト() {

		try {
			categoryList = taskregisterdao.selectAllCategory();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		assertNotNull(categoryList);
	}

	@Test
	public void ステータスリスト取得テスト() {

		try {
			statusList = taskregisterdao.selectAllStatus();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		assertNotNull(statusList);
	}
}