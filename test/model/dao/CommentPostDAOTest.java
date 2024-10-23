package model.dao;

import static org.junit.Assert.*;
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

import model.entity.TaskCommentsBean;

class CommentPostDAOTest {

	@InjectMocks
	CommentPostDAO commentpostdao;

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

	private CommentPostDAO commentpostdao1;
	private TaskCommentsBean taskcommentsbean;

	@BeforeEach
	public void テスト準備() {
		commentpostdao = new CommentPostDAO();
		taskcommentsbean = new TaskCommentsBean();
	}

	@Test
	public void コメント取得テスト() throws SQLException {
		int rowsAffected = 0;

		taskcommentsbean.setTaskId(1);
		taskcommentsbean.setUserId("コメ");
		taskcommentsbean.setComment("は");

		when(con.prepareStatement(anyString())).thenReturn(pstmt);
		when(pstmt.executeUpdate()).thenReturn(1);

		try {
			rowsAffected = commentpostdao.insertComment(taskcommentsbean);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		assertEquals(1, rowsAffected);

		verify(pstmt, times(1)).setInt(1, taskcommentsbean.getTaskId());
		verify(pstmt, times(1)).setString(2, taskcommentsbean.getUserId());
		verify(pstmt, times(1)).setString(3, taskcommentsbean.getComment());
		verify(pstmt, times(1)).executeUpdate();
	}

}
