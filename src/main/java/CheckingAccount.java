/**
 * A day-to-day account that allows the balance to dip below zero,
 * up to a fixed overdraft limit.
 */
public class CheckingAccount extends Account {

    private final double overdraftLimit;

    public CheckingAccount(String accountNumber, String ownerName, double openingBalance, double overdraftLimit) {
        super(accountNumber, ownerName, openingBalance);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive.");
        }
        if (balance - amount < -overdraftLimit) {
            throw new InsufficientFundsException(String.format(
                    "Withdrawal of R%.2f would exceed your overdraft limit of R%.2f.", amount, overdraftLimit));
        }
        recordWithdrawal(amount);
    }

    public double getOverdraftLimit() {
        return overdraftLimit;
    }
}
