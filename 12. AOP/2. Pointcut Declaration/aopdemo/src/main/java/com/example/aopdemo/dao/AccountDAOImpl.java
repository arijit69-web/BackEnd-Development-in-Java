package com.example.aopdemo.dao;

import com.example.aopdemo.Account;
import org.springframework.stereotype.Repository;

@Repository
// Makes the class available for Component Scanning, so that we can inject the AccountDAO. | These beans are then available to be injected as dependencies elsewhere in your application.
public class AccountDAOImpl implements AccountDAO {


    @Override
    public void addAccount(Account theAccount, boolean vipFlag) {
        System.out.println(theAccount.getLevel() + " " + theAccount.getName());


    }
}
