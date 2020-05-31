package com.crazystevenz.bookstore.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.crazystevenz.bookstore.dao.SaleDao;
import com.crazystevenz.bookstore.model.Sale;

import java.util.List;

public class SaleRepository {

    private SaleDao mSaleDao;
    private LiveData<List<Sale>> mSales;

    public SaleRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        mSaleDao = db.saleDao();
        mSales = mSaleDao.getAll();
    }

    public LiveData<List<Sale>> getAll() {
        return mSales;
    }

    public LiveData<List<Sale>> getIncompleteByCustomerId(int id) {
        return mSaleDao.getIncompleteByCustomerId(id);
    }

    public Sale getIncompleteByCustomerIdAndProductId(int customerId, int productId) {
        return mSaleDao.getIncompleteByCustomerIdAndProductId(customerId, productId);
    }

    public void buy(int customerId) {
        mSaleDao.buy(customerId);
    }

    public void insert(Sale sale) {
        mSaleDao.insert(sale);
    }

    public void update(Sale sale) {
        mSaleDao.update(sale);
    }

    public void delete(Sale sale) {
        mSaleDao.delete(sale);
    }
}
