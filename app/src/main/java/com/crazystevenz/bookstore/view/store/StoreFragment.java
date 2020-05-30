package com.crazystevenz.bookstore.view.store;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.crazystevenz.bookstore.R;
import com.crazystevenz.bookstore.model.Product;
import com.crazystevenz.bookstore.viewmodel.ProductViewModel;

import java.util.List;

public class StoreFragment extends Fragment {

    private ProductViewModel productViewModel;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_store, container, false);

        productViewModel = new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication()).create(ProductViewModel.class);
        productViewModel.getAll().observe(getViewLifecycleOwner(), new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                recyclerView = (RecyclerView) root.findViewById(R.id.recyclerview_product_list);

                layoutManager = new LinearLayoutManager(root.getContext());
                recyclerView.setLayoutManager(layoutManager);

                mAdapter = new StoreRecyclerViewAdapter(products);
                recyclerView.setAdapter(mAdapter);
            }
        });

        return root;
    }
}