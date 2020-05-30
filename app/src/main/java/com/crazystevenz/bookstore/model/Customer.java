package com.crazystevenz.bookstore.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "customer")
public class Customer {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String password;
    private double balance;

    private static Customer customerInstance = null;

    public Customer(String name, String password, double balance) {
        this.name = name;
        this.password = password;
        this.balance = balance;
    }

    public void login() {
        customerInstance = this;
    }

    public void logout() {
        customerInstance = null;
    }

    public static Customer getUserInstance() {
        return customerInstance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return id + " - " + name + " - " + balance + "€" + "\n";
    }
}
