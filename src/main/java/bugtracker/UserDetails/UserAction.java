package bugtracker.UserDetails;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ModelDriven;

import bugtracker.BugDetails.BugAction;


public class UserAction implements ModelDriven<User>, ServletRequestAware{
    private User user = new User();
    private UserController userController ;
    HttpServletRequest request;
    public UserAction() throws IOException{};
    PrintWriter out = ServletActionContext.getResponse().getWriter();
    @Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;
		
	}
    
    public void signUp() {
        userController = new UserController(user);
        Map<String, String> result = userController.signUp();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("detaile", result);
        out.print(jsonObject);
    }

    public void LogIn(){
        userController = new UserController(user);
        Map<String, String> result = userController.logIn();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("detaile", result);
        out.print(jsonObject);
       }
    
    public void isNameExists() {
        userController = new UserController(user);
       boolean result = userController.isNameExists();
       out.print(result);
       }
    
    public void isDeveloperExists() {
        userController = new UserController(user);
       boolean result = userController.isDeveloperExists();
       out.print(result);
       }
    
    public void getID() {
    	userController = new UserController(user);
    	String result = userController.getID();
    	out.print(result);
    }
    
    public void setModule() {
    	userController = new UserController(user);
    	boolean result = userController.setDeveloperModule();
    	out.print(result);
    }
	@Override
	public User getModel() {
		return user;
		  
	}


}

