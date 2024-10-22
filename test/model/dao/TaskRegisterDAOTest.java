package model.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import model.entity.TaskBean;

class TaskRegisterDAOTest {
	private TaskRegisterDAO dao = new TaskRegisterDAO();
	TaskBean bean = new TaskBean();

	@Test
	public void 登録成功テスト() {

		bean.setTaskName("タスク１");
		bean.setCategoryId(1);
		bean.setUserId("test2");
		bean.setStatusCode("50");
		bean.setMemo("未着手");
		int rowsAffected = 0;

		try {
			rowsAffected = dao.registerTask(bean);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		assertEquals(1, rowsAffected);
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
	        dao.registerTask(bean);
	    });
	}
}