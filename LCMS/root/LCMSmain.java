import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.Font;

public class LCMSmain {

	private JFrame frmLesChampsManagement;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LCMSmain window = new LCMSmain();
					window.frmLesChampsManagement.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LCMSmain() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLesChampsManagement = new JFrame();
		frmLesChampsManagement.setIconImage(Toolkit.getDefaultToolkit().getImage(LCMSmain.class.getResource("/com/sun/java/swing/plaf/windows/icons/Computer.gif")));
		frmLesChampsManagement.setTitle("Les Champs Management System");
		frmLesChampsManagement.setBounds(100, 100, 800, 500);
		frmLesChampsManagement.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLesChampsManagement.getContentPane().setLayout(null);
		
		JLabel lblMainMenu = new JLabel("Main Menu");
		lblMainMenu.setFont(new Font("Arial", Font.BOLD, 18));
		lblMainMenu.setBounds(10, 11, 100, 30);
		frmLesChampsManagement.getContentPane().add(lblMainMenu);
	}
}
