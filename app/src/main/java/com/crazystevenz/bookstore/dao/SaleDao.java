package com.crazystevenz.bookstore.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.crazystevenz.bookstore.model.Sale;

import java.util.List;

@Dao
public interface SaleDao {
    @Query("SELECT * FROM sale WHERE id = :id")
    Sale get(int id);

    @Query("SELECT * FROM sale")
    LiveData<List<Sale>> getAll();

    @Query("SELECT * FROM sale WHERE complete = 0 AND customer_id = :customerId")
    LiveData<List<Sale>> getIncompleteByCustomerId(int customerId);

    @Query("SELECT * FROM sale WHERE complete = 0 AND customer_id = :customerId AND product_id = :productId")
    Sale getIncompleteByCustomerIdAndProductId(int customerId, int productId);

    @Query("UPDATE sale SET complete = 1 WHERE customer_id = :customerId AND complete = 0")
    void buy(int customerId);

    @Insert
    void insert(Sale sale);

    @Update
    void update(Sale sale);

    @Delete
    void delete(Sale sale);
}
