package model.dao;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.entity.TaskCommentsBean;

class CommentPostDAOTest {
	private CommentPostDAO dao;
	private TaskCommentsBean bean;

	@BeforeEach
	public void テスト準備() {
		dao = new CommentPostDAO();
		bean = new TaskCommentsBean();
	}

	@Test
	public void コメント取得テスト() {
		int rowsAffected = 0;

		bean.setTaskId(1);
		bean.setUserId("コメ");
		bean.setComment("は");

		try {
			rowsAffected = dao.insertComment(bean);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		assertEquals(0, rowsAffected);
	}

}
