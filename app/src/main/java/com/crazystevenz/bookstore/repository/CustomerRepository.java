package com.crazystevenz.bookstore.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.crazystevenz.bookstore.dao.CustomerDao;
import com.crazystevenz.bookstore.model.Customer;

import java.util.List;

public class CustomerRepository {

    private CustomerDao mCustomerDao;
    private LiveData<List<Customer>> mCustomers;

    public CustomerRepository (Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        mCustomerDao = db.customerDao();
        mCustomers = mCustomerDao.getAll();
    }

    public LiveData<List<Customer>> getAll() {
        return mCustomers;
    }

    public Customer getByName (String name) {
        List<Customer> customers = mCustomers.getValue();

        if (customers == null) return null;

        for (Customer customer : customers) {
            if (customer.getName().equals(name)) return customer;
        }

        return null;
    }
}
