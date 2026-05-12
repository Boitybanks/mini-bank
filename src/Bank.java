import java.util.Scanner;

public class Bank {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Banks, enter your name:______");
        String custName = scanner.nextLine();
        System.out.println("Enter customer ID:");
        String custID = scanner.nextLine();
        Account account = new Account(custName,custID);
    }
}