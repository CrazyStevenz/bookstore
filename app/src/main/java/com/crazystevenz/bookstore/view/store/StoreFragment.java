package com.crazystevenz.bookstore.view.store;

import android.app.Application;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.crazystevenz.bookstore.R;
import com.crazystevenz.bookstore.model.Product;

import java.util.List;

public class StoreFragment extends Fragment implements StoreRecyclerViewAdapter.EventListener {

    private StoreViewModel storeViewModel;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Product> mProducts;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Application app = (getActivity().getApplication());

        storeViewModel = new ViewModelProvider.AndroidViewModelFactory(app).create(StoreViewModel.class);
        storeViewModel.getAll().observe(getViewLifecycleOwner(), new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                recyclerView = getView().findViewById(R.id.recyclerview_product_list);

                mProducts = products;

                layoutManager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(layoutManager);

                mAdapter = new StoreRecyclerViewAdapter(products, StoreFragment.this);
                recyclerView.setAdapter(mAdapter);
            }
        });
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_store, container, false);
    }

    public void onAddClick(int position) {
        if (storeViewModel.addToCart(mProducts, position)) {
            Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
        }
    }
}
