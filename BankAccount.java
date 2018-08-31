import java.util.*;
import javax.swing.*;
import java.util.StringTokenizer;
import java.text.DecimalFormat;


public class BankAccount   {
	
	protected double currentBalance;
	protected double initialBalance;
	protected double checkingBalance;
	protected double savingsBalance;
	protected final double serviceCharge = 1.50;  // charge for after 4 withdraws are exceeded.
	protected int serviceChargeCounter;           // counter that is incremented for each withdraw
	double[] accounts = new double[2];
	String inputStr = "";
	StringTokenizer st;
	DecimalFormat df = new DecimalFormat("##0.00"); // Format to two decimal places
	
	public BankAccount(double currentBalance, double initialBalance,
					   double checkingBalance, double savingsBalance) {
		this.currentBalance = currentBalance;
		this.initialBalance = initialBalance;
		this.checkingBalance = checkingBalance;
		this.savingsBalance = savingsBalance;
	}
	
	public double getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(double currentBalance) {
		this.currentBalance = currentBalance;
	}

	public double getInitialBalance() {
		return initialBalance;
	}

	public void setInitialBalance(double initialBalance) {
		this.initialBalance = initialBalance;
	}

	public double getCheckingBalance() {
		return checkingBalance;
	}

	public void setCheckingBalance(double checkingBalance) {
		this.checkingBalance = checkingBalance;
	}

	public double getSavingsBalance() {
		return savingsBalance;
	}

	public void setSavingsBalance(double savingsBalance) {
		this.savingsBalance = savingsBalance;
	}

	
	
/* This is the deposit method. This method deposits the desired amount into the 
 * selected account as well as performing checks for valid input.
 * @param accountBalance and depositAmount are used here, checkingBalance or
 * 		  savingsBalance is passed depending on radio button
 * @return this method returns the accountBalance to the selected account
 * @exception this method can throw a NumberFormatException
 */
	   
	public double deposit(double accountBalance, double depositAmount ) {
		
		inputStr = JOptionPane.showInputDialog("How much would you like to deposit? ");
		if(inputStr != null) {
			st = new StringTokenizer(inputStr);
			depositAmount = Double.parseDouble(st.nextToken());
			if(depositAmount < 0) {
				throw new NumberFormatException();
			}
			else {
				accountBalance += depositAmount;
				JOptionPane.showMessageDialog(null, "Deposit successful. Your new balance is $" + 
											df.format(accountBalance));
			}
		}
		return accountBalance;
			
	} // End Deposit
	
	
	/* This is the withdraw method. This method withdraws the desired amount from the 
	 * selected account as well as performing checks for valid input and insufficient funds.
	 * @param accountBalance and withdrawAmount are used here, checkingBalance or
	 * 		  savingsBalance is passed depending on radio button
	 * @return this method returns the accountBalance to the selected account
	 * @exception this method can throw a NumberFormatException or a InsufficientFundsException
	 */
	
	public double withdraw(double accountBalance, double withdrawAmount) throws InsufficientFundsException {
			
		inputStr = JOptionPane.showInputDialog("How much would you like to withdraw? ");
		if(inputStr != null) {
			st = new StringTokenizer(inputStr);
			withdrawAmount = Double.parseDouble(st.nextToken());
			if(withdrawAmount < 0) {
				throw new NumberFormatException();
			}
			else if(withdrawAmount % 20 != 0) {
				JOptionPane.showMessageDialog(null, "Withdrawl amount must be in $20 increments");
			}
			else if(withdrawAmount > accountBalance) {
				throw new InsufficientFundsException(withdrawAmount);
			}	
			else {
				serviceChargeCounter++;
				accountBalance -= withdrawAmount;
				if(serviceChargeCounter > 4) {
					accountBalance -= serviceCharge;
					if (accountBalance < 0){
						throw new InsufficientFundsException(accountBalance);
					}
					else {
						JOptionPane.showMessageDialog(null, "Notice: You have exceeded 4 withdrawl transactions and have " +
													"\nbeen charged a $1.50 service fee. Additional withdrawls will also " +
													"\nincurr this fee. \nWithdrawl success." +
													"Your new account balance is $" + df.format(accountBalance));
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Withdrawl success. Your new account balance is $" +
												 df.format(accountBalance));
				}
			}
		}
		
		return accountBalance;
		
	} // End withdraw
	
	
	
	/* This is the transfer method. This method transfers the desired amount to the 
	 * selected account, from the other account. It also performs checks for valid input and insufficient funds.
	 * @param accounts, accountOne, and accountTwo are used here, checkingBalance and
	 * 		  savingsBalance is passed depending on radio button.
	 * @return this method returns the array accounts, and the balances are stored into the appropriate account
	 *         objects depending on which radio button is selected.
	 * @exception this method can throw a NumberFormatException or a InsufficientFundsException
	 */
	
	public double[] transfer(double[] accounts, double accountOne, double accountTwo) 
													throws InsufficientFundsException {
		
		double transferAmount;
		
		inputStr = JOptionPane.showInputDialog("How much would you like to transfer? ");
		if(inputStr != null) {
			st = new StringTokenizer(inputStr);
			transferAmount = Double.parseDouble(st.nextToken());
			if(transferAmount < 0) {
				throw new NumberFormatException();
			}
			else if(transferAmount > accountTwo) {
				throw new InsufficientFundsException(transferAmount);
			}
			else {	
				accountOne += transferAmount;
				accounts[0] = accountOne;
				accountTwo -= transferAmount;
				accounts[1] = accountTwo;
				JOptionPane.showMessageDialog(null, "$" + df.format(transferAmount) + " was successfully transfered ");
			}
		}
		return accounts;	
		
	} // End transfer
	
	
	/* This is the printBalance method. It displays the current balance for whichever account is
	 * selected. 
	 * @param accountBalance and account are used here. checkingBalance or savingsBalance is passed,
	 * 		  along with the appropriate string. 
	 * @return No return, void method
	 * @exception No exceptions 
	 */
	
	public void printBalance(double accountBalance, String account) {
		
		JOptionPane.showMessageDialog(null, "Your current " + account + " balance is: $" + 
									 df.format(accountBalance));
	} // End printBalance
	
} // End Class
