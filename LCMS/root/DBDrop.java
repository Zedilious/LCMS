import javax.swing.*;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DBDrop {

	JFrame dropDatabase;
	private JTextField tf_tableName;
	DBInteract interact = new DBInteract();
	
	/**
	 * Create the application.
	 */
	public DBDrop() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		dropDatabase = new JFrame();
		dropDatabase.setIconImage(Toolkit.getDefaultToolkit().getImage(DBDrop.class.getResource("/com/sun/java/swing/plaf/windows/icons/Computer.gif")));
		dropDatabase.setTitle("Delete Database");
		dropDatabase.setBounds(100, 100, 250, 120);
		dropDatabase.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		dropDatabase.getContentPane().setLayout(null);
		
		JLabel lbltableName = new JLabel("Name of Table: ");
		lbltableName.setFont(new Font("Arial", Font.BOLD, 12));
		lbltableName.setBounds(10, 11, 94, 25);
		dropDatabase.getContentPane().add(lbltableName);
		
		tf_tableName = new JTextField();
		tf_tableName.setFont(new Font("Arial", Font.BOLD, 12));
		tf_tableName.setBounds(114, 14, 110, 20);
		dropDatabase.getContentPane().add(tf_tableName);
		tf_tableName.setColumns(10);
		
		JButton btntableDrop = new JButton("Delete");
		btntableDrop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String name = tf_tableName.getText();
				String query = "DROP TABLE IF EXISTS " + name + ";";
				
				interact.sendUpdate(query);
				dropDatabase.dispose();
			}
		});
		btntableDrop.setFont(new Font("Arial", Font.BOLD, 12));
		btntableDrop.setBounds(114, 45, 110, 23);
		dropDatabase.getContentPane().add(btntableDrop);
		
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
