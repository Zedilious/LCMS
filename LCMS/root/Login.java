import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class Login {

	private JFrame login_portal;
	private JTextField tf_uname;
	Connection connection;
	PreparedStatement statement;
	ResultSet set;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.login_portal.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		login_portal = new JFrame();
		login_portal.setTitle("LC Management Login");
		login_portal.getContentPane().setFont(new Font("Arial", Font.BOLD, 12));
		login_portal.setForeground(Color.LIGHT_GRAY);
		login_portal.setFont(new Font("Arial", Font.BOLD, 12));
		login_portal.setResizable(false);
		login_portal.setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/com/sun/java/swing/plaf/windows/icons/Computer.gif")));
		login_portal.setBounds(100, 100, 300, 150);
		login_portal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		login_portal.getContentPane().setLayout(null);

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JLabel lbl_login = new JLabel("Please Enter Your Username");
		lbl_login.setFont(new Font("Arial", Font.BOLD, 12));
		lbl_login.setBounds(60, 11, 175, 40);
		login_portal.getContentPane().add(lbl_login);
		
		JLabel lbl_Uname = new JLabel("Username:");
		lbl_Uname.setFont(new Font("Arial", Font.BOLD, 12));
		lbl_Uname.setBounds(60, 54, 65, 14);
		login_portal.getContentPane().add(lbl_Uname);
		
		tf_uname = new JTextField();
		tf_uname.setBounds(135, 52, 100, 20);
		login_portal.getContentPane().add(tf_uname);
		tf_uname.setColumns(10);
		
		JButton btn_login = new JButton("Login");
		btn_login.setFont(new Font("Arial", Font.BOLD, 12));
		btn_login.setBounds(135, 83, 99, 23);
		login_portal.getContentPane().add(btn_login);
		
		try 
		{
			Class.forName("org.h2.Driver");
			connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
			
		} 
		catch (ClassNotFoundException | SQLException e) 
		{
			JOptionPane.showMessageDialog(null, "Connection unsuccessfull. Contact Customer Support.");
			login_portal.dispose();
		}

		btn_login.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				String name = tf_uname.getText();
				String query1 = "SELECT * FROM user WHERE uname=?;";			
			
				try 
				{
					statement = connection.prepareStatement(query1);
					statement.setString(1, name);
					
					set = statement.executeQuery();
					if(set.next()) {
						login_portal.dispose();
						
						connection.close();
						statement.close();
						set.close();

						LCMSmain window = new LCMSmain();
						window.frmLesChampsManagement.setVisible(true);
						
					}
					else 
					{
						JOptionPane.showMessageDialog(null, "Failed");
					}	
				} 
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
		});
	}
}