package Bank.entities;

import javax.swing.*;
import java.time.LocalDate;

public class Account {
    protected String holder;
    protected int id;
    protected Double balance;
    protected LocalDate depositDate;

    public Account() {
    }

    public Account(String holder, int id, Double balance) {
        this.holder = holder;
        this.id = id;
        this.balance = balance;
    }

    public Account(String holder, int id, Double initialDeposit, LocalDate initialdepositDate) {
        this.holder= holder;
        this.id = id;
        this.balance = initialDeposit;
        depositDate = initialdepositDate;
    }

    public String getHolder() {
        return holder;
    }

    public void setHolder(String holder) {
        this.holder = holder;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getBalance() {
        return balance;
    }

    public LocalDate getDepositDate() {
        return depositDate;
    }

    public void depositDate(LocalDate depositDate) {
        this.depositDate = depositDate;
    }

    public Double withdraw(double amount){
        if(balance>amount) {
            balance -= amount;
        } else {
            JOptionPane.showMessageDialog(null, "Unavailable balance.");
        }
        JOptionPane.showMessageDialog(null, "Updated balance: " + balance);
        return balance;
    }
    
    public Double deposit(double amount){
        depositDate = LocalDate.now();
        balance+=amount;
        JOptionPane.showMessageDialog(null, "Updated balance: " + balance);
        return balance;
    }

    @Override
    public String toString() {
        return "Holder's Name: " + holder +
                "\nAccount ID: " + id +
                "\nBalance: $" + balance;
    }
}
