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

        // If no products are available
        if (product.getAmount() < 1) return false;

        // Else remove one from the product table
        product.setAmount(product.getAmount() - 1);

        // Check if the same item is already in the cart
        int currentUserId = Customer.getInstance().getId();
        Sale sale = saleRepository.getIncompleteByCustomerIdAndProductId(currentUserId, product.getId());

        // If same item is already in cart, increase amount by one
        if (sale != null) {
            sale.setProductAmount(sale.getProductAmount() + 1);
            saleRepository.update(sale);

        // Else add a new sale entry
        } else {
            saleRepository.insert(new Sale(currentUserId, product.getId(), 1));
        }

        return productRepository.addToCart(product);
    }
}
