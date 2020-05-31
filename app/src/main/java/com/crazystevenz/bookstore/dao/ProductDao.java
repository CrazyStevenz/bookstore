package com.crazystevenz.bookstore.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
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

    @Query("SELECT * FROM product WHERE name LIKE :name LIMIT 1")
    Product getByName(String name);

    @Query("UPDATE product SET amount = amount + 1 WHERE id = :id")
    void incrementAmount(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Product product);

    @Insert
    void insertAll(Product... products);

    @Update
    void update(Product product);

    @Delete
    void delete(Product product);

    @Query("DELETE FROM product")
    void deleteAll();
}
