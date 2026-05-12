import java.util.Scanner;

public class Bank {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Banks, enter your name:______");
        String custName = scanner.nextLine();
        System.out.println("Enter customer ID:");
        String custID = scanner.nextLine();
        Account account = new Account(custName,custID);

    void menu(){
            System.out.println("----------------------------------------------");
            System.out.println("Welcome," + account.customerName + "!");
            System.out.println("Your ID is:" + customerID);
            System.out.println("_______________________________________________");
            System.out.println("Select any option to proceed");
            System.out.println();
            System.out.println("A. Check Balance");
            System.out.println("B. Deposit");
            System.out.println("C. Withdraw");
            System.out.println("D. Previous Transaction");
            System.out.println("E. Interest");
            System.out.println("F. Check Balance");

        }
    char option;    // char dt var to accept user's choice
    boolean flag = true; // will be used to loop until user exits the program

    while (flag){
        System.out.println("Enter Your Choice");
        option = scanner.next().charAt(0); // Syntax to accept a single character as input

    switch (option){
        case "A":
            System.out.println(" Account Balnce = R" + account.balance);
            break;
        case "B":
            System.out.println("Enter an amount to deposit");
            break;
        case "C":
            System.out.println("Enter an amount to withdraw");
            int withdrawAmount = scanner.nextInt(); // accept an amount input
            account.withdraw(withdrawAmount);
            break;
        case "D":
            account.getPreviousTransaction();
            break;
        case "E":
            System.out.println("Enter the years for which you wish to calculate the interest:");
            int years = scanner.nextInt(); // Accept # of years as input
            account.calculateInterest(years);
            break;
        case "F":
            System.out.println("Thank you for banking with Banks!!");
            scanner.close();
            flag = false; // Nullify the flag to terminate the while loop
            break;
        default:
            System.out.println("Error: invalid option. Please enter A,B,C,C,D or E to access services");

    }
    }
    }
}