package com.crazystevenz.bookstore.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.crazystevenz.bookstore.model.Customer;
import com.crazystevenz.bookstore.model.Sale;

import java.util.List;

@Dao
public interface SaleDao {
    @Query("SELECT * FROM sale WHERE id = :id")
    Sale get(int id);

    @Query("SELECT * FROM sale")
    LiveData<List<Sale>> getAll();

    @Insert
    void insert(Sale sale);

    @Update
    void update(Sale sale);

    @Delete
    void delete(Sale sale);
}
