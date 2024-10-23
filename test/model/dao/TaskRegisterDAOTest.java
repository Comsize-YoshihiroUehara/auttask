package model.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.entity.CategoryBean;
import model.entity.StatusBean;
import model.entity.TaskBean;
import model.entity.UserBean;

class TaskRegisterDAOTest {
	private TaskRegisterDAO dao;
	private TaskBean bean;
	private UserBean user;
	private CategoryBean category;
	private StatusBean status;
	private List<UserBean> userList;
	private List<CategoryBean> categoryList;
	private List<StatusBean> statusList;
	
	@BeforeEach
	public void 登録準備() {
		dao = new TaskRegisterDAO();
		bean = new TaskBean();
		user = new UserBean();
		category = new CategoryBean();
		userList = null;
		categoryList = null;
		 statusList = null;
		
		
		
		
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
	
	@Test
	public void ステータスリスト取得テスト() {
		
		try {
			statusList = dao.selectAllStatus();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		assertNotNull(statusList);
	}
}