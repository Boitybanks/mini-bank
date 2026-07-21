/**
 * A small, dependency-free test suite for the account logic.
 * <p>
 * If your repo already has Maven + JUnit set up, feel free to translate
 * these into @Test methods — the assertions are identical either way.
 * This version runs with nothing but the JDK, so it's a safe first step:
 *
 *   javac -d out src/main/java/*.java src/test/java/AccountTest.java
 *   java -cp out AccountTest
 */
public class AccountTest {

    private static int passed = 0;
    private static int failed = 0;

    public static void main(String[] args) {
        testDepositIncreasesBalance();
        testCheckingAccountAllowsWithdrawalWithinOverdraft();
        testCheckingAccountRejectsWithdrawalBeyondOverdraft();
        testSavingsAccountRejectsWithdrawalBeyondBalance();
        testSavingsAccountProjectedBalanceCompoundsCorrectly();
        testTransactionHistoryTracksLastTransaction();
        testDepositRejectsNonPositiveAmount();

        System.out.println();
        System.out.println(passed + " passed, " + failed + " failed");
        if (failed > 0) {
            System.exit(1);
        }
    }

    private static void testDepositIncreasesBalance() {
        Account account = new CheckingAccount("001", "Boity", 100.0, 0.0);
        account.deposit(50.0);
        check("deposit increases balance", account.getBalance() == 150.0);
    }

    private static void testCheckingAccountAllowsWithdrawalWithinOverdraft() {
        Account account = new CheckingAccount("002", "Boity", 100.0, 500.0);
        try {
            account.withdraw(300.0);
            check("checking account allows withdrawal into overdraft", account.getBalance() == -200.0);
        } catch (InsufficientFundsException e) {
            check("checking account allows withdrawal into overdraft", false);
        }
    }

    private static void testCheckingAccountRejectsWithdrawalBeyondOverdraft() {
        Account account = new CheckingAccount("003", "Boity", 0.0, 100.0);
        try {
            account.withdraw(200.0);
            check("checking account rejects withdrawal beyond overdraft limit", false);
        } catch (InsufficientFundsException e) {
            check("checking account rejects withdrawal beyond overdraft limit", true);
        }
    }

    private static void testSavingsAccountRejectsWithdrawalBeyondBalance() {
        Account account = new SavingsAccount("004", "Boity", 50.0, 0.05);
        try {
            account.withdraw(100.0);
            check("savings account rejects withdrawal beyond balance", false);
        } catch (InsufficientFundsException e) {
            check("savings account rejects withdrawal beyond balance", true);
        }
    }

    private static void testSavingsAccountProjectedBalanceCompoundsCorrectly() {
        SavingsAccount account = new SavingsAccount("005", "Boity", 1000.0, 0.10);
        double projected = account.projectedBalance(2); // 1000 * 1.10^2 = 1210
        check("savings projected balance compounds correctly", Math.abs(projected - 1210.0) < 0.001);
    }

    private static void testTransactionHistoryTracksLastTransaction() {
        Account account = new CheckingAccount("006", "Boity", 0.0, 0.0);
        account.deposit(100.0);
        Transaction last = account.getLastTransaction();
        check("last transaction reflects most recent deposit",
                last != null && last.amount() == 100.0 && last.type() == TransactionType.DEPOSIT);
    }

    private static void testDepositRejectsNonPositiveAmount() {
        Account account = new CheckingAccount("007", "Boity", 0.0, 0.0);
        try {
            account.deposit(0.0);
            check("deposit rejects zero/negative amounts", false);
        } catch (IllegalArgumentException e) {
            check("deposit rejects zero/negative amounts", true);
        }
    }

    private static void check(String description, boolean condition) {
        if (condition) {
            System.out.println("  PASS - " + description);
            passed++;
        } else {
            System.out.println("  FAIL - " + description);
            failed++;
        }
    }
}
