public class Account {
    // Color codes
    static final String RESET = "\u001B[0m";
    static final String GREEN = "\u001B[32m";
    static final String RED = "\u001B[31m";
    static final String YELLOW = "\u001B[33m";
    static final String CYAN = "\u001B[36m";
    String customerName;
    String customerID;
    int balance = 0;
    int previousTransaction = 0;


    Account(String customerName, String customerID) {
        this.customerName = customerName;
        this.customerID = customerID;
    }

    void deposit(int amount) {
        if (amount > 0) {
            balance = balance + amount;
            previousTransaction = amount;
            System.out.println(GREEN + "Amount Deposited Successfully");
            System.out.println(CYAN + "New Balance is: R" + balance);
        } else {
            System.out.println(RED + "Please enter amount greater than 0, " + customerName);
        }
    }

    void withdraw(int amount) {
        if (amount > 0) {
            if (amount < balance) {
                balance = balance - amount;
                previousTransaction = -amount;
                System.out.println(GREEN + "Withdrawal Successful!");
                System.out.println(CYAN + "New Balance is: R" + balance);
            } else {
                System.out.println(RED + "Insufficient Funds, cannot complete withdrawal");
            }
        } else {
            System.out.println(YELLOW + "Please enter amount greater than 0");
        }
    }

    void getPreviousTransaction() {
        if (previousTransaction > 0) {
            System.out.println(GREEN +"Deposited: R" + previousTransaction);
        } else if (previousTransaction < 0) {
            System.out.println(CYAN + "Withdrawn: R" + Math.abs(previousTransaction));
        } else {
            System.out.println(YELLOW + "No Transaction Occurred");
        }
    }

    void calculateInterest(int years) {
        double interestRate = 0.0185;
        double newBalance = (balance * interestRate * years) + balance;
        double profit = newBalance - balance;
        System.out.printf(CYAN + "The current interest rate is %.2f%%%n", (100 * interestRate));
        System.out.printf(GREEN + "After %d years, your balance will be: R%.2f%n" + RESET, years, newBalance);
        System.out.println(YELLOW + "After " + years + " the profit will be " + profit);
    }
}