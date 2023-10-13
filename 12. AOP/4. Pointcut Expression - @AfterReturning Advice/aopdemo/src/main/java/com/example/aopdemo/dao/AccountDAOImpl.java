package com.example.aopdemo.dao;

import com.example.aopdemo.Account;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
// Makes the class available for Component Scanning, so that we can inject the AccountDAO. | These beans are then available to be injected as dependencies elsewhere in your application.
public class AccountDAOImpl implements AccountDAO {

    private String name;
    private String serviceCode;

    public String getName() {
        System.out.println("getName()");
        return name;
    }

    public void setName(String name) {
        System.out.println("setName()");
        this.name = name;
    }

    public String getServiceCode() {
        System.out.println("getServiceCode()");
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        System.out.println("setServiceCode()");
        this.serviceCode = serviceCode;
    }

    @Override
    public List<Account> findAccounts() {
        List<Account> myAccounts = new ArrayList<>();
        // Create sample Accounts
        Account temp1 = new Account("System Engineer","Abhi");
        Account temp2 = new Account("Digital Specialist Engineer","Sanyam");
        Account temp3 = new Account("Specialist Programmer","Arijit");

        // Add sample accounts inside the list

        myAccounts.add(temp1);
        myAccounts.add(temp2);
        myAccounts.add(temp3);

        return myAccounts;
    }

    @Override
    public void addAccount(Account theAccount, boolean vipFlag) {
        System.out.println(theAccount.getLevel() + " " + theAccount.getName());


    }
}
