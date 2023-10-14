package com.example.aopdemo.dao;


import com.example.aopdemo.Account;

import java.util.List;

public interface AccountDAO {

    void addAccount(Account theAccount, boolean vipFlag);


    public void findAccounts(boolean tripWire);
    public List<Account> findAccounts();

    public String getName();

    public void setName(String name);

    public String getServiceCode();

    public void setServiceCode(String serviceCode);


}
