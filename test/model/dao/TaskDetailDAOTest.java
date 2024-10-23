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

import model.entity.TaskCommentsBean;
import model.entity.TaskListBean;

public class TaskDetailDAOTest {

	@InjectMocks
	TaskDetailDAO taskDetailDAO;

	@Mock
	Connection con;

	@Mock
	PreparedStatement pstmt;

	@Mock
	ResultSet rs;

	private AutoCloseable closeable;
	private TaskListBean taskSelected;
	private List<TaskCommentsBean> taskComments;

	@BeforeEach
	void setUp() throws ClassNotFoundException, SQLException {
		Mockito.clearAllCaches(); // キャッシュをクリア
		closeable = MockitoAnnotations.openMocks(this);
		Mockito.mockStatic(ConnectionManager.class);
		when(ConnectionManager.getConnection()).thenReturn(con);
	}

	@Test
	public void test1_タスク情報表示成功テスト() throws SQLException, ClassNotFoundException {
		int taskId = 1;

		taskSelected = null;

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

		taskSelected = taskDetailDAO.selectTaskByTaskId(taskId);

		assertNotNull(taskSelected);
		assertEquals(1, taskSelected.getTaskId());
		assertEquals("タスク情報の表示", taskSelected.getTaskName());
		assertEquals(1, taskSelected.getCategoryId());
		assertEquals("新商品A：開発プロジェクト", taskSelected.getCategoryName());
		assertEquals(null, taskSelected.getLimitDate());
		assertEquals("admin", taskSelected.getUserId());
		assertEquals("テスト1", taskSelected.getUserName());
		assertEquals("50", taskSelected.getStatusCode());
		assertEquals("着手", taskSelected.getStatusName());
		assertEquals("始めました。", taskSelected.getMemo());
		assertEquals(null, taskSelected.getCreateDateTime());
		assertEquals(null, taskSelected.getUpdateTime());

		verify(pstmt, times(1)).executeQuery();

	}

	@Test
	public void test2_タスク情報表示失敗テスト() throws SQLException, ClassNotFoundException {
		int taskId = -1;

		taskSelected = null;

		when(con.prepareStatement(anyString())).thenReturn(pstmt);
		when(pstmt.executeQuery()).thenReturn(rs);
		when(rs.next()).thenReturn(false);

		taskSelected = taskDetailDAO.selectTaskByTaskId(taskId);

		assertNull(taskSelected);
		
		verify(pstmt, times(1)).setInt(1, taskId);
		verify(pstmt, times(1)).executeQuery();
	}

	@Test
	public void test2_コメント情報表示成功テスト() throws SQLException, ClassNotFoundException {
		int taskId = 1;
		int commentId = 1;

		taskComments = null;

		when(con.prepareStatement(anyString())).thenReturn(pstmt);
		when(pstmt.executeQuery()).thenReturn(rs);
		when(rs.next()).thenReturn(true).thenReturn(false);
		when(rs.getInt("comment_id")).thenReturn(1);
		when(rs.getInt("task_id")).thenReturn(1);
		when(rs.getString("task_name")).thenReturn("コメント情報の表示");
		when(rs.getString("user_id")).thenReturn("admin");
		when(rs.getString("user_name")).thenReturn("テスト1");
		when(rs.getString("comment")).thenReturn("コメントのテストです。");
		when(rs.getTimestamp("update_datetime")).thenReturn(null);

		taskComments = taskDetailDAO.selectCommentsByTaskId(taskId);

		assertNotNull(taskComments);
		assertEquals(1, taskComments.get(0).getCommentId());
		assertEquals(1, taskComments.get(0).getTaskId());
		assertEquals("コメント情報の表示", taskComments.get(0).getTaskName());
		assertEquals("admin", taskComments.get(0).getUserId());
		assertEquals("テスト1", taskComments.get(0).getUserName());
		assertEquals("コメントのテストです。", taskComments.get(0).getComment());
		assertEquals(null, taskComments.get(0).getUpdateDateTime());

		verify(pstmt, times(1)).executeQuery();
	}
}
