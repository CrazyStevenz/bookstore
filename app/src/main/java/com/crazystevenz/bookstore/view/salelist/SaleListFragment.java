package com.crazystevenz.bookstore.view.salelist;

import android.app.Application;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.crazystevenz.bookstore.R;
import com.crazystevenz.bookstore.model.Customer;
import com.crazystevenz.bookstore.model.Product;
import com.crazystevenz.bookstore.model.Sale;

import java.text.DecimalFormat;
import java.util.List;

public class SaleListFragment extends Fragment {

    private SaleListViewModel saleListViewModel;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Application app = (getActivity().getApplication());
        final TextView textView = getView().findViewById(R.id.text_salelist);

        // Load and display sale data to the fragment
        saleListViewModel = new ViewModelProvider.AndroidViewModelFactory(app).create(SaleListViewModel.class);
        saleListViewModel.getAll().observe(getViewLifecycleOwner(), new Observer<List<Sale>>() {
            @Override
            public void onChanged(List<Sale> sales) {
                StringBuilder sb = new StringBuilder();
                for (Sale sale : sales) {
                    try {
                        Customer customer = saleListViewModel.getCustomerById(sale.getCustomerId());
                        Product product = saleListViewModel.getProductById(sale.getProductId());

                        sb.append(customer.getFullName());
                        sb.append(" - ");
                        sb.append(product.getName());
                        sb.append(" - ");
                        sb.append(product.getAmount());
                        sb.append("\n");
                        if (sale.isComplete()) sb.append("Complete");
                        else sb.append("In cart");

                        sb.append("\n\n");
                    } catch (Exception e) {
                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                textView.setText(sb);
            }
        });
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_salelist, container, false);
    }
}