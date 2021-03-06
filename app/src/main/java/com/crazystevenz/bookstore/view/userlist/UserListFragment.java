package com.crazystevenz.bookstore.view.userlist;

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
import com.crazystevenz.bookstore.model.Customer;

import java.util.List;

public class UserListFragment extends Fragment {

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Application app = (getActivity().getApplication());
        final TextView textView = getView().findViewById(R.id.text_userlist);

        // Load and display customer data to the fragment
        UserListViewModel userlistViewModel =
                new ViewModelProvider.AndroidViewModelFactory(app).create(UserListViewModel.class);
        userlistViewModel.getAll().observe(getViewLifecycleOwner(), new Observer<List<Customer>>() {
            @Override
            public void onChanged(List<Customer> customers) {
                StringBuilder sb = new StringBuilder();
                for (Customer customer : customers) {
                    sb.append(customer);
                }
                textView.setText(sb);
            }
        });
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_userlist, container, false);
    }
}
