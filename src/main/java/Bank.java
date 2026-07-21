import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Keeps track of every account that's been opened, keyed by account number.
 * Separating this from MiniBankApp means the account-management logic can
 * be unit tested without touching Scanner or System.out at all.
 */
public class Bank {

    private final Map<String, Account> accounts = new HashMap<>();

    public Account open(Account account) {
        accounts.put(account.getAccountNumber(), account);
        return account;
    }

    public Account find(String accountNumber) {
        Account account = accounts.get(accountNumber);
        if (account == null) {
            throw new NoSuchElementException("No account found with number: " + accountNumber);
        }
        return account;
    }

    public boolean exists(String accountNumber) {
        return accounts.containsKey(accountNumber);
    }

    public int accountCount() {
        return accounts.size();
    }
}
