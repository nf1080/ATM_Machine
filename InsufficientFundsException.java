
public class InsufficientFundsException extends Exception {
	
	private double balance;
	
	public InsufficientFundsException(double balance) {
		this.balance = balance;
	}

	public double getBalance() {
		return balance;
	}

} // End Class
