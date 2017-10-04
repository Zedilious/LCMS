import javax.swing.*;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DBCreate {

	JFrame createDatabase;
	private JTextField tf_tableName;
	DBInteract interact = new DBInteract();
	
	/**
	 * Create the application.
	 */
	public DBCreate() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		createDatabase = new JFrame();
		createDatabase.setIconImage(Toolkit.getDefaultToolkit().getImage(DBCreate.class.getResource("/com/sun/java/swing/plaf/windows/icons/Computer.gif")));
		createDatabase.setTitle("Create Database");
		createDatabase.setBounds(100, 100, 250, 120);
		createDatabase.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		createDatabase.getContentPane().setLayout(null);
		
		JLabel lbltableName = new JLabel("Name of Table: ");
		lbltableName.setFont(new Font("Arial", Font.BOLD, 12));
		lbltableName.setBounds(10, 11, 94, 25);
		createDatabase.getContentPane().add(lbltableName);
		
		tf_tableName = new JTextField();
		tf_tableName.setFont(new Font("Arial", Font.BOLD, 12));
		tf_tableName.setBounds(114, 14, 110, 20);
		createDatabase.getContentPane().add(tf_tableName);
		tf_tableName.setColumns(10);
		
		JButton btntableCreate = new JButton("Create");
		btntableCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String name = tf_tableName.getText();
				String query = "Create Table " + name + "(id int);";
				
				interact.sendUpdate(query);
				createDatabase.dispose();
			}
		});
		btntableCreate.setFont(new Font("Arial", Font.BOLD, 12));
		btntableCreate.setBounds(114, 45, 110, 23);
		createDatabase.getContentPane().add(btntableCreate);
		
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
		
	}
}
