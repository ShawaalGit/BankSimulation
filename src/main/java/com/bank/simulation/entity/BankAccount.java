package com.bank.simulation.entity;

import com.bank.simulation.exception.InsufficientBalance;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Component
public class BankAccount {
    private BigDecimal balance = new BigDecimal("100.00");
    /**
     * Lock is used for to properly implement synchronization
     * ReentrantLock is used to prevent a race condition
     **/
    private final Lock lock = new ReentrantLock();

    public void deposit(BigDecimal amount) {
        lock.lock();
        try {
            balance = balance.add(amount);
            LocalDateTime localDate = LocalDateTime.now();
            System.out.println(Thread.currentThread().getName() + " deposited " + amount + " at " + localDate);
            System.out.println("Total Balance = " + balance );
        } finally {
            lock.unlock();
        }
    }

    public void withDraw(BigDecimal amount) {
        lock.lock();
        try {
            if (balance.compareTo(amount) >= 0) {
                balance = balance.subtract(amount);
                LocalDateTime localDate = LocalDateTime.now();
                System.out.println(Thread.currentThread().getName() + " withdrew " + amount + " at " + localDate);
                System.out.println("Total Balance = " + balance );
            } else {
                System.out.println("Total Balance = " + balance );
                /**
                 * Using a custom class for exception Handling
                 **/
                throw new InsufficientBalance(Thread.currentThread().getName() + " cant withdraw " + amount + "due to insufficient balance");

            }
        } finally {
            lock.unlock();

        }
    }
}
