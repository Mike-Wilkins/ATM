import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
//import javax.swing.JPasswordField;
import javax.swing.JTextField;

//import com.mysql.jdbc.authentication.MysqlClearPasswordPlugin;

public class MainFrame extends JFrame {

	public MainFrame() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		
		super("ATM");
		
		JPanel login = new JPanel(new GridBagLayout());
		
	/* -----------------------------------------------*/
	/*             Create login layout 				  */
	/* 			 - Enter PIN Number label			  */
	/* 			 - Text field to enter PIN			  */
	/* 			 - Enter button						  */
	/* -----------------------------------------------*/
		
		JLabel label = new JLabel("Enter PIN Number");
		label.setFont(new Font("Arial", Font.PLAIN, 18));
		
		JTextField text = new JTextField(10);
		//JPasswordField text = new JPasswordField();
		text.setPreferredSize(new Dimension(100, 40));
		text.setHorizontalAlignment(JTextField.CENTER);
		
		JButton button = new JButton("Enter");
		button.setPreferredSize(new Dimension(100, 40));
		button.setFont(new Font("Arial", Font.PLAIN, 18));
		
	/* -----------------------------------------------------------*/
		
		button.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
				String myPIN = text.getText();				// <--- This a bad way to store a PIN. Should use char[] instead of String 
				//char[] myPIN = text.getPassword();
				
				try {
					ATMDB executePIN = new ATMDB();
					executePIN.checkPIN(myPIN);
				} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		
		
		
	/* ------Format label, text field and button ------------------*/
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(15, 15, 15, 15);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		login.add(label, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		login.add(text, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		login.add(button, gbc);
				
		add(login, BorderLayout.CENTER);
		
		setSize(300, 300);
		setLocation(700, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
	
		
	}

}
