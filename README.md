# BankSimulation
Demonstrate a small Banking app where amount is deposited and withdrew concurrently using multithreading
ReentrantLock is used to prevent the race condition during these transactions.
Used Lambda expression in customer class to look what type of transaction is being made i.e deposite of withdraw
Created a class for Exception handling which occurs when customer is trying to withdraw Insufficient amount from the account.
