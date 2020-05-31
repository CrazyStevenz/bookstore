package com.crazystevenz.bookstore.view.salelist;

import android.app.Application;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.crazystevenz.bookstore.R;
import com.crazystevenz.bookstore.model.Sale;

import java.util.List;

public class SaleListFragment extends Fragment {

    private SaleListViewModel saleListViewModel;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Application app = (getActivity().getApplication());
        final TextView textView = getView().findViewById(R.id.text_salelist);

        saleListViewModel = new ViewModelProvider.AndroidViewModelFactory(app).create(SaleListViewModel.class);
        saleListViewModel.getAll().observe(getViewLifecycleOwner(), new Observer<List<Sale>>() {
            @Override
            public void onChanged(List<Sale> sales) {
                StringBuilder sb = new StringBuilder();
                for (Sale sale : sales) {
                    sb.append(sale);
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