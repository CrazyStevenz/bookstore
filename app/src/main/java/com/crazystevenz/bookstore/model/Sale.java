package com.crazystevenz.bookstore.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "sale")
public class Sale {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "customer_id")
    private int customerId;
    @ColumnInfo(name = "product_id")
    private int productId;
    @ColumnInfo(name = "product_amount")
    private double productAmount;
    private boolean complete;

    public Sale(int customerId, int productId, double productAmount) {
        this.customerId = customerId;
        this.productId = productId;
        this.productAmount = productAmount;
        complete = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public double getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(double productAmount) {
        this.productAmount = productAmount;
    }
}
