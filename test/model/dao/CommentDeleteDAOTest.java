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

public class CommentDeleteDAOTest {
	@InjectMocks
	CommentDeleteDAO commentDeleteDAO;

	@Mock
	Connection con;

	@Mock
	PreparedStatement pstmt;

	@Mock
	ResultSet mockResultSet;

	private AutoCloseable closeable;
	private int rowsAffected;

	@BeforeEach
	void setUp()
			throws ClassNotFoundException, SQLException {
		Mockito.clearAllCaches();
		closeable = MockitoAnnotations.openMocks(this);
		Mockito.mockStatic(ConnectionManager.class);
		when(ConnectionManager.getConnection()).thenReturn(con);

		rowsAffected = 0;
	}

	@Test
	void test1_ユーザID取得成功_正常動作()
			throws SQLException, ClassNotFoundException {
		int commentId = 1;

		when(con.prepareStatement(anyString())).thenReturn(pstmt);
		when(pstmt.executeQuery()).thenReturn(mockResultSet);
		when(mockResultSet.next()).thenReturn(true).thenReturn(false);
		when(mockResultSet.getString("user_id")).thenReturn("admin");

		String userId = commentDeleteDAO.selectUserIdByCommentId(commentId);

		assertNotNull(userId);
		assertEquals(userId, "admin");

		verify(pstmt, times(1)).setInt(1, commentId);
		verify(pstmt, times(1)).executeQuery();
	}

	@Test
	void test2_ユーザID取得失敗_正常動作()
			throws SQLException, ClassNotFoundException {
		int commentId = -1;

		when(con.prepareStatement(anyString())).thenReturn(pstmt);
		when(pstmt.executeQuery()).thenReturn(mockResultSet);
		when(mockResultSet.next()).thenReturn(false);

		String userId = commentDeleteDAO.selectUserIdByCommentId(commentId);

		assertNull(userId);

		verify(pstmt, times(1)).setInt(1, commentId);
		verify(pstmt, times(1)).executeQuery();
	}

	@Test
	void test3_ユーザID取得失敗_SQLException()
			throws SQLException, ClassNotFoundException {
		int commentId = 1;

		when(con.prepareStatement(anyString())).thenReturn(pstmt);
		when(pstmt.executeQuery()).thenThrow(SQLException.class);

		assertThrows(SQLException.class, () -> {
			commentDeleteDAO.selectUserIdByCommentId(commentId);
		});

		verify(pstmt, times(1)).setInt(1, commentId);
		verify(pstmt, times(1)).executeQuery();
	}

	@Test
	void test4_コメント削除成功_正常動作()
			throws SQLException, ClassNotFoundException {
		int commentId = 1;

		when(con.prepareStatement(anyString())).thenReturn(pstmt);
		when(pstmt.executeUpdate()).thenReturn(1);

		rowsAffected = commentDeleteDAO.deleteCommentByCommentId(commentId);

		assertEquals(1, rowsAffected);

		verify(pstmt, times(1)).setInt(1, commentId);
		verify(pstmt, times(1)).executeUpdate();
	}

	@Test
	void test5_コメント削除失敗_正常動作()
			throws SQLException, ClassNotFoundException {
		int commentId = -1;

		when(con.prepareStatement(anyString())).thenReturn(pstmt);
		when(pstmt.executeUpdate()).thenReturn(0);

		rowsAffected = commentDeleteDAO.deleteCommentByCommentId(commentId);

		assertEquals(0, rowsAffected);

		verify(pstmt, times(1)).setInt(1, commentId);
		verify(pstmt, times(1)).executeUpdate();
	}

	@Test
	void test6_コメント削除失敗_SQLException()
			throws SQLException, ClassNotFoundException {
		int commentId = -1;

		when(con.prepareStatement(anyString())).thenReturn(pstmt);
		when(pstmt.executeUpdate()).thenThrow(SQLException.class);
		
		assertThrows(SQLException.class, () ->{
			commentDeleteDAO.deleteCommentByCommentId(commentId);
		});

		verify(pstmt, times(1)).setInt(1, commentId);
		verify(pstmt, times(1)).executeUpdate();
	}
}
