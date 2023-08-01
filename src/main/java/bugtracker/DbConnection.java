package bugtracker;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnection {
	private static final String URL="jdbc:mysql://localhost:3306/bugTrackingSystem";
	private static final String username ="rajava";
	private static final String password ="Java@123";
	
	public static PreparedStatement preparedStatement(StringBuilder query) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection(URL,username,password);
		Statement stmt = conn.createStatement();
        PreparedStatement statement = conn.prepareStatement(String.valueOf(query));
//		stmt.close();
//		conn.close();
		return statement;
	}
}


