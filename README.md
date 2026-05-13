# Mini Bank

A simple command-line banking application built in Java. Mini Bank simulates core banking operations including deposits, withdrawals, balance checks, transaction history, and interest calculations — all from your terminal.

---

##  Features

- **Deposit** — Add funds to your account
- **Withdraw** — Remove funds with balance validation
- **Check Balance** — View your current account balance
- **Previous Transaction** — See your last deposit or withdrawal
- **Interest Calculator** — Calculate projected balance and profit over a number of years at a fixed interest rate of 1.85%
- **Colour-coded output** — Green for success, red for errors, cyan for menus, yellow for prompts

---

##  Getting Started

### Prerequisites
- Java JDK 17 or higher installed
- A terminal / command line

### Running the App

```bash
# Navigate to the source files
cd src/main/java

# Compile
javac Account.java Bank.java

# Run
java Bank
```

---

## 💻 Usage

On launch you will be prompted for your name and customer ID. After that, a menu is displayed with the following options:

```
A. Check Balance
B. Deposit
C. Withdraw
D. Previous Transaction
E. Interest
F. Exit
```

Options are case-insensitive — both `a` and `A` work.

---

##  Project Structure

```
mini-bank/
├── src/
│   └── main/
│       └── java/
│           ├── Account.java   # Account class — balance, transactions, interest
│           └── Bank.java      # Main class — menu and user interaction
└── README.md
```

---

## Built With

- Java (JDK 17)
- No external libraries — pure Java standard library

---

## Author

**Boitumelo Mbhele**  
GitHub: [@Boitybanks](https://github.com/Boitybanks)
