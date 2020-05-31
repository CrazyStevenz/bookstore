package com.crazystevenz.bookstore.view.cart;

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
import com.crazystevenz.bookstore.model.Customer;
import com.crazystevenz.bookstore.model.Product;
import com.crazystevenz.bookstore.model.Sale;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class CartFragment extends Fragment {

    private CartViewModel cartViewModel;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Application app = (getActivity().getApplication());

        cartViewModel = new ViewModelProvider.AndroidViewModelFactory(app).create(CartViewModel.class);
        cartViewModel.getIncompleteByUserId(Customer.getUserInstance().getId()).observe(getViewLifecycleOwner(), new Observer<List<Sale>>() {
            @Override
            public void onChanged(List<Sale> sales) {
                recyclerView = getView().findViewById(R.id.recyclerview_product_list);

                layoutManager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(layoutManager);

                List<Product> products = new ArrayList<>();
                for (Sale sale : sales) {

                    try {
                        Product product = cartViewModel.getProductById(sale.getProductId());
                        products.add(product);
                    } catch (ExecutionException | InterruptedException e) {
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                mAdapter = new CartRecyclerViewAdapter(products);
                recyclerView.setAdapter(mAdapter);
            }
        });
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }
}