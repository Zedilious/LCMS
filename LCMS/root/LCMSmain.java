import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JSeparator;

public class LCMSmain implements ActionListener {

	public JFrame frmLesChampsManagement;
	Connection connection;
	PreparedStatement statement;
	ResultSet set;
	ArrayList<String> names;
	int xbound;
	
	/**
	 * Create the application.
	 */
	public LCMSmain() {
		initialize();
	}

	/**
	 * Initialise the contents of the frame.
	 */
	private void initialize() {
		frmLesChampsManagement = new JFrame();
		frmLesChampsManagement.setIconImage(Toolkit.getDefaultToolkit().getImage(LCMSmain.class.getResource("/com/sun/java/swing/plaf/windows/icons/Computer.gif")));
		frmLesChampsManagement.setTitle("Les Champs Management System");
		frmLesChampsManagement.setBounds(100, 100, 800, 500);
		frmLesChampsManagement.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLesChampsManagement.getContentPane().setLayout(null);
		
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
		
		JLabel lblMainMenu = new JLabel("Database Management");
		lblMainMenu.setFont(new Font("Arial", Font.BOLD, 18));
		lblMainMenu.setBounds(75, 26, 199, 30);
		frmLesChampsManagement.getContentPane().add(lblMainMenu);
		
		JLabel lblCreate = new JLabel("Create ");
		lblCreate.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreate.setFont(new Font("Arial", Font.BOLD, 12));
		lblCreate.setBounds(52, 82, 85, 25);
		frmLesChampsManagement.getContentPane().add(lblCreate);
		
		JButton btnDBCreate = new JButton("Database");
		btnDBCreate.setBounds(52, 130, 90, 25);
		frmLesChampsManagement.getContentPane().add(btnDBCreate);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(20, 115, 150, 25);
		frmLesChampsManagement.getContentPane().add(separator);
		
		JLabel lblView = new JLabel("View");
		lblView.setHorizontalAlignment(SwingConstants.CENTER);
		lblView.setFont(new Font("Arial", Font.BOLD, 12));
		lblView.setBounds(226, 82, 85, 25);
		frmLesChampsManagement.getContentPane().add(lblView);
		
		JButton btnReport = new JButton("Report");
		btnReport.setBounds(52, 165, 90, 25);
		frmLesChampsManagement.getContentPane().add(btnReport);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(195, 115, 150, 2);
		frmLesChampsManagement.getContentPane().add(separator_1);
		
		names = new ArrayList<String>(); 
		int numTables = 0;
		try 
		{
			Class.forName("org.h2.Driver");
			connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
			String query1 = "SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_TYPE = 'TABLE';";
			try 
			{
				statement = connection.prepareStatement(query1);
				
				set = statement.executeQuery();
				if(set.next()) {
					String table = set.getString("TABLE_NAME");
					names.add(table);
					numTables++;
				}
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
			//for(int i = 0; i < names.size(); i++) System.out.println("item " + (i+1) + ": " + names.get(i));
		} 
		catch (ClassNotFoundException | SQLException e) 
		{
			
		}
		
		
		JButton buttons[] = new JButton[numTables];
		xbound = 130;
		
		for (int i = 0; i < buttons.length; ++i)
		{
			String tabName = names.get(i);
		    JButton btn = new JButton(tabName);
		    
		    btn.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		            System.out.println(tabName);
		        }
		    });
		    
		    add(btn, xbound);
		    buttons[i] = btn;
		}
		

		btnDBCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DBCreate newDB = new DBCreate();
				newDB.createDatabase.setVisible(true);
			}
		});
	}

	private void add(JButton btn, int x)
	{
		btn.setBounds(226, x, 90, 25);
		frmLesChampsManagement.getContentPane().add(btn);
		xbound += 35;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}

//SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_TYPE = 'TABLE';