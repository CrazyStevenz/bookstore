package com.crazystevenz.bookstore.view.store;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.crazystevenz.bookstore.model.Customer;
import com.crazystevenz.bookstore.model.Product;
import com.crazystevenz.bookstore.model.Sale;
import com.crazystevenz.bookstore.repository.ProductRepository;
import com.crazystevenz.bookstore.repository.SaleRepository;

import java.util.List;

public class StoreViewModel extends AndroidViewModel {

    private SaleRepository saleRepository;
    private ProductRepository productRepository;

    public StoreViewModel(@NonNull Application application) {
        super(application);
        saleRepository = new SaleRepository(application);
        productRepository = new ProductRepository(application);
    }

    public LiveData<List<Product>> getAll() {
        return productRepository.getAll();
    }

    public boolean addToCart(List<Product> products, int position) {
        if (products == null) return false;

        Product product = products.get(position);
        if (product.getAmount() < 1) return false;
        product.setAmount(product.getAmount() - 1);

        int currentUserId = Customer.getInstance().getId();

        Sale sale = saleRepository.getIncompleteByCustomerIdAndProductId(currentUserId, product.getId());

        if (sale != null) {
            sale.setProductAmount(sale.getProductAmount() + 1);
            saleRepository.update(sale);
        } else {
            saleRepository.insert(new Sale(currentUserId, product.getId(), 1));
        }

        return productRepository.addToCart(product);
    }
}
