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
                    System.out.println("New Balance is: " + "R" +balance);

                } else{
                    System.out.println("Please enter amount greater than 0");
                } else{
                    System.out.println("Insufficient Funds, cannot complete withdrawal");
                }
            }
        }
    // Getting previous transaction
    void getPreviousTransaction(){
            if(previousTransaction > 0){
                System.out.println("Deposited: " + previousTransaction);

            } else if(previousTransaction < 0){
                System.out.println("Withdrawn: " + previousTransaction);

            } else {
                System.out.println("No Transaction Occurred");
            }

        }
     // Calculating the interest method
     void calculateInterest(int years){
            double interestRate = .0185; // constant rate
            double newBalance = ( balance * interestRate* years) + balance; // Years is received as a parameter
            System.out.println("The current interest rate is " + (100 * interestRate) + "%");
            System.out.println("After " + years + "years, your balance will be: " + "R"+ newBalance );

        }
    }
}