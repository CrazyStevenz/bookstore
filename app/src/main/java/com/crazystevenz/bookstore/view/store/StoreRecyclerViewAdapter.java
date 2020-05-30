package com.crazystevenz.bookstore.view.store;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.crazystevenz.bookstore.R;
import com.crazystevenz.bookstore.model.Product;

import java.util.List;

public class StoreRecyclerViewAdapter extends RecyclerView.Adapter<StoreRecyclerViewAdapter.ProductHolder> {
    private List<Product> mProducts;

    public StoreRecyclerViewAdapter(List<Product> products) {
        mProducts = products;
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_store_item, parent, false);
        return new ProductHolder(itemView);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {
        // Get element from products list at this position
        // and replace the contents of the view with that element
        Product currentProduct = mProducts.get(position);
        holder.textViewName.setText(currentProduct.getName());
        holder.textViewPrice.setText(currentProduct.getPrice() + " €");
    }

    // Return the size of the products list (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    // Provide a reference to the views for each data item
    public static class ProductHolder extends RecyclerView.ViewHolder {
        private TextView textViewName, textViewPrice;
        private ImageButton buttonAddToCart;

        public ProductHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.text_name);
            textViewPrice = itemView.findViewById(R.id.text_price);
            buttonAddToCart = itemView.findViewById(R.id.button_addToCart);
        }
    }
}