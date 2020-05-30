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
import androidx.lifecycle.ViewModelProviders;

import com.crazystevenz.bookstore.R;

public class SaleFragment extends Fragment {

    private SaleViewModel saleViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        saleViewModel =
                ViewModelProviders.of(this).get(SaleViewModel.class);
        View root = inflater.inflate(R.layout.fragment_salelist, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        saleViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}