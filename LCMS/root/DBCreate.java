import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JScrollPane;

public class DBCreate {

	JFrame createDatabase;
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
		createDatabase.setTitle("Create Database");
		createDatabase.setBounds(100, 100, 450, 300);
		createDatabase.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createDatabase.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 25, 414, 225);
		createDatabase.getContentPane().add(scrollPane);
		
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
		
	}
}
