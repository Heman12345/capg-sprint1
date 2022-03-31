package in.capgproject.healthcare.domain;
import org.springframework.stereotype.Component;

import in.capgproject.healthcare.entities.User;
public class Login {
private boolean loginStatus;
	
	private User user;
	
	Login(){
		
	}
	
	

	/**
	 * @param loginStatus
	 * @param user
	 */
	public Login(boolean loginStatus, User user) {
		super();
		this.loginStatus = loginStatus;
		this.user = user;
	}



	
	/** 
	 * @return boolean
	 */
	public boolean isLoginStatus() {
		return loginStatus;
	}



	
	/** 
	 * @param loginStatus
	 */
	public void setLoginStatus(boolean loginStatus) {
		this.loginStatus = loginStatus;
	}



	
	/** 
	 * @return User
	 */
	public User getUser() {
		return user;
	}



	
	/** 
	 * @param user
	 */
	public void setUser(User user) {
		this.user = user;
	}

	
	
	/** 
	 * @return String
	 */
	public String getRole() {
		return user.getRole();
	}
	
}
