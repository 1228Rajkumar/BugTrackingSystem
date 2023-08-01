package bugtracker.BugDetails;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ModelDriven;

import bugtracker.UserDetails.User;
import bugtracker.UserDetails.UserAction;
import netscape.javascript.JSObject;

public class BugAction implements ModelDriven<Bugs>, ServletRequestAware {

	private Bugs bug = new Bugs();
	private User user = new User();
	private BugController bugController;
	HttpServletRequest request;
	public BugAction() throws IOException {};

	PrintWriter out = ServletActionContext.getResponse().getWriter();
	
	@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;
		
	}
	
	public void setBug(Bugs bug) {
		this.bug = bug;
	}

	public String createBugReport() {
		bugController = new BugController(bug, user);
		String result = bugController.createBugReport();
		out.print(result);
		return result;

	}

	public void assignBug() {
		int DEVEID = Integer.parseInt(ServletActionContext.getRequest().getParameter("DeveloperId"));
		bugController = new BugController(bug, user);
		boolean result = bugController.assignBug(DEVEID);
		out.print(result);
	}

	public void addSolution() {
		bugController = new BugController(bug, user);
		boolean result = bugController.addSolution();
		out.print(result);
	}

	public void updateStatus() {
		bugController = new BugController(bug, user);
		out.print(bugController.updateStatus());
	}

	public void bugVerify() {
		bugController = new BugController(bug, user);
		out.print(bugController.updateStatus());
	}

	public void isBugIdExists() {
		bugController = new BugController(bug, user);
		out.print(bugController.isBugIdExists());
	}
	public void isBugIdExistByUser() {
		int UserID = Integer.parseInt(ServletActionContext.getRequest().getParameter("userId"));
		String role = ServletActionContext.getRequest().getParameter("Role");
		bugController = new BugController(bug, user);
		out.print(bugController.isBugIdExistByUser(UserID,role));
	}
	
	public void getBugReports() {
		int UserID = Integer.parseInt(ServletActionContext.getRequest().getParameter("UserID"));
		String role = ServletActionContext.getRequest().getParameter("Role");
		bugController = new BugController(bug, user);
		List<Bugs> bugReports = bugController.getBugReports(UserID,role);
		JSONArray bugJsObject = new JSONArray(bugReports);
		out.print(bugJsObject);
	}
	
	public void LeastBugDeveloper() {
		bugController = new BugController(bug, user);
		JSONArray jsonArray = bugController.findLeastBugDeveloper();
		out.print(jsonArray);
	}
	
	@Override
	public Bugs getModel() {
		System.out.println("bugModel");
		return bug;
	}

	

//   public String getTotalBugCount() {
//      bugController = new BugController(bug, user);
//      int bugCount = bugController.getTotalBugCount();
//      // Set the bugCount value to be used in the JSP
//      // You can use ActionContext to set the value
//      return SUCCESS;
//   }

}
