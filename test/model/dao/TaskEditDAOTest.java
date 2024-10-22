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
	public void test1_タスク取得テスト() {
		int taskId = 1;
		try {
			taskEditForm = taskEditDAO.selectTaskByTaskId(taskId);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		assertNotNull(taskEditForm);
		assertEquals(1, taskEditForm.getTaskId());
	}
}
