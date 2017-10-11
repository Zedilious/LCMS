import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
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
	ArrayList<String> names = new ArrayList<String>();
	ArrayList<JButton> buttons = new ArrayList<JButton>();
	int xbound;
	DBInteract interact= new DBInteract();;
	
	/**
	 * Create the application.
	 * @throws SQLException 
	 */
	public LCMSmain() throws SQLException {
		initialize();
	}

	/**
	 * Initialise the contents of the frame.
	 */
	private void initialize() throws SQLException {
		frmLesChampsManagement = new JFrame();
		frmLesChampsManagement.setIconImage(Toolkit.getDefaultToolkit().getImage(LCMSmain.class.getResource("/com/sun/java/swing/plaf/windows/icons/Computer.gif")));
		frmLesChampsManagement.setTitle("Les Champs Management System");
		frmLesChampsManagement.setBounds(100, 100, 380, 300);
		frmLesChampsManagement.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLesChampsManagement.getContentPane().setLayout(null);
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
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
		btnDBCreate.setFont(new Font("Arial", Font.PLAIN, 12));
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
		btnReport.setFont(new Font("Arial", Font.PLAIN, 12));
		btnReport.setBounds(52, 165, 90, 25);
		frmLesChampsManagement.getContentPane().add(btnReport);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(195, 115, 150, 2);
		frmLesChampsManagement.getContentPane().add(separator_1);
		
		JButton btnDropTbl = new JButton("Delete");
		btnDropTbl.setFont(new Font("Arial", Font.PLAIN, 12));
		btnDropTbl.setBounds(52, 201, 89, 23);
		frmLesChampsManagement.getContentPane().add(btnDropTbl);
		
		findTables();

		btnDBCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DBCreate newDB = new DBCreate();
				newDB.createDatabase.setVisible(true);
				newDB.createDatabase.addWindowListener(new WindowAdapter() {
					public void windowClosed(WindowEvent e) {
						findTables();
					}
					
				});
			}
		});
		

		btnDropTbl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DBDrop dropDB = new DBDrop();
				dropDB.dropDatabase.setVisible(true);
				dropDB.dropDatabase.addWindowListener(new WindowAdapter() {
					public void windowClosed(WindowEvent e) {
						try {
							frmLesChampsManagement.dispose();
							LCMSmain main = new LCMSmain();
							main.frmLesChampsManagement.setVisible(true);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						
					}
					
				});
			}
		});
	}

	private void findTables()
	{
		frmLesChampsManagement.repaint();
		String query1 = "SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_TYPE = 'TABLE';";
		int numTables = 0;
		
		try 
		{		
			set = interact.sendQuery(query1);
			while(set.next()) {
				String table = set.getString("TABLE_NAME");
				
				boolean check = false;
				
				for(int i = 0; i < names.size(); i++) {
					if(table == names.get(i)) {
						check = true;
						numTables++;
					}
				}
				
				if(!check) {
					//System.out.println("error check 1: " + table);
					names.add(table);
					numTables++;
				}
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}

		createBtns(numTables);
	}
	
	private void createBtns(int numTables)
	{
		xbound = 130;
			//System.out.println("num of tables: "  + numTables);
		for (int i = 0; i < numTables; ++i)
		{
			String tabName = names.get(i);
				//System.out.println("error check 1.1: tab name assigned to btn: " + tabName);
		    JButton btn = new JButton(tabName);
		    
		    btn.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		            TableView view;
					try {
						view = new TableView(tabName);
		            view.frmViewTable.setVisible(true);
					} catch (SQLException e1) {
						e1.printStackTrace();
					} 
		        }
		    });

				//System.out.println("error check 1.2: add call");
		    add(btn, xbound);
		    frmLesChampsManagement.repaint();
		}
		
	}
	
	private void add(JButton btn, int x)
	{
			//System.out.println("error check 2: add start");
		btn.setBounds(226, x, 90, 25);
		frmLesChampsManagement.getContentPane().add(btn);
		xbound += 35;
			//System.out.println("error check 3: add finish");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}

//SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_TYPE = 'TABLE';