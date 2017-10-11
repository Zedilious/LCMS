import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class TableView {

	public JFrame frmViewTable;
	DBInteract interact = new DBInteract();
	private JTable table;

	/**
	 * Create the application.
	 * @param tabName 
	 * @throws SQLException 
	 */
	public TableView(String tabName) throws SQLException {
		initialize(tabName);
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 */
	private void initialize(String tname) throws SQLException{
		frmViewTable = new JFrame();
		frmViewTable.setTitle("Table View");
		frmViewTable.setIconImage(Toolkit.getDefaultToolkit().getImage(TableView.class.getResource("/com/sun/java/swing/plaf/windows/icons/Computer.gif")));
		frmViewTable.setFont(new Font("Arial", Font.BOLD, 12));
		frmViewTable.getContentPane().setFont(new Font("Arial", Font.BOLD, 12));
		frmViewTable.setBounds(100, 100, 700, 400);
		frmViewTable.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmViewTable.getContentPane().setLayout(null);
		
		JScrollPane spTablePain = new JScrollPane();
		spTablePain.setBounds(10, 291, 665, -250);
		frmViewTable.getContentPane().add(spTablePain);
		
		table = new JTable();
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		table.setFont(new Font("Arial", Font.BOLD, 12));
		table.setBounds(10, 291, 665, -250);
		//frmViewTable.getContentPane().add(table);
		spTablePain.add(table);
		
		
		JLabel lblViewingTable = new JLabel("Viewing Table:");
		lblViewingTable.setBounds(10, 11, 82, 14);
		lblViewingTable.setFont(new Font("Arial", Font.BOLD, 12));
		frmViewTable.getContentPane().add(lblViewingTable);
		
		JLabel lblTable = new JLabel("");
		lblTable.setBounds(102, 12, 46, 14);
		lblTable.setToolTipText("Shows the name of the selected table");
		lblTable.setText(tname);
		lblTable.setFont(new Font("Arial", Font.BOLD, 12));
		frmViewTable.getContentPane().add(lblTable);
		
		ResultSet rs = interact.sendQuery("SELECT * FROM " + tname + ";");
		
		ResultSetMetaData rsmd = rs.getMetaData();
		   int columnsNumber = rsmd.getColumnCount();
		   while (rs.next()) {
		       for (int i = 1; i <= columnsNumber; i++) {
		           if (i > 1) System.out.print(",  ");
		           String columnValue = rs.getString(i);
		           System.out.print(rsmd.getColumnName(i) + " " + columnValue);
		       }
		       System.out.println("");
		   }
        
		interact.destroy();
	}
}
