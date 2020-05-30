package com.crazystevenz.bookstore.repository;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.crazystevenz.bookstore.dao.CustomerDao;
import com.crazystevenz.bookstore.dao.ProductDao;
import com.crazystevenz.bookstore.dao.SaleDao;
import com.crazystevenz.bookstore.model.Customer;
import com.crazystevenz.bookstore.model.Product;
import com.crazystevenz.bookstore.model.Sale;

@Database(entities = {Customer.class, Product.class, Sale.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;

    public abstract CustomerDao customerDao();
    public abstract ProductDao productDao();
    public abstract SaleDao saleDao();

    public static synchronized AppDatabase getInstance(Context context) {
        // If the instance does not exist, create one
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "app_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        // Populates the database when it is created
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    // Async task for populating the database on the background thread
    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private CustomerDao customerDao;
        private ProductDao productDao;

        public PopulateDbAsyncTask(AppDatabase db) {
            this.customerDao = db.customerDao();
            this.productDao = db.productDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            customerDao.insert(new Customer("stef", "1234", 50.0));
            customerDao.insert(new Customer("bob", "1234", 10.0));
            productDao.insert(new Product("Permanent Record", 3,  9.99));
            productDao.insert(new Product("1984", 1,  14.99));
            productDao.insert(new Product("The Talent Code", 12,  20));
            productDao.insert(new Product("What If?", 0,  5.99));
            return null;
        }
    }
}
