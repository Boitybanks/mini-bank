/**
 * An account that earns interest and never allows a negative balance —
 * no overdraft here.
 */
public class SavingsAccount extends Account {

    private final double interestRate; // e.g. 0.0185 means 1.85% per year

    public SavingsAccount(String accountNumber, String ownerName, double openingBalance, double interestRate) {
        super(accountNumber, ownerName, openingBalance);
        this.interestRate = interestRate;
    }

    @Override
    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive.");
        }
        if (amount > balance) {
            throw new InsufficientFundsException(String.format(
                    "Withdrawal of R%.2f exceeds your available balance of R%.2f.", amount, balance));
        }
        recordWithdrawal(amount);
    }

    /**
     * Projects what the balance would grow to after the given number of
     * years, assuming the current balance compounds annually at this
     * account's interest rate.
     */
    public double projectedBalance(int years) {
        return balance * Math.pow(1 + interestRate, years);
    }

    public double getInterestRate() {
        return interestRate;
    }
}
