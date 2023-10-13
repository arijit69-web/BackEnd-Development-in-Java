package com.example.aopdemo;

public class Account {

    private String level;
    private String name;

    public Account(String level, String name) {
        this.level = level;
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Account{" +
                "level='" + level + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
