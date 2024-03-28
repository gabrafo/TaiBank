package Bank.application;

import Bank.entities.Account;
import Bank.entities.CheckingAccount;
import Bank.entities.SavingsAccount;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.swing.*;

public class Program {
    public static void main(String[] args) {

        Locale.setDefault(Locale.US);

        JFrame frame = new JFrame("TaiBank");
        JOptionPane.showMessageDialog(frame, "Welcome to TaiBank!");

        List<Account> customers = new ArrayList<>();
        int cont = 0; // Contador de clientes

        // Solicita o nome do titular da conta
        String holderName = JOptionPane.showInputDialog(frame, "Enter holder's name:");

        // Solicita o ID da conta
        int id = cont + 1;

        // Solicita o saldo atual da conta
        double balance = 0;
        boolean validInitialBalance = false;
        while (!validInitialBalance) {
            try {
                String balanceStr = JOptionPane.showInputDialog(frame, "Enter current balance:");
                balance = Double.parseDouble(balanceStr);
                validInitialBalance = true;
            } catch (NumberFormatException e) {
                // Trata a exceção caso a conversão falhe
                JOptionPane.showMessageDialog(frame, "Error: Invalid balance format. Please enter a valid number.");
            }
        }

        // Opções disponíveis para o usuário
        String[] options = {"Savings Account", "Checking Account"};
        int choice = JOptionPane.showOptionDialog(null, "Choose the type of account:", "Account Type",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);

        if (choice == 0) {
            JOptionPane.showMessageDialog(null, "You have chosen Savings Account.", "Information",
                    JOptionPane.INFORMATION_MESSAGE, null);
            boolean validInterestRate = false;
            while (!validInterestRate) {
                try {
                    String interestRateStr = JOptionPane.showInputDialog(frame, "Enter interest rate:");
                    double interestRate = Double.parseDouble(interestRateStr);
                    customers.add(new SavingsAccount(holderName, id, balance, LocalDate.now(), interestRate));
                    cont++;
                    validInterestRate = true;
                } catch (NumberFormatException e) {
                    // Trata a exceção caso a conversão falhe
                    JOptionPane.showMessageDialog(frame, "Error: Invalid balance format. Please enter a valid number.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "You have chosen Checking Account.", "Information",
                    JOptionPane.INFORMATION_MESSAGE, null);
            boolean validLoanLimit = false;
            while (!validLoanLimit) {
                try {
                    String loanLimitStr = JOptionPane.showInputDialog(frame, "Enter loan limit:");
                    double loanLimit = Double.parseDouble(loanLimitStr);
                    customers.add(new CheckingAccount(holderName, id, balance, loanLimit));
                    cont++;
                    validLoanLimit = true;
                } catch (NumberFormatException e) {
                    // Trata a exceção caso a conversão falhe
                    JOptionPane.showMessageDialog(frame, "Error: Invalid balance format. Please enter a valid number.");
                }
            }
        }

        // Exibe as informações da conta
        if (!customers.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Account created:\n" + customers.get(customers.size() - 1).toString());
        } else {
            JOptionPane.showMessageDialog(frame, "No account created.");
        }

        switch (choice) {
            case 0: {
                String[] transactionOptions = {"Deposit", "Withdraw"};
                int transactionChoice = JOptionPane.showOptionDialog(null, "Choose a transaction:", "Transaction",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, transactionOptions, transactionOptions[0]);
                switch (transactionChoice) {
                    case 0: // Opção: Depósito
                        performDeposit(customers);
                        break;
                    case 1: // Opção: Saque
                        performWithdraw(customers);
                        break;
                    default:
                        // Opção inválida
                        break;
                }
                break;
            }
            case 1: {
                String[] transactionOptions = {"Deposit", "Withdraw", "Loan"};
                int transactionChoice = JOptionPane.showOptionDialog(null, "Choose a transaction:", "Transaction",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, transactionOptions, transactionOptions[0]);
                switch (transactionChoice) {
                    case 0: // Opção: Depósito
                        performDeposit(customers);
                        break;
                    case 1: // Opção: Saque
                        performWithdraw(customers);
                        break;
                    case 2: // Opção: Empréstimo
                        performLoan(customers);
                        break;
                    default:
                        // Opção inválida
                        break;
                }
                break;
            }
        }

        // Fecha a janela quando o processo é concluído
        frame.dispose();
    }

    private static void performDeposit(List<Account> customers) {
        try {
            String depositAmountStr = JOptionPane.showInputDialog(null, "Enter the deposit amount:");
            double depositAmount = Double.parseDouble(depositAmountStr);
            customers.get(customers.size() - 1).deposit(depositAmount);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: Invalid deposit format. Please enter a valid number and try again.");
        }
    }

    private static void performWithdraw(List<Account> customers) {
        try {
            String withdrawAmountStr = JOptionPane.showInputDialog(null, "Enter the withdraw amount:");
            double withdrawAmount = Double.parseDouble(withdrawAmountStr);
            customers.get(customers.size() - 1).withdraw(withdrawAmount);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: Invalid withdraw format. Please enter a valid number and try again.");
        }
    }

    private static void performLoan(List<Account> customers){
        try {
            String loanAmountStr = JOptionPane.showInputDialog(null, "Enter the loan amount:");
            double loanAmount = Double.parseDouble(loanAmountStr);
            ((CheckingAccount)customers.get(customers.size() - 1)).loan(loanAmount);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: Invalid loan format. Please enter a valid number and try again.");
        }
    }
}
