package DollarsBanking;

import java.util.ArrayList;

public class Account {

    String name;
    String address;
    String contact;
    int uid;
    String password;
    Double Deposit;
    ArrayList<String> transactionHistory = new ArrayList<String>();

    boolean isLoggedIn = false;

    public Account(String name, String address, String contact, int uid, String password, Double initialDeposit) {
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.uid = uid;
        this.password = password;
        this.Deposit = initialDeposit;
    }

    public void deposit(Double amount) {
        this.Deposit += amount;
        System.out.println("you deposited " + amount);
        System.out.println("your new Account balance is " + Deposit);
    }

    public void withdraw(Double amount) {
        try{
            if (amount <= Deposit) {
                this.Deposit -= amount;
                System.out.println("you have withdrawn " + amount + " dollars");
                System.out.println("your new Account balance is " + this.Deposit);
            }else{
                throw new NotEnoughFundsException("You're account does not have enough funds to make withdrawl for "+amount);
            }
        }catch(NotEnoughFundsException e){
            System.out.println(e);
        }

    }

    public void addTransaction(String trans) {
        this.transactionHistory.add(trans);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getDeposit() {
        return Deposit;
    }

    public void setDeposit(Double initialDeposit) {
        this.Deposit = initialDeposit;
    }

    @Override
    public String toString() {
        return "Account [name=" + this.name + ", address=" + this.address + ", contact=" + this.contact + ", uid="
                + this.uid
                + ", password=" + this.password + ", initialDeposit=" + this.Deposit + "]";
    }

    public void logIn() {
        this.isLoggedIn = true;
    }

    public void logout() {
        this.isLoggedIn = false;
    }

}
