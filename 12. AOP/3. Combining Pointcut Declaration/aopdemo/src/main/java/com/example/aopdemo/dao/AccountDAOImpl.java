package com.example.aopdemo.dao;

import com.example.aopdemo.Account;
import org.springframework.stereotype.Repository;

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
    public void addAccount(Account theAccount, boolean vipFlag) {
        System.out.println(theAccount.getLevel() + " " + theAccount.getName());


    }
}
