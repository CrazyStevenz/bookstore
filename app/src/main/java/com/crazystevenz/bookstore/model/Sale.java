package com.crazystevenz.bookstore.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "sale", foreignKeys = {
    @ForeignKey(entity = Customer.class, parentColumns = "id", childColumns = "customer_id",
            onDelete = CASCADE, onUpdate = CASCADE),
    @ForeignKey(entity = Product.class, parentColumns = "id", childColumns = "product_id",
            onDelete = CASCADE, onUpdate = CASCADE)
})
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

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", customerId=" + customerId +
                ", productId=" + productId +
                ", productAmount=" + productAmount +
                ", complete=" + complete + "\n";
    }
}
