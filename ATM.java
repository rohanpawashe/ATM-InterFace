import java.util.Scanner;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient funds. Withdrawal failed.");
            return false;
        } else {
            balance -= amount;
            System.out.println("Withdrawal successful. Remaining balance: $" + balance);
            return true;
        }
    }
}

public class ATM {
    private BankAccount userAccount;

    public ATM(BankAccount userAccount) {
        this.userAccount = userAccount;
    }

    public void displayOptions() {
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Check Balance");
        System.out.println("4. Exit");
    }

    public void performTransaction(int choice, double amount) {
        switch (choice) {
            case 1:
                withdraw(amount);
                break;
            case 2:
                deposit(amount);
                break;
            case 3:
                checkBalance();
                break;
            case 4:
                System.out.println("Thank you for using the ATM. Goodbye!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid option. Please choose a valid option.");
        }
    }

    public void withdraw(double amount) {
        if (userAccount.withdraw(amount)) {
            System.out.println("$" + amount + " withdrawn successfully.");
        } else {
            System.out.println("Withdrawal failed. Please try again.");
        }
    }

    public void deposit(double amount) {
        userAccount.deposit(amount);
        System.out.println("$" + amount + " deposited successfully. Updated balance: $" + userAccount.getBalance());
    }

    public void checkBalance() {
        System.out.println("Current balance: $" + userAccount.getBalance());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter initial account balance: $");
        double initialBalance = scanner.nextDouble();

        BankAccount userAccount = new BankAccount(initialBalance);
        ATM atm = new ATM(userAccount);

        while (true) {
            atm.displayOptions();

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            if (choice == 4) {
                break;
            }

            System.out.print("Enter amount: $");
            double amount = scanner.nextDouble();

            atm.performTransaction(choice, amount);
        }

        scanner.close();
    }
}