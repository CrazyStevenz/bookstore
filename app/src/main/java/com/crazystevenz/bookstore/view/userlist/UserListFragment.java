package com.crazystevenz.bookstore.view.userlist;

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
import com.crazystevenz.bookstore.viewmodel.CustomerViewModel;

import java.util.List;

public class UserListFragment extends Fragment {

    private UserListViewModel userListViewModel;
    private CustomerViewModel customerViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        userListViewModel =
                ViewModelProviders.of(this).get(UserListViewModel.class);
        View root = inflater.inflate(R.layout.fragment_userlist, container, false);
        final TextView textView = root.findViewById(R.id.text_userlist);
        userListViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        customerViewModel = new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication()).create(CustomerViewModel.class);
        customerViewModel.getAll().observe(getViewLifecycleOwner(), new Observer<List<Customer>>() {
            @Override
            public void onChanged(List<Customer> customers) {
                StringBuilder sb = new StringBuilder();
                for (Customer customer : customers) {
                    sb.append(customer);
                }
                textView.setText(sb);
            }
        });

        return root;
    }
}
