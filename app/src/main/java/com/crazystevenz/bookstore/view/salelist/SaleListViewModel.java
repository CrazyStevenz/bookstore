package com.crazystevenz.bookstore.view.salelist;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.crazystevenz.bookstore.model.Customer;
import com.crazystevenz.bookstore.model.Product;
import com.crazystevenz.bookstore.model.Sale;
import com.crazystevenz.bookstore.repository.CustomerRepository;
import com.crazystevenz.bookstore.repository.ProductRepository;
import com.crazystevenz.bookstore.repository.SaleRepository;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class SaleListViewModel extends AndroidViewModel {

    private CustomerRepository customerRepository;
    private ProductRepository productRepository;
    private LiveData<List<Sale>> sales;

    public SaleListViewModel(@NonNull Application application) {
        super(application);
        SaleRepository saleRepository = new SaleRepository(application);
        customerRepository = new CustomerRepository(application);
        productRepository = new ProductRepository(application);
        sales = saleRepository.getAll();
    }

    public LiveData<List<Sale>> getAll() {
        return sales;
    }

    public Customer getCustomerById(int id) {
        return customerRepository.get(id);
    }

    public Product getProductById(int id) throws ExecutionException, InterruptedException {
        return productRepository.getProductById(id);
    }
}
