package com.crazystevenz.bookstore.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.crazystevenz.bookstore.model.Product;

import java.util.List;

@Dao
public interface ProductDao {
    @Query("SELECT * FROM product WHERE id = :id")
    Product get(int id);

    @Query("SELECT * FROM product")
    LiveData<List<Product>> getAll();

    @Query("UPDATE product SET amount = amount + 1 WHERE id = :id")
    void incrementAmount(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Product product);

    @Update
    void update(Product product);
}
