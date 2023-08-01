package bugtracker.UserDetails;

import java.util.Map;

public class UserController {
	
	private UserDbQueries userDb;
	
	public UserController(User user) {
		this.userDb = new UserDbQueries(user);
	}
	 
	public Map<String, String> signUp() {
	 return	userDb.signUp();
	}
	
	public Map<String, String> logIn() {
		 return userDb.loginCheck();
	}
	
	public boolean isNameExists() {
		 return userDb.isNameExists();
	}
	
	public String getID() {
		return userDb.getID();
	}
	
	public boolean setDeveloperModule() {
		return userDb.setDeveloperModule();
	}
	
	public boolean isDeveloperExists() {
		 return userDb.isDeveloperExists();
	}
}
