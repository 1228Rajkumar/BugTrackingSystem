package bugtracker.BugDetails;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;

import bugtracker.UserDetails.User;
import bugtracker.UserDetails.UserDbQueries;

public class BugController {
	
	private BugDbQueries bugDb;
	
	public BugController() {};
	
	public BugController(Bugs bug, User user) {
		this.bugDb= new BugDbQueries(bug, user);
	}
	
	public boolean isBugIdExists() {
		return bugDb.isBugIdExists();
	}
	public boolean isBugIdExistByUser(int userId, String role) {
		return bugDb.isBugIdExistsUser(userId,role);
	}
	
	public String createBugReport() {
	 return	bugDb.createBugReport();
	}
	
	public int getTotalBugCount() {
	 return	bugDb.getTotalBugCount();
	}
	
	public boolean assignBug(int DeveID) {
	 return	bugDb.assignBug(DeveID);
	}
	
	public boolean addSolution() {
	 return	bugDb.addSolution();
	}
	
	public boolean updateStatus() {
	 return	bugDb.updateStatus();
	}
	
	public boolean bugVerify() {
	 return	bugDb.updateStatus();
	}
	
	public List<Bugs> getBugReports(int UserId, String Role){
		List<Bugs> list = bugDb.getBugReports(UserId,Role);
		System.out.println(list.size());
		return list;
	}
	
	public String setSolvedDate() {
		Date currentDate = new Date();
		  SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		  return dateFormat.format(currentDate);
	}
	
	public String getModule(int bugId) {
		return bugDb.getModule(bugId);
	}
	
	public JSONArray findLeastBugDeveloper() {
		return bugDb.findLeastBugDeveloper();
	}
}
