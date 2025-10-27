import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Base class: Account
class Account {
    protected String accountHolderName;
    protected String accountNumber;
    protected double balance;
    protected List<String> transactionHistory;

    public Account(String accountHolderName, String accountNumber, double initialBalance) {
        this.accountHolderName = accountHolderName;
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
        transactionHistory.add("Account created with initial balance: ₹" + initialBalance);
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add("Deposited: " + amount + " | Balance: " + balance);
            System.out.println("Deposit successful! New Balance: " + balance);
        } else {
            System.out.println("❌ Invalid deposit amount!");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add("Withdrawn: " + amount + " | Balance: " + balance);
            System.out.println(" Withdrawal successful! New Balance: " + balance);
        } else {
            System.out.println("Insufficient balance or invalid amount!");
        }
    }

    public void showBalance() {
        System.out.println(" Current Balance: " + balance+"rs");
    }

    public void showTransactionHistory() {
        System.out.println("\n Transaction History:");
        for (String t : transactionHistory) {
            System.out.println("* " + t);
        }
    }
}

// Derived class: SavingsAccount (Example of Inheritance & Method Overriding)
class SavingsAccount extends Account {
    private double interestRate = 0.03; // 3% interest

    public SavingsAccount(String accountHolderName, String accountNumber, double initialBalance) {
        super(accountHolderName, accountNumber, initialBalance);
    }

    // Overriding withdraw method to apply minimum balance rule
    @Override
    public void withdraw(double amount) {
        double minBalance = 500;
        if (amount > 0 && (balance - amount) >= minBalance) {
            balance -= amount;
            transactionHistory.add("Withdrawn: " + amount + " | Balance: " + balance);
            System.out.println("Withdrawal successful! New Balance: " + balance);
        } else {
            System.out.println("Cannot withdraw! Minimum balance of " + minBalance + " required.");
        }
    }

    public void addInterest() {
        double interest = balance * interestRate;
        balance += interest;
        transactionHistory.add("Interest added: " + interest + " | Balance: " + balance);
        System.out.println("Interest added: " + interest + " | New Balance: " + balance);
    }
}

// Main class
public class BankSimulation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to Java Bank Account Simulation ");
        System.out.print("Enter Account Holder Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Account Number: ");
        String accNumber = sc.nextLine();

        System.out.print("Enter Initial Balance: ₹");
        double balance = sc.nextDouble();

        SavingsAccount account = new SavingsAccount(name, accNumber, balance);

        int choice=0;
        do {
            System.out.println("\n==== MENU ====");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. View Transaction History");
            System.out.println("5. Add Interest");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            
            try{
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = sc.nextDouble();
                    account.deposit(depositAmount);
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = sc.nextDouble();
                    account.withdraw(withdrawAmount);
                    break;
                case 3:
                    account.showBalance();
                    break;
                case 4:
                    account.showTransactionHistory();
                    break;
                case 5:
                    account.addInterest();
                    break;
                case 0:
                    System.out.println(" Thank you for banking with us!");
                    break;
                default:
                    System.out.println(" Invalid choice! Try again.");
            }
        }
        catch(Exception e){
            System.out.println("Invalid Input ! please Try Number ");
            continue;
        }
        }
         while (choice != 0);

        sc.close();
    
    }
}
