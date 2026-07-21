import java.util.ArrayList;
import java.util.List;

/**
 * Represents a generic bank account.
 * <p>
 * This class holds everything every account has in common — a balance,
 * an owner, and a transaction history. It deliberately does NOT decide
 * how withdrawals work: that's left to subclasses (CheckingAccount,
 * SavingsAccount), each of which enforces its own rules by overriding
 * {@link #withdraw(double)}.
 */
public abstract class Account {

    private final String accountNumber;
    private final String ownerName;
    protected double balance;
    private final List<Transaction> history = new ArrayList<>();

    public Account(String accountNumber, String ownerName, double openingBalance) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = openingBalance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public double getBalance() {
        return balance;
    }

    public List<Transaction> getHistory() {
        return history;
    }

    public Transaction getLastTransaction() {
        if (history.isEmpty()) {
            return null;
        }
        return history.get(history.size() - 1);
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive.");
        }
        balance += amount;
        history.add(new Transaction(TransactionType.DEPOSIT, amount, balance));
    }

    /**
     * Withdraws money from the account. Every account type implements this
     * differently — a CheckingAccount allows dipping into an overdraft,
     * a SavingsAccount never lets the balance go below zero.
     */
    public abstract void withdraw(double amount) throws InsufficientFundsException;

    /**
     * Helper for subclasses: applies the withdrawal and logs it, once the
     * subclass has already decided the withdrawal is allowed.
     */
    protected void recordWithdrawal(double amount) {
        balance -= amount;
        history.add(new Transaction(TransactionType.WITHDRAWAL, amount, balance));
    }

    @Override
    public String toString() {
        return String.format("%s[number=%s, owner=%s, balance=R%.2f]",
                getClass().getSimpleName(), accountNumber, ownerName, balance);
    }
}
