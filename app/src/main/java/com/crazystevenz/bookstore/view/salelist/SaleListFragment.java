package com.crazystevenz.bookstore.view.salelist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.crazystevenz.bookstore.R;
import com.crazystevenz.bookstore.model.Customer;
import com.crazystevenz.bookstore.model.Sale;
import com.crazystevenz.bookstore.viewmodel.CustomerViewModel;
import com.crazystevenz.bookstore.viewmodel.SaleViewModel;

import java.util.List;

public class SaleListFragment extends Fragment {

    private SaleListViewModel saleListViewModel;
    private SaleViewModel saleViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        saleListViewModel =
                ViewModelProviders.of(this).get(SaleListViewModel.class);
        View root = inflater.inflate(R.layout.fragment_salelist, container, false);
        final TextView textView = root.findViewById(R.id.text_salelist);
        saleListViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        saleViewModel = new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication()).create(SaleViewModel.class);
        saleViewModel.getAll().observe(getViewLifecycleOwner(), new Observer<List<Sale>>() {
            @Override
            public void onChanged(List<Sale> sales) {
                StringBuilder sb = new StringBuilder();
                for (Sale sale : sales) {
                    sb.append(sale);
                }
                textView.setText(sb);
            }
        });

        return root;
    }
}