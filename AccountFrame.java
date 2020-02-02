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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class AccountFrame extends JFrame {
	
	public AccountFrame(String myPIN) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		
		super("My Account");
		
		JPanel userAcc = new JPanel(new GridBagLayout());
		
	// ------- Get account details from database and add to labels -----------------------------//
		
		// Add account name to label //
		ATMDB getAccountDetails = new ATMDB();
		String[] myAccount = getAccountDetails.userDetails(myPIN);
		
		JLabel label = new JLabel("Hello " + myAccount[0]);
		label.setFont(new Font("Arial", Font.PLAIN, 18));
		
	    // Add account number to label //
		JLabel labelAccount = new JLabel("Account Number: " + myAccount[2]);
		labelAccount.setFont(new Font("Arial", Font.PLAIN, 18));
		
	// -------------------------- Create Text field ---------------------------------------------//
		
		
		JTextField text = new JTextField(10);
		text.setPreferredSize(new Dimension(100, 40));
		text.setHorizontalAlignment(JTextField.CENTER);
		
		
		
	// ------------------------ Deposit money into account --------------------------------------//
		
		JButton depositBtn = new JButton("Deposit Money");
		depositBtn.setPreferredSize(new Dimension(150, 40));
		depositBtn.setFont(new Font("Arial", Font.PLAIN, 14));
		
		depositBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String deposit = text.getText();
				Integer depositVal = Integer.parseInt(deposit);
				
				try {
					ATMDB getBalance = new ATMDB();
					String[] myAccount = getBalance.userDetails(myPIN);
					
					Integer myBalance = Integer.parseInt(myAccount[1]);
					int newBalance = depositVal + myBalance;
					String newDeposit = Integer.toString(newBalance);
					System.out.println(newDeposit);
					
					ATMDB myDeposit = new ATMDB();
					myDeposit.updateBalance(newDeposit, myPIN);
					JOptionPane.showMessageDialog(null, "Your balance is now £" + newDeposit, null, JOptionPane.PLAIN_MESSAGE, null);
					
					text.setText("");
					
				} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e2) {
					e2.printStackTrace();
				}
			}
		});
		
	// --------------------------- Withdraw money from account ---------------------------------//
		
		JButton withdrawalBtn = new JButton("Withdraw Money");
		withdrawalBtn.setPreferredSize(new Dimension(150, 40));
		withdrawalBtn.setFont(new Font("Arial", Font.PLAIN, 14));
		
		withdrawalBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String withdrawal = text.getText();
				Integer withdrawalVal = Integer.parseInt(withdrawal);
				
				try {
					ATMDB getBalance = new ATMDB();
					String[] myAccount = getBalance.userDetails(myPIN);
					
					Integer myBalance = Integer.parseInt(myAccount[1]);
					int newBalance = myBalance - withdrawalVal;
					
					if(newBalance < 0){
						JOptionPane.showMessageDialog(null, "Insufficient funds", null, JOptionPane.ERROR_MESSAGE);
					} else {
						String newWithdrawal = Integer.toString(newBalance);
						System.out.println(newWithdrawal);
					
						ATMDB myWithdrawal = new ATMDB();
						myWithdrawal.updateBalance(newWithdrawal, myPIN);
						JOptionPane.showMessageDialog(null, "Your balance is now £" + newWithdrawal, null, JOptionPane.PLAIN_MESSAGE, null);
						
						
					}
					text.setText("");
					
				} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
	// ---------------------------- Show account balance ---------------------------------------//	
		
		JButton balanceBtn = new JButton("Check Balance");
		balanceBtn.setPreferredSize(new Dimension(150, 40));
		balanceBtn.setFont(new Font("Arial", Font.PLAIN, 14));
		
		balanceBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					ATMDB showBalance = new ATMDB();
					String[] myBalance = showBalance.userDetails(myPIN);
					JOptionPane.showMessageDialog(null, "Your balance is £" + myBalance[1], null, JOptionPane.PLAIN_MESSAGE, null);
					
				} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		
	// -------------------------------------------------------------//
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(0, 15, 15, 0);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		userAcc.add(label, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		userAcc.add(labelAccount, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		userAcc.add(text, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		userAcc.add(depositBtn, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 3;
		userAcc.add(withdrawalBtn, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 3;
		userAcc.add(balanceBtn, gbc);
		
		
		
		add(userAcc, BorderLayout.WEST);
		
		setSize(590, 300);
		setLocation(700, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

}
