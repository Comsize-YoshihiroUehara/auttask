package model.entity;

import java.sql.Timestamp;

public class TaskCommentsBean {
	
	private int comentId;
	private int taskId;
	private String userId;
	private String comment;
	private  Timestamp updateDateTime;
	
	public int getComentId() {
		return comentId;
	}
	public void setComentId(int comentId) {
		this.comentId = comentId;
	}
	public int getTaskId() {
		return taskId;
	}
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Timestamp getUpdateDateTime() {
		return updateDateTime;
	}
	public void setUpdateDateTime(Timestamp updateDateTime) {
		this.updateDateTime = updateDateTime;
	}
	

}
