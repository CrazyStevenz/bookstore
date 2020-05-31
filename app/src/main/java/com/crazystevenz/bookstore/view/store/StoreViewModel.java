package com.crazystevenz.bookstore.view.store;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.crazystevenz.bookstore.model.Product;
import com.crazystevenz.bookstore.repository.ProductRepository;

import java.util.List;

public class StoreViewModel extends AndroidViewModel {

    private LiveData<List<Product>> products;

    public StoreViewModel(@NonNull Application application) {
        super(application);
        ProductRepository productRepository = new ProductRepository(application);
        products = productRepository.getAll();
    }

    public LiveData<List<Product>> getAll() {
        return products;
    }
}
