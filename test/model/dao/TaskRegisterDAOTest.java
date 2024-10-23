package model.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.entity.CategoryBean;
import model.entity.TaskBean;
import model.entity.UserBean;

class TaskRegisterDAOTest {
	private TaskRegisterDAO dao;
	private TaskBean bean;
	private List<UserBean> userList;
	private CategoryBean category;
	private UserBean user;
	private List<CategoryBean> categoryList;
	
	@BeforeEach
	public void 登録準備() {
		categoryList = null;
		userList = null;
		category = new CategoryBean();
		user = new UserBean();
		dao = new TaskRegisterDAO();
		bean = new TaskBean();
	}

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

	@Test
	public void ユーザーリスト取得テスト() {

		try {
			userList = dao.selectAllUser();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		assertNotNull(userList);

	}
	
	@Test
	public void カテゴリーリスト取得テスト() {
		
		try {
			categoryList = dao.selectAllCategory();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		assertNotNull(categoryList);
	}
}