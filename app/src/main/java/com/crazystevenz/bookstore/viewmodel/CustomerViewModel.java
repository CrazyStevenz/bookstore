package com.crazystevenz.bookstore.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.crazystevenz.bookstore.model.Customer;
import com.crazystevenz.bookstore.repository.CustomerRepository;

import java.util.List;

public class CustomerViewModel extends AndroidViewModel {

    private CustomerRepository customerRepository;
    private LiveData<List<Customer>> customers;

    public CustomerViewModel(@NonNull Application application){
        super(application);
        customerRepository = new CustomerRepository(application);
        customers = customerRepository.getAll();
    }

    public LiveData<List<Customer>> getAll() {
        return customers;
    }

    public Customer getByName (String name) {
        return customerRepository.getByName(name);
    }
}
