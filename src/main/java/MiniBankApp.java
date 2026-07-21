import java.util.Scanner;

/**
 * Command-line front end for Mini Bank. All the actual banking rules
 * live in Account / CheckingAccount / SavingsAccount / Bank — this class
 * is just the menu loop and user interaction.
 */
public class MiniBankApp {

    private static final String RESET = "\u001B[0m";
    private static final String GREEN = "\u001B[32m";
    private static final String RED = "\u001B[31m";
    private static final String CYAN = "\u001B[36m";
    private static final String YELLOW = "\u001B[33m";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Bank bank = new Bank();

        System.out.print(CYAN + "Welcome to Mini Bank! What's your name? " + RESET);
        String name = scanner.nextLine();

        System.out.print(CYAN + "Choose account type - (S)avings or (C)hecking: " + RESET);
        String type = scanner.nextLine().trim().toUpperCase();

        System.out.print(CYAN + "Enter your customer ID: " + RESET);
        String customerId = scanner.nextLine();

        Account account = type.startsWith("S")
                ? new SavingsAccount(customerId, name, 0.0, 0.0185)
                : new CheckingAccount(customerId, name, 0.0, 500.0);
        bank.open(account);

        System.out.println(GREEN + "Account created: " + account + RESET);

        boolean running = true;
        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim().toUpperCase();

            switch (choice) {
                case "A" -> System.out.println(GREEN
                        + String.format("Balance: R%.2f", account.getBalance()) + RESET);

                case "B" -> {
                    System.out.print(YELLOW + "Amount to deposit: " + RESET);
                    double amount = readAmount(scanner);
                    if (amount > 0) {
                        account.deposit(amount);
                        System.out.println(GREEN + String.format(
                                "Deposited R%.2f. New balance: R%.2f", amount, account.getBalance()) + RESET);
                    }
                }

                case "C" -> {
                    System.out.print(YELLOW + "Amount to withdraw: " + RESET);
                    double amount = readAmount(scanner);
                    if (amount > 0) {
                        try {
                            account.withdraw(amount);
                            System.out.println(GREEN + String.format(
                                    "Withdrew R%.2f. New balance: R%.2f", amount, account.getBalance()) + RESET);
                        } catch (InsufficientFundsException e) {
                            System.out.println(RED + e.getMessage() + RESET);
                        }
                    }
                }

                case "D" -> {
                    Transaction last = account.getLastTransaction();
                    System.out.println(last == null
                            ? YELLOW + "No transactions yet." + RESET
                            : CYAN + last + RESET);
                }

                case "E" -> {
                    if (account instanceof SavingsAccount savings) {
                        System.out.print(YELLOW + "Project balance over how many years? " + RESET);
                        try {
                            int years = Integer.parseInt(scanner.nextLine().trim());
                            double projected = savings.projectedBalance(years);
                            System.out.println(GREEN + String.format(
                                    "Projected balance after %d year(s) at %.2f%%: R%.2f (profit: R%.2f)",
                                    years, savings.getInterestRate() * 100, projected,
                                    projected - savings.getBalance()) + RESET);
                        } catch (NumberFormatException e) {
                            System.out.println(RED + "Please enter a whole number of years." + RESET);
                        }
                    } else {
                        System.out.println(RED + "Interest projections are only available on savings accounts." + RESET);
                    }
                }

                case "F" -> {
                    System.out.println(CYAN + "Thanks for banking with us, " + name + "!" + RESET);
                    running = false;
                }

                default -> System.out.println(RED + "Invalid option. Please choose A-F." + RESET);
            }
        }
        scanner.close();
    }

    private static double readAmount(Scanner scanner) {
        try {
            double amount = Double.parseDouble(scanner.nextLine().trim());
            if (amount <= 0) {
                System.out.println(RED + "Amount must be greater than zero." + RESET);
                return 0.0;
            }
            return amount;
        } catch (NumberFormatException e) {
            System.out.println(RED + "Invalid amount, please enter a number." + RESET);
            return 0.0;
        }
    }

    private static void printMenu() {
        System.out.println(CYAN + """

                A. Check Balance
                B. Deposit
                C. Withdraw
                D. Previous Transaction
                E. Interest
                F. Exit
                """ + RESET);
        System.out.print(YELLOW + "Choose an option: " + RESET);
    }
}
