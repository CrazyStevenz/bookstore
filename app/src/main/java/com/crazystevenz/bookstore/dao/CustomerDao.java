package com.crazystevenz.bookstore.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.crazystevenz.bookstore.model.Customer;

import java.util.List;

@Dao
public interface CustomerDao {
    @Query("SELECT * FROM customer WHERE id = :id")
    Customer get(int id);

    @Query("SELECT * FROM customer ORDER BY id ASC")
    LiveData<List<Customer>> getAll();

    @Insert
    void insert(Customer customer);

    @Insert
    void insertAll(Customer... customers);

    @Update
    void update(Customer customer);

    @Delete
    void delete(Customer customer);

    @Query("DELETE FROM customer")
    void deleteAll();
}
