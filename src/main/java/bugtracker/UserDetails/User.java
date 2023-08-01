package bugtracker.UserDetails;


public class User {
	private String name;
	private String password;
	private int age;
	private String location;
	private String Role;
	private int UserID;
	private int DeveloperId;
	private String deleoperRole;	
	private String module;
	
	public String getName() {
		System.out.println("name"+name);
		return name;
	}

	public void setName(String name) {
		System.out.println("setname :"+name);
		this.name = name;
	}
	public String getPassword() {
		System.out.println("password"+password);
		return password;
	}

	public void setPassword(String password) {
		this.password = password;}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getRole() {
		return Role;
	}

	public void setRole(String role) {
		Role = role;
	}

	public int getUserID() {
		return UserID;
	}

	public void setUserID(int userID) {
		UserID = userID;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}
	public int getDeveloperId() {
		return DeveloperId;
	}

	public void setDeveloperId(int developerId) {
		DeveloperId = developerId;
	}

	public String getDeleoperRole() {
		return deleoperRole;
	}

	public void setDeleoperRole(String deleoperRole) {
		this.deleoperRole = deleoperRole;
	}


}
