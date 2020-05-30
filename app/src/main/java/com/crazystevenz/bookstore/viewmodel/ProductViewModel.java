package com.crazystevenz.bookstore.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.crazystevenz.bookstore.model.Product;
import com.crazystevenz.bookstore.repository.ProductRepository;

import java.util.List;

public class ProductViewModel extends AndroidViewModel {

    private ProductRepository productRepository;
    private LiveData<List<Product>> products;

    public ProductViewModel(@NonNull Application application){
        super(application);
        productRepository = new ProductRepository(application);
        products = productRepository.getAll();
    }

    public LiveData<List<Product>> getAll() {
        return products;
    }
}
