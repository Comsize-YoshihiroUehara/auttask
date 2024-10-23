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

import model.entity.TaskBean;

public class TaskDeleteDAOTest {
	@InjectMocks
	TaskDeleteDAO taskDeleteDAO;

	@Mock
	Connection con;

	@Mock
	PreparedStatement pstmt;

	@Mock
	ResultSet mockResultSet;

	private AutoCloseable closeable;
	private List<TaskBean> taskList;

	@BeforeEach
	void setUp_テスト初期化()
			throws ClassNotFoundException, SQLException {
		Mockito.clearAllCaches(); // キャッシュをクリア
		closeable = MockitoAnnotations.openMocks(this);
		Mockito.mockStatic(ConnectionManager.class);
		when(ConnectionManager.getConnection()).thenReturn(con);

		taskList = null;
	}

	@Test
	void test1_タスク取得テスト成功_正常動作()
			throws SQLException, ClassNotFoundException {
		taskDeleteDAO = new TaskDeleteDAO();

		int[] taskIds = { 1, 2, 3 };

		when(con.prepareStatement(anyString())).thenReturn(pstmt);
		when(pstmt.executeQuery()).thenReturn(mockResultSet);
		when(mockResultSet.next())
				.thenReturn(true)
				.thenReturn(true)
				.thenReturn(true)
				.thenReturn(false);
		when(mockResultSet.getInt("task_id"))
				.thenReturn(1)
				.thenReturn(2)
				.thenReturn(3);
		when(mockResultSet.getString("user_id")).thenReturn("userId");

		taskList = taskDeleteDAO.selectTasksByTaskID(taskIds);

		assertNotNull(taskList);
		assertEquals(taskList.size(), taskIds.length);

		for (int i = 0; i < taskIds.length; i++) {
			assertEquals(taskList.get(i).getTaskId(), taskIds[i]);
			assertEquals(taskList.get(i).getUserId(), "userId");
		}

		verify(pstmt, times(taskIds.length)).setInt(anyInt(), anyInt());
		verify(mockResultSet, times(taskIds.length)).getInt("task_id");
		verify(mockResultSet, times(taskIds.length)).getString("user_id");
	}
}
