package bugtracker.BugDetails;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import bugtracker.DbConnection;
import bugtracker.UserDetails.User;
import bugtracker.UserDetails.UserDbQueries;

public class BugDbQueries {
	private Bugs bug;
	private User user;
	private UserDbQueries userDb;

	public BugDbQueries(Bugs bug, User user) {
		this.bug = bug;
		this.user = user;
		this.userDb = new UserDbQueries(user);
	}

	public boolean isBugIdExists() {
		StringBuilder query = new StringBuilder("select * from bug where Bug_Id = ? ");
		try (PreparedStatement statement = DbConnection.preparedStatement(query)) {
			statement.setInt(1, bug.getBugId());
			ResultSet rs1 = statement.executeQuery();
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

	public boolean isBugIdExistsUser(int userId, String Role) {
		StringBuilder query = new StringBuilder("select * from bug where Bug_Id = ? AND ");
		String role = Role;

		if ("tester".equalsIgnoreCase(role)) {
			query.append("RaisedBy = ?");
		} else if ("developer".equalsIgnoreCase(role)) {
			query.append("AssignedTo = ?");
		}
		try (PreparedStatement statement = DbConnection.preparedStatement(query)) {
			statement.setInt(1, bug.getBugId());
			System.out.println("isBugID :" + bug.getBugId());
			statement.setInt(2, userId);

			ResultSet rs1 = statement.executeQuery();
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

	public String createBugReport() {
		Date currentDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		String formattedDate = dateFormat.format(currentDate);
		StringBuilder query = new StringBuilder(
				"insert into bug (Bug_Id, Name, Description, Module, Priority, Status, RaisedBy, RaisedDate)  values(?,?,?,?,?,?,?,?)");
		try (PreparedStatement statement = DbConnection.preparedStatement(query)) {
			statement.setInt(1, bug.getBugId());
			statement.setString(2, bug.getBugName());
			statement.setString(3, bug.getDescription());
			statement.setString(4, bug.getModule());
			statement.setString(5, bug.getPriority());
			statement.setString(6, "Open");
			statement.setInt(7, bug.getRaisedById());
			statement.setString(8, formattedDate);
//			boolean execute = statement.execute();
//			if (execute) {
//				return true;
//			}
//			return false;	
			statement.execute();
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	public String getModule(int bugId) {
		StringBuilder query = new StringBuilder("select Module from bug where Bug_Id = ?");
		try (PreparedStatement statement = DbConnection.preparedStatement(query)) {
			statement.setInt(1, bugId);
			ResultSet rs1 = statement.executeQuery();
			if (rs1.next()) {
				String name = rs1.getString("Module");
				rs1.close();
				return name;
			}
			rs1.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean assignBug(int DeveID) {
		StringBuilder query = new StringBuilder("UPDATE bug SET AssignedTo = ? WHERE Bug_Id = ?");
		try (PreparedStatement statement = DbConnection.preparedStatement(query)) {
			statement.setInt(1, DeveID);

			statement.setInt(2, bug.getBugId());
			statement.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean addSolution() {
		StringBuilder query = new StringBuilder("UPDATE bug SET solution = ? WHERE Bug_Id = ?");
		try (PreparedStatement statement = DbConnection.preparedStatement(query)) {
			statement.setString(1, bug.getSolution());
			statement.setInt(2, bug.getBugId());
			statement.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean updateStatus() {
		StringBuilder query = new StringBuilder("UPDATE bug SET status = ? WHERE Bug_Id = ?");
		try (PreparedStatement statement = DbConnection.preparedStatement(query)) {
			statement.setString(1, bug.getStatus());
			statement.setInt(2, bug.getBugId());
			statement.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public int getTotalBugCount() {
		StringBuilder query = new StringBuilder("SELECT COUNT(*) FROM bug");
		try (PreparedStatement statement = DbConnection.preparedStatement(query)) {
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public List<Bugs> getBugReports(int userId, String Role) {
		List<Bugs> bugReports = new ArrayList<>();
		StringBuilder query = new StringBuilder("SELECT * FROM bug WHERE ");
		String role = Role;

		if ("tester".equalsIgnoreCase(role)) {
			query.append("RaisedBy = ?");
		} else if ("developer".equalsIgnoreCase(role)) {
			query.append("AssignedTo = ?");
		}
		try (PreparedStatement statement = DbConnection.preparedStatement(query)) {
	
			statement.setInt(1, userId);
		
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Bugs bug = new Bugs();
				bug.setBugId(resultSet.getInt("Bug_Id"));
				bug.setBugName(resultSet.getString("Name"));
				bug.setDescription(resultSet.getString("Description"));
				bug.setPriority(resultSet.getString("Priority"));
				bug.setStatus(resultSet.getString("Status"));
				bug.setSolution(resultSet.getString("Solution"));
				bug.setModule(resultSet.getString("Module"));
				bug.setRaisedByName(userDb.getName(resultSet.getInt("RaisedBy")));
				bug.setAssignedToName(userDb.getName(resultSet.getInt("AssignedTo")));
				bug.setRaisedDate(resultSet.getString("RaisedDate"));
				bug.setSolvedDate(resultSet.getString("SolvedDate"));
				bugReports.add(bug);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bugReports;
	}

	public JSONArray findLeastBugDeveloper() {
		JSONArray jsonArray = new JSONArray();
		StringBuilder query = new StringBuilder("select Module from bug WHERE Bug_Id = ?");
		try (PreparedStatement statement = DbConnection.preparedStatement(query)) {
			System.out.println("bug:::"+bug.getBugId());
			statement.setInt(1, bug.getBugId());
			ResultSet resultSet = statement.executeQuery();
			String module = "";
			if (resultSet.next()) {
				module = resultSet.getString("Module");
			}
			System.out.println("module::"+module);
			StringBuilder query1 = new StringBuilder("SELECT COUNT(bug.Bug_Id) AS bugCount,Developer.module, Developer.User_ID FROM Developer LEFT JOIN bug ON Developer.User_ID = bug.AssignedTo and Developer.module = ? GROUP BY Developer.User_ID, Developer.module HAVING Developer.module = ? ORDER BY bugCount ASC LIMIT 3");
			PreparedStatement statement1 = DbConnection.preparedStatement(query1);
			statement1.setString(1, module);
			statement1.setString(2, module);
			ResultSet rs = statement1.executeQuery();
			while (rs.next()) {
				JSONObject jsonObject = new JSONObject();
				StringBuilder query2 = new StringBuilder("select User_ID, Name, Location from User where User_ID = ?");
				PreparedStatement statement2 = DbConnection.preparedStatement(query2);
				statement2.setInt(1, rs.getInt("user_ID"));
				ResultSet rs1 = statement2.executeQuery();
				while (rs1.next()) {
					jsonObject.put("UserId", rs1.getInt("User_ID"));
					jsonObject.put("Name", rs1.getString("Name"));
					jsonObject.put("Location", rs1.getString("Location"));
				}
				jsonObject.put("bugCount", rs.getInt("bugCount"));

				jsonArray.put(jsonObject);
				rs1.close();

			}
			rs.close();
			return jsonArray;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
