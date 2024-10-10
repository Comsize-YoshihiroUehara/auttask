package model.dao;

import java.util.ArrayList;
import java.util.List;

import model.entity.TaskBean;
import model.entity.UserBean;

public class TaskListDAO {
	
	public List<TaskBean> selectAllTask(UserBean userInfo){
		List<TaskBean> taskList = new ArrayList<>();
		
		//SQLを作成
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT");
		sb.append("");
		sb.append("");
		sb.append("");
		String sql = sb.toString();
		
		return taskList;
	}
}
