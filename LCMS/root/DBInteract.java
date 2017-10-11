import java.sql.*;

import javax.swing.JOptionPane;

public class DBInteract {

	Connection connection;
	PreparedStatement statement;
	ResultSet set;
	
		public DBInteract(){
			try {
				Class.forName("org.h2.Driver");
				connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
			} catch (ClassNotFoundException | SQLException e) {
				JOptionPane.showMessageDialog(null, "Connection unsuccessfull. Contact Customer Support.");
				System.exit(0);
			}
		}
		
		public ResultSet sendQuery(String query)
		{
			try 
			{ 
				statement = connection.prepareStatement(query);
				set = statement.executeQuery();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
			return set;
		}
		
		public void sendUpdate(String query)
		{
			try 
			{
				statement = connection.prepareStatement(query);
				statement.execute();
			} 
			catch (SQLException e) 
			{
				/*JOptionPane.showMessageDialog(null,
				    "There was an error with your request!",
				    "",
				    JOptionPane.ERROR_MESSAGE);
				*/
				
				//look into using .getErrorCode() and doing custom error returns.
				System.out.println(e.getMessage());
			}
		}
		
		public void destroy()
		{
			try {
				connection.close();
				statement.close();
				set.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
}
