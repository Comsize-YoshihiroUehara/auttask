package model.entity;

public class UserBean {
	
	private String UserID;
	
	private String PassWord;
	
	private String UserName;
	
	private int UpdateDataTime;

	public String getUserID() {
		return UserID;
	}

	public void setUserID(String userID) {
		UserID = userID;
	}

	public String getPassWord() {
		return PassWord;
	}

	public void setPassWord(String passWord) {
		PassWord = passWord;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public int getUpdateDataTime() {
		return UpdateDataTime;
	}

	public void setUpdateDataTime(int updateDataTime) {
		UpdateDataTime = updateDataTime;
	}
	

}
