package Bank.entities;

import javax.swing.*;

public class CheckingAccount extends Account{
    protected Double loanLimit;
    protected Double debt; // Valor a se pagar após fazer um empréstimo

    public CheckingAccount(){}

    public CheckingAccount(String holder, int id, Double balance, Double loanLimit) {
        super(holder, id, balance);
        this.loanLimit = loanLimit;
    }

    public Double getLoanLimit() {
        return loanLimit;
    }

    public void setLoanLimit(Double loanLimit) {
        this.loanLimit = loanLimit;
    }

    public Double getDebt() {
        return debt;
    }

    public Double loan(double amount){
            if (amount <= loanLimit && amount > 0) {
                balance+=amount;
                debt = amount + (amount*0.06); // Taxa no empréstimo
            } else {
                JOptionPane.showMessageDialog(null, "The amount does not match the loan limit.");
            }
        JOptionPane.showMessageDialog(null, "Updated balance: " + balance);
        return balance;
    }

    public Double payDebt(){
        if(debt>0){
            if(balance>debt){
                balance-=debt;
            }
        } else {
            JOptionPane.showMessageDialog(null, "You currently have no debts.");
        }
        JOptionPane.showMessageDialog(null, "Updated balance: " + balance);
        return balance;
    }

    @Override
    public String toString() {
        return super.toString() + "\nLoan Limit $: " + loanLimit;
    }
}
