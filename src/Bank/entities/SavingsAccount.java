package Bank.entities;

import javax.swing.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public class SavingsAccount extends Account {

    protected Double interestRate;
    protected LocalDate retrieveDate;

    public SavingsAccount() {}

    public SavingsAccount(String holder, int id, Double initialDeposit, LocalDate initialdepositDate, Double interestRate) {
        super(holder, id, initialDeposit, initialdepositDate);
        this.interestRate = interestRate;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    @Override
    public Double withdraw(double amount){
        retrieveDate = LocalDate.now();
        long monthsBetween = ChronoUnit.MONTHS.between(depositDate, retrieveDate);
        if(monthsBetween > 0) {
            double accumulatedInterest = balance * interestRate * monthsBetween;
            balance += accumulatedInterest;
        } else{
            super.withdraw(amount);
        }
        JOptionPane.showMessageDialog(null, "Updated balance: " + balance);
        return balance;
    }

    @Override
    public String toString() {
        return super.toString() + "\nInterest Rate: " + interestRate;
    }
}
