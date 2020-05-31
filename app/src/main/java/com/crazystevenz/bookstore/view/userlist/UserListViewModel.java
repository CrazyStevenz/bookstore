package com.crazystevenz.bookstore.view.userlist;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.crazystevenz.bookstore.model.Customer;
import com.crazystevenz.bookstore.repository.CustomerRepository;

import java.util.List;

public class UserListViewModel extends AndroidViewModel {

    private CustomerRepository customerRepository;

    public UserListViewModel(@NonNull Application application) {
        super(application);
        customerRepository = new CustomerRepository(application);
    }

    public LiveData<List<Customer>> getAll() {
        return customerRepository.getAll();
    }
}