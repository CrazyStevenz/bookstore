package com.crazystevenz.bookstore.view;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.crazystevenz.bookstore.repository.CustomerRepository;

public class MainViewModel extends AndroidViewModel {

    private CustomerRepository customerRepository;

    public MainViewModel(@NonNull Application application) {
        super(application);
        customerRepository = new CustomerRepository(application);
    }

    public LiveData<Double> getBalance(int id) {
        return customerRepository.getBalance(id);
    }
}
