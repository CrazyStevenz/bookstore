package com.crazystevenz.bookstore.view.cart;

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
import com.crazystevenz.bookstore.model.Customer;
import com.crazystevenz.bookstore.model.Product;
import com.crazystevenz.bookstore.model.Sale;
import com.crazystevenz.bookstore.viewmodel.SaleViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class CartFragment extends Fragment {

    private SaleViewModel saleViewModel;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_cart, container, false);

        saleViewModel = new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication()).create(SaleViewModel.class);
        saleViewModel.getIncompleteByUserId(Customer.getUserInstance().getId()).observe(getViewLifecycleOwner(), new Observer<List<Sale>>() {
            @Override
            public void onChanged(List<Sale> sales) {
                recyclerView = root.findViewById(R.id.recyclerview_product_list);

                layoutManager = new LinearLayoutManager(root.getContext());
                recyclerView.setLayoutManager(layoutManager);

                List<Product> products = new ArrayList<>();
                for (Sale sale : sales) {

                    try {
                        Product product = saleViewModel.getProductById(sale.getProductId());
                        products.add(product);
                    } catch (ExecutionException | InterruptedException e) {
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                mAdapter = new CartRecyclerViewAdapter(products);
                recyclerView.setAdapter(mAdapter);
            }
        });

        return root;
    }
}