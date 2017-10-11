import javax.swing.*;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DBCreate {

	JFrame createDatabase;
	private JTextField tf_tableName;
	DBInteract interact = new DBInteract();
	private JTextField tf_PColumn;
	
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
		createDatabase.setBounds(100, 100, 270, 170);
		createDatabase.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		createDatabase.getContentPane().setLayout(null);
		
		JLabel lbltableName = new JLabel("Name of Table: ");
		lbltableName.setFont(new Font("Arial", Font.BOLD, 12));
		lbltableName.setBounds(10, 11, 94, 25);
		createDatabase.getContentPane().add(lbltableName);
		
		tf_tableName = new JTextField();
		tf_tableName.setFont(new Font("Arial", Font.BOLD, 12));
		tf_tableName.setBounds(130, 13, 110, 20);
		createDatabase.getContentPane().add(tf_tableName);
		tf_tableName.setColumns(10);
		
		JButton btntableCreate = new JButton("Create");
		btntableCreate.setFont(new Font("Arial", Font.BOLD, 12));
		btntableCreate.setBounds(130, 94, 110, 23);
		createDatabase.getContentPane().add(btntableCreate);
		
		JLabel lblPrimaryColumn = new JLabel("Primary Column:");
		lblPrimaryColumn.setFont(new Font("Arial", Font.BOLD, 12));
		lblPrimaryColumn.setBounds(10, 40, 94, 14);
		createDatabase.getContentPane().add(lblPrimaryColumn);
		
		tf_PColumn = new JTextField();
		tf_PColumn.setFont(new Font("Arial", Font.BOLD, 12));
		tf_PColumn.setBounds(130, 38, 110, 20);
		createDatabase.getContentPane().add(tf_PColumn);
		tf_PColumn.setColumns(10);
		
		JLabel lblDataType = new JLabel("Data Type:");
		lblDataType.setFont(new Font("Arial", Font.BOLD, 12));
		lblDataType.setBounds(10, 65, 94, 14);
		createDatabase.getContentPane().add(lblDataType);
		
		JComboBox<Object> comboBox = new JComboBox<Object>();
		comboBox.setModel(new DefaultComboBoxModel<Object>(new String[] {"CHAR", "VARCHAR", "TEXT", "LONGTEXT", "BOOLEAN", "", "SMALLINT", "INTEGER", "DECIMAL", "NUMERIC", "REAL", "FLOAT", "DOUBLE", "", "DATE", "DATETIME", "TIME", "TIMESTAMP", "YEAR"}));
		comboBox.setFont(new Font("Arial", Font.BOLD, 12));
		comboBox.setBounds(130, 63, 110, 20);
		createDatabase.getContentPane().add(comboBox);
		

		btntableCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String tname = tf_tableName.getText();
				String cname = tf_PColumn.getText();
				String dtype = (String) comboBox.getSelectedItem();
				String query = "Create Table " + tname + "(" + cname + " " + dtype +");";
				
				interact.sendUpdate(query);
				createDatabase.dispose();
			}
		});
		
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
