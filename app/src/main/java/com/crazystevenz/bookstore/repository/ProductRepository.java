package com.crazystevenz.bookstore.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.crazystevenz.bookstore.dao.ProductDao;
import com.crazystevenz.bookstore.model.Product;

import java.util.List;

public class ProductRepository {

    private ProductDao mProductDao;
    private LiveData<List<Product>> mProducts;

    public ProductRepository (Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        mProductDao = db.productDao();
        mProducts = mProductDao.getAll();
    }

    public LiveData<List<Product>> getAll() {
        return mProducts;
    }
}
