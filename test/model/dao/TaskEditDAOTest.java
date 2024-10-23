package model.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.entity.CategoryBean;
import model.entity.StatusBean;
import model.entity.UserBean;
import model.form.TaskEditForm;

public class TaskEditDAOTest {
	private TaskEditDAO taskEditDAO;
	private TaskEditForm taskEditForm;
	private int rowsAffected;
	private List<CategoryBean> categoryList;
	private List<UserBean> userList;
	private List<StatusBean> statusList;
	@BeforeEach
	void setUp_テスト準備() {
		taskEditDAO = new TaskEditDAO();
		taskEditForm = null;
		
		
	}

	@Test
	 void test1_タスク取得成功テスト() {
		int taskId = 1;
		try {
			taskEditForm = taskEditDAO.selectTaskByTaskId(taskId);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		assertNotNull(taskEditForm);
		assertEquals(1, taskEditForm.getTaskId());
	}
	
	@Test
	void test2_タスク取得失敗テスト() {
		int taskId = 0;
		try {
			taskEditForm = taskEditDAO.selectTaskByTaskId(taskId);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		assertNotNull(taskEditForm);
		assertEquals(0, taskEditForm.getTaskId());
	}
		
	@Test
	void test3_タスク追加成功テスト() {
		java.sql.Date date = Date.valueOf("2030-01-22");
		
		rowsAffected = 0;
		
		taskEditForm = new TaskEditForm();
		taskEditForm.setTaskId(1);
		taskEditForm.setTaskName("タスク1_テスト");
		taskEditForm.setCategoryId(2);
		taskEditForm.setLimitDate(date);
		taskEditForm.setUserId("admin");
		taskEditForm.setStatusCode("0");
		taskEditForm.setMemo(null);
		
		try {
			rowsAffected = taskEditDAO.updateTask(taskEditForm);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		assertEquals(1, rowsAffected);
	}
	
	@Test
	public void カテゴリーリスト取得テスト() {
		
		try {
			categoryList = taskEditDAO.selectAllCategory();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		assertNotNull(categoryList);
	}
	
	@Test
	public void ユーザーリスト取得テスト() {
		
		try {
			userList = taskEditDAO.selectAllUser();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		assertNotNull(userList);
	}
	
	@Test
	public void ステータスリスト取得テスト() {
		
		try {
			statusList = taskEditDAO.selectAllStatus();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		assertNotNull(statusList);
	}
}
