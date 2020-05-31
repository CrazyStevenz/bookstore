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

    public Customer get(int id) {
        return mCustomerDao.get(id);
    }

    public LiveData<Double> getBalance(int id) {
        return mCustomerDao.getBalance(id);
    }

    public LiveData<List<Customer>> getAll() {
        return mCustomers;
    }

    public Customer getByName(String name) {
        List<Customer> customers = mCustomers.getValue();

        if (customers == null) return null;

        // Find the customer using their name and return it
        for (Customer customer : customers) {
            if (customer.getUsername().equals(name)) return customer;
        }

        // If a customer with that name is not found
        return null;
    }

    public void update(Customer customer) {
        mCustomerDao.update(customer);
    }
}
