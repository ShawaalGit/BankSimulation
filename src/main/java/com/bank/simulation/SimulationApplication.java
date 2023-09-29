package com.bank.simulation;

import com.bank.simulation.entity.BankAccount;
import com.bank.simulation.entity.Customer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SimulationApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(SimulationApplication.class, args);
        /**
         * Getting the customer objects from application context as they are defined as Component
         **/
        Customer firstCustomer = context.getBean(Customer.class);
        Customer secondCustomer = context.getBean(Customer.class);
        /**
         * Using the above 2 customers to perform deposit/withdraw actions
         * concurrently with the  same bank account
         **/
        Thread firstThread = new Thread(firstCustomer);
        Thread secondThread = new Thread(secondCustomer);
        firstThread.start();
        secondThread.start();
        try {
            firstThread.join();
            secondThread.join();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
