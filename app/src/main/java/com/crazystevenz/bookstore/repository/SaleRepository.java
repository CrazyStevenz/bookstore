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

    public LiveData<List<Sale>> getIncompleteByUserId(int id) {
        return mSaleDao.getIncompleteByUserId(id);
    }
}
