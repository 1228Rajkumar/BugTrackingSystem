package bugtracker.UserDetails;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import com.mysql.cj.protocol.ValueDecoder;

import bugtracker.DbConnection;

public class UserDbQueries {
	
private User user;
	
	public UserDbQueries(User user) {
		this.user = user;
	}
	
	public Map<String, String>  signUp() {
		StringBuilder query = new StringBuilder("insert into User (Name, Password, Age, Location, Role) values(?,?,?,?,?)");

		try(PreparedStatement statement = DbConnection.preparedStatement(query)) {
			statement.setString(1,user.getName());
			statement.setString(2, user.getPassword());
			statement.setInt(3, user.getAge());
			statement.setString(4, user.getLocation());
			statement.setString(5, user.getRole());
			statement.execute();
			return loginCheck(); 
		} catch (Exception e) {
			e.printStackTrace();
			return loginCheck();
		}
	}

	public boolean isNameExists() {
			StringBuilder query = new StringBuilder("select Role from User where Name = ? ");
			try (PreparedStatement statement =DbConnection.preparedStatement(query)) {
	        	statement.setString(1, user.getName());
	        	ResultSet rs1 =statement.executeQuery();
			if (rs1.next()) {
				rs1.close();
				return true;
			}
			rs1.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
			return false;
	}
	
	public boolean isDeveloperExists() {
		StringBuilder query = new StringBuilder("select module from Developer where user_ID = ? ");
		try (PreparedStatement statement =DbConnection.preparedStatement(query)) {
        	statement.setInt(1, user.getUserID());
        	System.out.println("DeveID::"+user.getUserID());
        	ResultSet rs1 =statement.executeQuery();
		if (rs1.next()) {
			rs1.close();
			return true;
		}
		rs1.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
		return false;
}

	public Map<String, String> loginCheck() {
		Map<String, String> detailsMap = new HashMap<>();
			StringBuilder query = new StringBuilder(
					"select User_ID, Role from User where Name = ? AND password= ? ");
			
			try (PreparedStatement statement =DbConnection.preparedStatement(query)) {
	        	statement.setString(1, user.getName());
	        	statement.setString(2, user.getPassword());
	        	ResultSet rs1 =statement.executeQuery();
			if (rs1.next()) {
				int UserID = rs1.getInt("User_ID");
				String role = rs1.getString("Role");
				user.setUserID(UserID);
				user.setRole(role);
				detailsMap.put("UserId", String.valueOf(UserID));
				detailsMap.put("Role", role);
				rs1.close();
				System.out.println("Role:"+user.getRole());
				return detailsMap;
			}
			rs1.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
			return detailsMap;
	}

	public boolean setDeveloperModule() {
		StringBuilder query = new StringBuilder("insert into Developer values(?,?)");
		try(PreparedStatement statement= DbConnection.preparedStatement(query)) {
			System.out.println("user ::"+user.getUserID());
			statement.setInt(1, user.getUserID());
			statement.setString(2, user.getModule());
			statement.execute();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public String getID() {
		 StringBuilder query = new StringBuilder("select User_ID, Role from User where Name = ?");    
      try (PreparedStatement statement =DbConnection.preparedStatement(query)) {
          statement.setString(1, user.getName());
          ResultSet rs1 =statement.executeQuery();
          if (rs1.next()) {
       	   	int deveId = rs1.getInt("User_ID");
       	   	String role = rs1.getString("Role");
       	   	if(role.equalsIgnoreCase("Developer")) {
       	   		user.setDeveloperId(deveId);
       	   	}else if (role.equalsIgnoreCase("Tester")) {
				user.setUserID(deveId);
			}
				user.setDeleoperRole(role);
				return "Success";	
			}
			rs1.close();
          
      } catch (Exception e) {
          e.printStackTrace();  
      }
      return "error";
  }
	
	public String getName(int ID) {
		 StringBuilder query = new StringBuilder("select Name from User where User_ID = ?");    
     try (PreparedStatement statement =DbConnection.preparedStatement(query)) {
         statement.setInt(1, ID);
         ResultSet rs1 =statement.executeQuery();
         if (rs1.next()) {
      	   	String name= rs1.getString("Name");
				rs1.close();
				return name;
			}
			rs1.close();
         
     } catch (Exception e) {
         e.printStackTrace();
     }
 	return null;
 }
	

}
