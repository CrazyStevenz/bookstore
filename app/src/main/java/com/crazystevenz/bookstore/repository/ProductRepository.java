package com.crazystevenz.bookstore.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.crazystevenz.bookstore.dao.ProductDao;
import com.crazystevenz.bookstore.model.Product;

import java.util.List;
import java.util.concurrent.ExecutionException;

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

    public Product getProductById(int id) throws ExecutionException, InterruptedException {
        return new GetProductByIdAsyncTask(mProductDao).execute(id).get();
    }

    private static class GetProductByIdAsyncTask extends AsyncTask<Integer, Void, Product> {
        private ProductDao productDao;

        private GetProductByIdAsyncTask(ProductDao productDao) {
            this.productDao = productDao;
        }

        @Override
        protected Product doInBackground(Integer... integers) {
            return productDao.get(integers[0]);
        }
    }
}
