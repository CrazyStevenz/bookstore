package com.crazystevenz.bookstore.view.cart;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.crazystevenz.bookstore.model.Product;
import com.crazystevenz.bookstore.model.Sale;
import com.crazystevenz.bookstore.repository.ProductRepository;
import com.crazystevenz.bookstore.repository.SaleRepository;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class CartViewModel extends AndroidViewModel {

    private SaleRepository saleRepository;
    private ProductRepository productRepository;
    private LiveData<List<Sale>> sales;

    public CartViewModel(@NonNull Application application) {
        super(application);
        saleRepository = new SaleRepository(application);
        productRepository = new ProductRepository(application);
        sales = saleRepository.getAll();
    }

    public LiveData<List<Sale>> getAll() {
        return sales;
    }

    public LiveData<List<Sale>> getIncompleteByUserId(int id) {
        return saleRepository.getIncompleteByCustomerId(id);
    }

    public Product getProductById(int id) throws ExecutionException, InterruptedException {
        return productRepository.getProductById(id);
    }
}