package model.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
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

import model.entity.TaskListBean;

public class TaskListDAOTest {
	
	@InjectMocks
	TaskListDAO taskListDAO;
	
	@Mock
	Connection con;

	@Mock
	PreparedStatement pstmt;

	@Mock
	ResultSet rs;

	private AutoCloseable closeable;
	private List<TaskListBean> taskList;
	
	@BeforeEach
	void setUp() throws ClassNotFoundException, SQLException {
		Mockito.clearAllCaches(); // キャッシュをクリア
		closeable = MockitoAnnotations.openMocks(this);
		Mockito.mockStatic(ConnectionManager.class);
		when(ConnectionManager.getConnection()).thenReturn(con);
	}
	
	@Test
	public void test1_リスト取得テスト() throws ClassNotFoundException, SQLException {
		
		when(con.prepareStatement(anyString())).thenReturn(pstmt);
		when(pstmt.executeQuery()).thenReturn(rs);
		when(rs.next()).thenReturn(true).thenReturn(false);
		when(rs.getInt("task_id")).thenReturn(1);
		when(rs.getString("task_name")).thenReturn("タスク情報の表示");
		when(rs.getInt("category_id")).thenReturn(1);
		when(rs.getString("category_name")).thenReturn("新商品A：開発プロジェクト");
		when(rs.getDate("limit_date")).thenReturn(null);
		when(rs.getString("user_id")).thenReturn("admin");
		when(rs.getString("user_name")).thenReturn("テスト1");
		when(rs.getString("status_code")).thenReturn("50");
		when(rs.getString("status_name")).thenReturn("着手");
		when(rs.getString("memo")).thenReturn("始めました。");
		when(rs.getTimestamp("create_datetime")).thenReturn(null);
		when(rs.getTimestamp("update_datetime")).thenReturn(null);
		
		taskList = taskListDAO.selectAllTask(null);

		assertNotNull(taskList);
		assertEquals(1, taskList.get(0).getTaskId());
		assertEquals("タスク情報の表示", taskList.get(0).getTaskName());
		assertEquals(1, taskList.get(0).getCategoryId());
		assertEquals("新商品A：開発プロジェクト", taskList.get(0).getCategoryName());
		assertEquals(null, taskList.get(0).getLimitDate());
		assertEquals("admin", taskList.get(0).getUserId());
		assertEquals("テスト1", taskList.get(0).getUserName());
		assertEquals("50", taskList.get(0).getStatusCode());
		assertEquals("着手", taskList.get(0).getStatusName());
		assertEquals("始めました。", taskList.get(0).getMemo());
		assertEquals(null, taskList.get(0).getCreateDateTime());
		assertEquals(null, taskList.get(0).getUpdateTime());

		verify(pstmt, times(1)).executeQuery();
	}
}
