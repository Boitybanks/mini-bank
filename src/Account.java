public class Account{
    String customerName;
    String customerID;
    int balance = 0;
    int previousTransaction = 0;

    //constructor
    Account(String customerName, String customerID){
        this.customerName = customerName;
        this.customerID = customerID;
    }

    // Deposit Method
    void deposit(int amount){
        if (amount > 0){
            balance = balance + amount; // Balance update
            previousTransaction = amount; // Marking this for prev transcation
            System.out.println("Amount Deposited Successfully");
            System.out.println(("New Balance is: " + balance));
        } else{
            System.out.println("Please enter amount greater than 0," + customerName);
        }

    // Withdraw method
    void withdraw(int amount){
            if (amount < balance){
                if(amount > 0 ){
                    balance = balance - amount;
                    previousTransaction = -amount;
                    System.out.println("Widrawal Successful!");
                    System.out.println("New Balance is: " + balance);

                } else{
                    System.out.println("Please enter amount greater than 0");
                } else{
                    System.out.println("Insufficient Funds, cannot complete withdrawal");
                }
            }
        }
    }
}