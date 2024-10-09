package model.entity;

<<<<<<< HEAD
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
	

=======
import java.io.Serializable;
import java.sql.Timestamp;

public class UserBean implements Serializable{
	private String userId;
	private String password;
	private String userName;
	private Timestamp updateDatetime;
	
	public UserBean() {
		super();
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Timestamp getUpdateDatetime() {
		return updateDatetime;
	}

	public void setUpdateDatetime(Timestamp updateDatetime) {
		this.updateDatetime = updateDatetime;
	}
	
	
>>>>>>> adc43556da9dee509cc485b54777c8404ed845b7
}
