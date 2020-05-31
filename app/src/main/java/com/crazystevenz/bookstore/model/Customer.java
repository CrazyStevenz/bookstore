package com.crazystevenz.bookstore.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "customer")
public class Customer {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String fullName;
    private String username;
    private String password;
    private double balance;

    //region Singleton
    private static Customer customerInstance = null;

    public static Customer getInstance() {
        return customerInstance;
    }

    public void login() {
        customerInstance = this;
    }

    public static void logout() {
        customerInstance = null;
    }
    //endregion

    public Customer(String fullName, String username, String password, double balance) {
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
        return id + " - " + fullName + " - @" + username + " - " + balance + "€" + "\n\n";
    }
}
