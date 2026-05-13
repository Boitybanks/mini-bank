import java.util.Scanner;

public class Bank {

    static final String RESET = "\u001B[0m";
    static final String GREEN = "\u001B[32m";
    static final String RED = "\u001B[31m";
    static final String YELLOW = "\u001B[33m";
    static final String CYAN = "\u001B[36m";

    static void menu(Account account) {
        System.out.println(CYAN + "----------------------------------------------" + RESET);
        System.out.println(CYAN + "Welcome, " + account.customerName + "!" + RESET);
        System.out.println(CYAN + "Your ID is: " + account.customerID + RESET);
        System.out.println(CYAN + "----------------------------------------------" + RESET);
        System.out.println("A. Check Balance");
        System.out.println("B. Deposit");
        System.out.println("C. Withdraw");
        System.out.println("D. Previous Transaction");
        System.out.println("E. Interest");
        System.out.println("F. Exit");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(GREEN + "Welcome to Banks, enter your name: " + RESET);
        String custName = scanner.nextLine();
        System.out.println(GREEN + "Enter customer ID: " + RESET);
        String custID = scanner.nextLine();
        Account account = new Account(custName, custID);

        char option;
        boolean flag = true;

        while (flag) {
            menu(account);
            System.out.println(YELLOW + "Enter Your Choice: " + RESET);
            option = Character.toUpperCase(scanner.next().charAt(0));

            switch (option) {
                case 'A':
                    System.out.println(GREEN + "Account Balance = R" + account.balance + RESET);
                    break;
                case 'B':
                    System.out.println(YELLOW + "Enter an amount to deposit: " + RESET);
                    int depositAmount = scanner.nextInt();
                    account.deposit(depositAmount);
                    break;
                case 'C':
                    System.out.println(YELLOW + "Enter an amount to withdraw: " + RESET);
                    int withdrawAmount = scanner.nextInt();
                    account.withdraw(withdrawAmount);
                    break;
                case 'D':
                    account.getPreviousTransaction();
                    break;
                case 'E':
                    System.out.println(YELLOW + "Enter the years for which you wish to calculate the interest: " + RESET);
                    int years = scanner.nextInt();
                    account.calculateInterest(years);
                    break;
                case 'F':
                    System.out.println(GREEN + "Thank you for banking with Banks!!" + RESET);
                    scanner.close();
                    flag = false;
                    break;
                default:
                    System.out.println(RED + "Error: invalid option. Please enter A, B, C, D, E or F." + RESET);
            }
        }
    }
}