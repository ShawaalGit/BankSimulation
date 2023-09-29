package com.bank.simulation.entity;

import com.bank.simulation.exception.InsufficientBalance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class Customer implements Runnable {
    @Autowired
    private BankAccount bankAccount;

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            BigDecimal depositedAmount = BigDecimal.valueOf(Math.random() * 10);
            BigDecimal withDrawnAmount = BigDecimal.valueOf(Math.random() * 5);
            makeTransaction("deposit", depositedAmount);
            makeTransaction("withdrawn", withDrawnAmount);
        }
    }
    /**Using Lambda Expression created the makeTransaction method
    for deposit and withdraw action respectively using Runnable**/
    private void makeTransaction(String action, BigDecimal amount) {
        Runnable transaction = () -> {
            if ("deposit".equals(action)) {
                bankAccount.deposit(amount);
            } else if ("withdrawn".equals(action)) {
                try {
                    bankAccount.withDraw(amount);
                } catch (InsufficientBalance exception) {
                    System.out.println(exception.getMessage());
                }
            }
        };
        transaction.run();
    }
}
