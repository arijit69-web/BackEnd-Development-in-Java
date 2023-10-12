package com.example.aopdemo.dao;

import org.springframework.stereotype.Repository;

@Repository
// Makes the class available for Component Scanning, so that we can inject the AccountDAO. | These beans are then available to be injected as dependencies elsewhere in your application.
public class MembershipDAOImpl implements MembershipDAO {
    @Override
    public void addAccount() {
        System.out.println("Check Membership Account! " + getClass());
    }

    @Override
    public String addData() {
        return "Check Membership Data! ";

    }
}
