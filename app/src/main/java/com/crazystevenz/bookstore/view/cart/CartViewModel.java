package com.crazystevenz.bookstore.view.cart;

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

    public boolean removeFromCart(Product product) {
        if (product.getAmount() <= 0) return false;

        // Return false if sale entry doesn't exist
        int currentUserId = Customer.getInstance().getId();
        Sale sale = saleRepository.getIncompleteByCustomerIdAndProductId(currentUserId, product.getId());
        if (sale == null) return false;

        // If amount was more than one, decrement
        if (product.getAmount() > 1) {
            sale.setProductAmount(sale.getProductAmount() - 1);
            saleRepository.update(sale);

        // If amount was one, delete sale
        } else {
            saleRepository.delete(sale);
        }

        // Put the item back to the store
        productRepository.incrementAmount(product.getId());

        return true;
    }
}
