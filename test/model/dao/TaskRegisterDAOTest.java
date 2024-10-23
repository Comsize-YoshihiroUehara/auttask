package model.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

	private TaskBean taskbean;
	private UserBean userbean;
	private CategoryBean category;
	private StatusBean status;
	private List<UserBean> userList;
	private List<CategoryBean> categoryList;
	private List<StatusBean> statusList;

	@Test
	public void 登録成功テスト() throws SQLException {
		int rowsAffected = 0;
		taskbean = new TaskBean();

		taskbean.setTaskName("タスク１");
		taskbean.setCategoryId(1);
		taskbean.setLimitDate(null);
		taskbean.setUserId("test2");
		taskbean.setStatusCode("50");
		taskbean.setMemo("未着手");

		when(con.prepareStatement(anyString())).thenReturn(pstmt);
		when(pstmt.executeUpdate()).thenReturn(1);

		try {
			rowsAffected = taskregisterdao.registerTask(taskbean);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		assertEquals(1, rowsAffected);

		verify(pstmt, times(1)).setString(1, taskbean.getTaskName());
		verify(pstmt, times(1)).setInt(2, taskbean.getCategoryId());
		verify(pstmt, times(1)).setDate(3, taskbean.getLimitDate());
		verify(pstmt, times(1)).setString(4, taskbean.getUserId());
		verify(pstmt, times(1)).setString(5, taskbean.getStatusCode());
		verify(pstmt, times(1)).setString(6, taskbean.getMemo());
		verify(pstmt, times(1)).executeUpdate();
	}

	@Test
	public void 登録失敗例外テスト() throws SQLException {

		taskbean = new TaskBean();

		taskbean.setTaskName("タスク１");
		taskbean.setCategoryId(1);
		taskbean.setUserId("test3");
		taskbean.setStatusCode("50");
		taskbean.setMemo("未着手");

		when(con.prepareStatement(anyString())).thenReturn(pstmt);
		// pstmt.executeUpdate()がSQLExceptionをスローするように設定
		when(pstmt.executeUpdate()).thenThrow(new SQLException("SQLエラーが発生しました"));

		// ClassNotFoundExceptionやSQLExceptionが発生することを確認する
		assertThrows(SQLException.class, () -> {
			taskregisterdao.registerTask(taskbean);
		});

		verify(pstmt, times(1)).setString(1, taskbean.getTaskName());
		verify(pstmt, times(1)).setInt(2, taskbean.getCategoryId());
		verify(pstmt, times(1)).setDate(3, taskbean.getLimitDate());
		verify(pstmt, times(1)).setString(4, taskbean.getUserId());
		verify(pstmt, times(1)).setString(5, taskbean.getStatusCode());
		verify(pstmt, times(1)).setString(6, taskbean.getMemo());
		verify(pstmt, times(1)).executeUpdate();
	}

	@Test
	public void ユーザーリスト取得テスト() throws SQLException {

		
		when(con.prepareStatement(anyString())).thenReturn(pstmt);
		when(pstmt.executeQuery()).thenReturn(rs);
		when(rs.next()).thenReturn(true).thenReturn(false);
		when(rs.getString("user_id")).thenReturn("");
		when(rs.getString("user_name")).thenReturn("");
		
		
		try {
			userList = taskregisterdao.selectAllUser();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		assertNotNull(userList);
		assertEquals("" ,userList.get(0).getUserId());
		
		verify(pstmt,times(1)).executeQuery();
	}

	@Test
	public void カテゴリーリスト取得テスト() throws SQLException {

		
		when(con.prepareStatement(anyString())).thenReturn(pstmt);
		when(pstmt.executeQuery()).thenReturn(rs);
		when(rs.next()).thenReturn(true).thenReturn(false);
		when(rs.getInt("CategoryId")).thenReturn(0);
		when(rs.getString("CategoryName")).thenReturn("");
		
		try {
			categoryList = taskregisterdao.selectAllCategory();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		assertNotNull(categoryList);
		assertEquals(0,categoryList.get(0).getCategoryId());
		
		verify(pstmt,times(1)).executeQuery();
	}

	@Test
	public void ステータスリスト取得テスト() throws SQLException {

		
		when(con.prepareStatement(anyString())).thenReturn(pstmt);
		when(pstmt.executeQuery()).thenReturn(rs);
		when(rs.next()).thenReturn(true).thenReturn(false);
		when(rs.getString("status_code")).thenReturn("");
		when(rs.getString("status_name")).thenReturn("");
		

	    try {
	        statusList = taskregisterdao.selectAllStatus();
	    } catch (ClassNotFoundException | SQLException e) {
	        e.printStackTrace();
	    }

	    // リストがnullでないことを確認
	    assertNotNull(statusList);
	    
	   
	    
	    // 要素の内容を確認
	    assertEquals("", statusList.get(0).getStatusCode());
	    assertEquals("", statusList.get(0).getStatusName());

	    // executeQueryが1回だけ呼ばれていることを確認
	    verify(pstmt, times(1)).executeQuery();
	}
}