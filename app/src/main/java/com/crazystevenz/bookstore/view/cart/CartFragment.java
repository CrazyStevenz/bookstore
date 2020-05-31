package com.crazystevenz.bookstore.view.cart;

import android.app.Application;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class CartFragment extends Fragment implements CartRecyclerViewAdapter.EventListener {

    private CartViewModel cartViewModel;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Product> mProducts = new ArrayList<>();

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Application app = (getActivity().getApplication());
        final Button buy = getView().findViewById(R.id.button_buy);

        cartViewModel = new ViewModelProvider.AndroidViewModelFactory(app).create(CartViewModel.class);
        cartViewModel.getIncompleteByUserId(Customer.getInstance().getId()).observe(getViewLifecycleOwner(), new Observer<List<Sale>>() {
            @Override
            public void onChanged(List<Sale> sales) {
                recyclerView = getView().findViewById(R.id.recyclerview_product_list);

                layoutManager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(layoutManager);

                mProducts.clear();
                for (Sale sale : sales) {
                    try {
                        // Get the product object using the product id
                        Product product = cartViewModel.getProductById(sale.getProductId());
                        // Set product amount to cart amount
                        product.setAmount(sale.getProductAmount());
                        mProducts.add(product);
                    } catch (ExecutionException | InterruptedException e) {
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                if (mProducts.isEmpty()) {
                    buy.setVisibility(View.GONE);
                } else {
                    double totalCost = 0;
                    for (Product product : mProducts) {
                        totalCost += product.getAmount() * product.getPrice();
                    }
                    DecimalFormat df = new DecimalFormat();
                    df.setMaximumFractionDigits(2);
                    buy.setText("BUY - " + df.format(totalCost) + "€");
                    buy.setVisibility(View.VISIBLE);
                }

                mAdapter = new CartRecyclerViewAdapter(mProducts, CartFragment.this);
                recyclerView.setAdapter(mAdapter);
            }
        });

        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartViewModel.buy(mProducts);
            }
        });
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }

    public void onRemoveClick(Product product) {
        if (cartViewModel.removeFromCart(product)) {
//            Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
        }
    }
}
