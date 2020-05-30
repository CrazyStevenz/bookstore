package com.crazystevenz.bookstore.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.crazystevenz.bookstore.model.Sale;
import com.crazystevenz.bookstore.repository.SaleRepository;

import java.util.List;

public class SaleViewModel extends AndroidViewModel {
    private SaleRepository saleRepository;
    private LiveData<List<Sale>> sales;

    public SaleViewModel(@NonNull Application application){
        super(application);
        saleRepository = new SaleRepository(application);
        sales = saleRepository.getAll();
    }

    public LiveData<List<Sale>> getAll() {
        return sales;
    }
}
