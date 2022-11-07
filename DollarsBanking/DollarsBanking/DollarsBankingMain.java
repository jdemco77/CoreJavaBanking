package DollarsBanking;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Date;

public class DollarsBankingMain {

    private static final Account None = null;

    public static void main(String[] args) {

        ArrayList<Account> accts = new ArrayList<Account>();
        boolean done = false;

        initialWelcome();

        while (!done) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter choice (1,2 or 3)");
            int choice = scan.nextInt();

            switch (choice) {
                case 1:
                    Scanner in = new Scanner(System.in);
                    createAccountMessage();

                    System.out.println("Enter Customer full name: ");
                    String name = in.nextLine();

                    System.out.println("Enter Customer Address: ");
                    String address = in.nextLine();

                    System.out.println("Enter Customer contact number: ");
                    String contact = in.nextLine();

                    System.out.println("Enter Customer userId: ");
                    int uid = in.nextInt();
                    in.nextLine();

                    System.out.println("Enter Password: 8 characters with Lower,Upper and special character: ");
                    String upass = in.nextLine();

                    System.out.println("Enter Initial Deposit Amount: ");
                    double initDeposit = in.nextDouble();

                    Account acct = new Account(name, address, contact, uid, upass, initDeposit);
                    accts.add(acct);

                    System.out.println("You have created a new customer account");
                    System.out.println(acct.toString());
                    break;

                case 2:
                    System.out.println("You have chosen to login to an existing account");
                    System.out.println("Enter userId number");
                    int EnteredUserId = scan.nextInt();
                    scan.nextLine();

                    System.out.println("Enter password");
                    String enteredpassword = scan.nextLine();

                    Account selectedAcct = None;

                    for (Account account : accts) {
                        if (account.uid == EnteredUserId) {
                            selectedAcct = account;
                            if (enteredpassword.equals(selectedAcct.password)) {
                                selectedAcct.logIn();
                                System.out
                                        .println("you have successfully logged in to account uid= " + selectedAcct.uid);
                            } else {
                                System.out.println("Password is invalid");
                            }
                        } else {
                            System.out.println("Could not find your account with given credentials");
                        }
                    }

                    if (selectedAcct.isLoggedIn) {
                        welcomeCustomer();

                        boolean finished = false;
                        while (!finished) {
                            System.out.println("Enter the number corresponding to above transaction you wish to make");

                            int trans = scan.nextInt();
                            switch (trans) {
                                case 1:
                                    System.out.println("Enter an amount");
                                    double withdrawl = scan.nextDouble();
                                    selectedAcct.withdraw(withdrawl);
                                    selectedAcct
                                            .addTransaction("You have withdrawn " + withdrawl + " on " + new Date());
                                    break;
                                case 2:
                                    System.out.println("Enter an amount");
                                    double depositAmt = scan.nextDouble();
                                    selectedAcct.deposit(depositAmt);
                                    selectedAcct
                                            .addTransaction("You have deposited " + depositAmt + " on " + new Date());
                                    break;
                                case 3:
                                    scan.nextLine();
                                    System.out.println("Enter the customer name you wish to transfer funds to");
                                    String transferName = scan.nextLine();
                                    System.out.println("Enter the amount you wish to transfer: ");
                                    Double transferAmt = scan.nextDouble();
                                    Account transferAccount = None;
                                    for (Account act : accts) {
                                        if (act.name.equals(transferName)) {
                                            transferAccount = act;
                                            selectedAcct.withdraw(transferAmt);
                                            transferAccount.deposit(transferAmt);
                                            System.out.println("You have succesfully transfered " + transferAmt + " to "
                                                    + transferName);
                                            selectedAcct.addTransaction("You have succesfully transfered " + transferAmt
                                                    + " to " + transferName + " on " + new Date());
                                        }
                                    }
                                    break;
                                case 4:
                                    System.out.println("Displaying recent Transactions");
                                    int i = 1;
                                    for (String tran : selectedAcct.transactionHistory) {
                                        System.out.println(i + " " + tran);
                                        i++;
                                    }
                                    break;
                                case 5:
                                    System.out.println("your account information is displayed below...");
                                    System.out.println(selectedAcct.toString());
                                    break;
                                case 6:
                                    selectedAcct.logout();
                                    System.out.println("logging out of account...");
                                    break;
                            }
                            if (selectedAcct.isLoggedIn) {
                                System.out.println("Would you like to make another transaction? enter y/n");
                                scan.nextLine();
                                String fin = scan.nextLine();
                                if (fin.equals("y")) {
                                    finished = false;
                                } else {
                                    finished = true;
                                }
                            }
                        }
                    }
                    break;

                case 3:
                    System.out.println("Exiting Program");
                    return;
            }

        }

    }

    public static void initialWelcome() {
        System.out.println("+----------------------------+");
        System.out.println("| Dollars Bank Welcomes You! |");
        System.out.println("+----------------------------+");
        System.out.println(" 1: create new account");
        System.out.println(" 2: login ");
        System.out.println(" 3: Exit ");
    }

    public static void createAccountMessage() {
        System.out.println("You have chosen to create a new account");
        System.out.println("+----------------------------+");
        System.out.println("|Enter details for new Account|");
        System.out.println("+----------------------------+ \n");
    }

    public static void welcomeCustomer() {
        System.out.println("+------------------+");
        System.out.println("| Welcome Customer |");
        System.out.println("+------------------+");
        System.out.println("1: Withdraw");
        System.out.println("2: Deposit");
        System.out.println("3: Transfer funds");
        System.out.println("4: View recent transactions");
        System.out.println("5: Display customer information");
        System.out.println("6: Logout");
    }
}