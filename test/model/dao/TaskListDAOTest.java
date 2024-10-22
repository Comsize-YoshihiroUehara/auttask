package model.dao;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.entity.TaskListBean;

public class TaskListDAOTest {
	private TaskListDAO taskListDAO;
	private List<TaskListBean> taskList;
	
	@BeforeEach
	void setUp_テスト準備() {
		taskListDAO = new TaskListDAO();
		taskList = null;
	}
	
	@Test
	public void test1_リスト取得テスト() {
		try {
			taskList = taskListDAO.selectAllTask(null);
		}catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		assertNotNull(taskList);
	}
}
