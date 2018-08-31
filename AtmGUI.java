/* ATM Machine application
 * This program is an interactive ATM machine that allows the user to deposit, withdraw, transfer funds,
 * and view account balances for checking and savings accounts 
 * 
 * @author - Neal Fesefeldt
 * @since - 2017/09/23
 *  Programming assignment #1, CS 2050
 *  Eclipse 4.7.0, MacBook Pro, OSX 10.12.5
 *  
 */ 

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.util.*;

public class AtmGUI extends BankAccount  {

	public AtmGUI(double currentBalance, double initialBalance, 
					double checkingBalance, double savingsBalance) {
		super(0.0, 0.0, 500.0, 500.0);	
	}

	JPanel firstPanel, secondPanel, thirdPanel, fourthPanel, bottomPanel;
	JButton depButton, withButton, transButton, balButton;
	JRadioButton checkRadButton, saveRadButton;	
	
	public void createAndDisplayGUI()  {
		
		// Create and set up JFrame.
		JFrame frame = new JFrame("ATM Machine"); 
		frame.setLayout(new FlowLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Make window look good
		JFrame.setDefaultLookAndFeelDecorated(true);
		
		// Create Panels
		firstPanel = new JPanel();
		secondPanel = new JPanel();
		thirdPanel = new JPanel();
		fourthPanel = new JPanel();
		bottomPanel = new JPanel();			
		firstPanel.setLayout(new FlowLayout());
		secondPanel.setLayout(new FlowLayout());
		thirdPanel.setLayout(new FlowLayout());
		fourthPanel.setLayout(new FlowLayout());
		bottomPanel.setLayout(new FlowLayout());
		
		// Add the Welcome Message.
		JLabel label = new JLabel("Welcome. Choose an option below."); 
		firstPanel.add(label);
		
		// Create and Add Buttons to panels
		depButton = new JButton("Deposit");
		secondPanel.add(depButton);		
		withButton = new JButton("Withdraw");
		secondPanel.add(withButton);		
		transButton = new JButton("Transfer");
		thirdPanel.add(transButton);
		balButton = new JButton("View Balance");
		thirdPanel.add(balButton);	
		checkRadButton = new JRadioButton("Checking");
		fourthPanel.add(checkRadButton);
		saveRadButton = new JRadioButton("Savings");
		fourthPanel.add(saveRadButton);
		
		// Create a button group for radio buttons so that only one can be selected
		ButtonGroup group = new ButtonGroup();
		group.add(checkRadButton);
		group.add(saveRadButton);
		
		// Add panels to JFrame
		frame.getContentPane().add("North", firstPanel);
		frame.getContentPane().add(secondPanel);
		frame.getContentPane().add(thirdPanel);
		frame.getContentPane().add(fourthPanel);
		frame.getContentPane().add(bottomPanel);	
				
		// Set Frame size
		frame.setMaximumSize(new Dimension(400, 300));
		frame.setPreferredSize(new Dimension(400, 300));
		
		// Add functionality to buttons
		depButton.addActionListener(new ActionListener() { 
		    public void actionPerformed(ActionEvent e) { 
		    		try {
		    			if(checkRadButton.isSelected()) {
		    				checkingBalance = deposit(checkingBalance, currentBalance); 
		    			} 
		    			else if(saveRadButton.isSelected()) {
		    				savingsBalance = deposit(savingsBalance, currentBalance);
		    			}
		    			else {
		    				JOptionPane.showMessageDialog(null, "Please select an account below");
		    			}
		    		}
		    		catch(NumberFormatException | NoSuchElementException ex) {
		    			JOptionPane.showMessageDialog(null, "Error. Please enter an a positive numeric value.", 
		    											inputStr, JOptionPane.ERROR_MESSAGE);
		    		}    
		    } 
		});
		
		withButton.addActionListener(new ActionListener() { 
		    public void actionPerformed(ActionEvent e) { 
		    		try {
		    			if(checkRadButton.isSelected()) {
		    				checkingBalance = withdraw(checkingBalance, currentBalance);         
		    			} 
		    			else if(saveRadButton.isSelected()) {
		    				savingsBalance = withdraw(savingsBalance, currentBalance);	
		    			}
		    			else {
		    				JOptionPane.showMessageDialog(null, "Please select an account below");
		    			}
		    		}
		    		catch(NumberFormatException | NoSuchElementException ex) {
		    			JOptionPane.showMessageDialog(null, "Error. Please enter a positive numeric value.", 
		    											inputStr, JOptionPane.ERROR_MESSAGE);
		    		}
		    		catch(InsufficientFundsException insuf) {
		    			JOptionPane.showMessageDialog(null, "Error! Insufficient Funds.", 
												inputStr, JOptionPane.ERROR_MESSAGE);
		    		}
		    } 
		});
		
		transButton.addActionListener(new ActionListener() { 
		    public void actionPerformed(ActionEvent e) { 
		    		try {
		    			if(checkRadButton.isSelected()) {
		    				accounts = transfer(accounts, checkingBalance, savingsBalance); 
		    				checkingBalance = accounts[0];  
		    				savingsBalance = accounts[1];
		    			} 
		    			else if(saveRadButton.isSelected()) {
		    				accounts = transfer(accounts, savingsBalance, checkingBalance);
		    				savingsBalance = accounts[0];
		    				checkingBalance = accounts[1];
			    		}
			    		else {
			    			JOptionPane.showMessageDialog(null, "Please select an account below");
			    		}
		    		}
		    		catch(NumberFormatException | NoSuchElementException ex) {
		    			JOptionPane.showMessageDialog(null, "Error! Please enter a positive numeric value.", 
		    											inputStr, JOptionPane.ERROR_MESSAGE);
		    		}
		    		catch(InsufficientFundsException insuf) {
		    			JOptionPane.showMessageDialog(null, "Error! Insufficient Funds.", 
												inputStr, JOptionPane.ERROR_MESSAGE);
		    		}
		    } 
		});
		
		balButton.addActionListener(new ActionListener() { 
		    public void actionPerformed(ActionEvent e) { 
		    		if(checkRadButton.isSelected()) {
		    			printBalance(checkingBalance, "checking account");    
		    		} 
		    		else if(saveRadButton.isSelected()) {
		    			printBalance(savingsBalance, "savings account");	
		    		}
		    		else {
		    			JOptionPane.showMessageDialog(null, "Please select an account below");
		    		}
		}});

		// Display GUI
		frame.setVisible(true); 
		frame.pack();
		frame.setLocationRelativeTo(null);
		
	} // End createAndShowGUI
	
	public static void main(String[] args) {
		
		AtmGUI gui = new AtmGUI(0.0, 0.0, 0.0, 0.0);
		gui.createAndDisplayGUI();

	} // End Main
	
} // End Class