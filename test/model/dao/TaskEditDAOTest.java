package model.dao;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.form.TaskEditForm;

public class TaskEditDAOTest {
	private TaskEditDAO taskEditDAO;
	private TaskEditForm taskEditForm;

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
}
