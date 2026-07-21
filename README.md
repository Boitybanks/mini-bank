# 🏦 Mini Bank

A command-line banking application built in Java, demonstrating core object-oriented design: abstraction, inheritance, polymorphism, and custom exception handling. Mini Bank simulates real banking operations — deposits, withdrawals, interest projection, and transaction history — across two account types with different rules.

---

## 📋 Features

- **Two account types** — `CheckingAccount` (allows a defined overdraft) and `SavingsAccount` (earns interest, no overdraft)
- **Deposit / Withdraw** — with validation and account-specific rules enforced via polymorphism
- **Balance & transaction history** — every deposit/withdrawal is logged with a timestamp
- **Interest projection** — compound-interest forecast for savings accounts, over any number of years
- **Custom exception handling** — `InsufficientFundsException` for invalid withdrawals, instead of silently failing
- **Colour-coded CLI output** — green for success, red for errors, cyan for menus, yellow for prompts

---

## 🧠 Design overview

```
Account (abstract)
 ├── balance, owner, transaction history
 ├── deposit()                — shared by all accounts
 └── withdraw()  [abstract]   — each subclass defines its own rule
      │
      ├── CheckingAccount     — can go negative, up to an overdraft limit
      └── SavingsAccount      — can never go negative; also projects interest

Bank        — opens and looks up accounts by account number
Transaction — immutable record of a single deposit/withdrawal
```

The `withdraw()` rule lives on each account type rather than in a big `if/else` in the app — that's the whole point of the abstract class. `MiniBankApp` never needs to know *which* kind of account it's talking to; it just calls `account.withdraw(amount)` and lets polymorphism route to the right logic.

---

## 🚀 Getting Started

### Prerequisites
- Java JDK 17 or higher
- A terminal / command line

### Running the app

```bash
# From the project root
javac -d out src/main/java/*.java
java -cp out MiniBankApp
```

### Running the tests

```bash
javac -d out src/main/java/*.java src/test/java/AccountTest.java
java -cp out AccountTest
```

No external test framework required — the suite runs on nothing but the JDK. (If you'd rather use JUnit, the assertions translate 1:1 into `@Test` methods.)

---

## 💻 Usage

On launch you'll be asked for your name, account type, and customer ID. Then:

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

## 📁 Project Structure

```
mini-bank/
├── src/
│   ├── main/java/
│   │   ├── Account.java                 # abstract base class
│   │   ├── CheckingAccount.java         # overdraft-enabled account
│   │   ├── SavingsAccount.java          # interest-earning account
│   │   ├── Transaction.java             # immutable transaction record
│   │   ├── TransactionType.java         # DEPOSIT / WITHDRAWAL enum
│   │   ├── InsufficientFundsException.java
│   │   ├── Bank.java                    # opens/looks up accounts
│   │   └── MiniBankApp.java             # CLI entry point (main)
│   └── test/java/
│       └── AccountTest.java             # unit tests for account logic
└── README.md
```

---

## 🛠️ Built With

- Java 17+ (records, sealed-style abstraction, pattern matching for `instanceof`)
- No external libraries — pure Java standard library

---

## 👤 Author

**Boitumelo Mbhele**
GitHub: [@Boitybanks](https://github.com/Boitybanks)
