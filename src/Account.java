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
}