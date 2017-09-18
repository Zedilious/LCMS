import java.sql.*;

public class DBInteract {

	Connection connection;
	PreparedStatement statement;
	ResultSet set;
	
		public DBInteract(){
			try {
				Class.forName("org.h2.Driver");
				connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}
