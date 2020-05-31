package com.crazystevenz.bookstore.view.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.crazystevenz.bookstore.R;
import com.crazystevenz.bookstore.model.Customer;
import com.crazystevenz.bookstore.view.MainActivity;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText = findViewById(R.id.et_name);
        passwordEditText = findViewById(R.id.et_password);
        loginButton = findViewById(R.id.login_button);

        // Initializes the loginViewModel so it can use the customer repository
        // onChanged is irrelevant and is ignored
        loginViewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(LoginViewModel.class);
        loginViewModel.getAll().observe(this, new Observer<List<Customer>>() {
            @Override
            public void onChanged(List<Customer> customers) {}
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                // Checks for empty fields
                if (username.trim().isEmpty() || password.trim().isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Empty fields are not allowed", Toast.LENGTH_SHORT).show();
                } else {
                    login(username, password);
                }
            }

            // Compares credentials with DB
            void login (String username, String password) {
                Customer customer = loginViewModel.getByName(username);
                if (credentialsAreValid(customer, username, password)) {
                    customer.login();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                }
            }

            boolean credentialsAreValid (Customer customer, String username, String password) {
                return customer != null
                    && customer.getUsername().equals(username)
                    && customer.getPassword().equals(password);
            }
        });
    }
}
