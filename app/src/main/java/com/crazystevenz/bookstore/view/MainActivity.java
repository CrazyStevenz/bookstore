package com.crazystevenz.bookstore.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.crazystevenz.bookstore.R;
import com.crazystevenz.bookstore.model.Customer;
import com.crazystevenz.bookstore.view.login.LoginActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private MainViewModel mainViewModel;
    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_store, R.id.nav_gallery, R.id.nav_userlist)
                .setDrawerLayout(drawer)
                .build();
        final NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        // Handles drawer item clicks
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Checks if the logout button was pressed
                if (!handleNavigationItemSelected(item)) {
                    navController.navigate(item.getItemId());
                }

                // Closes drawer and navigates to selected view
                drawer.closeDrawers();
                return true;
            }
        });

        // Change the default drawer headers to customer data
        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = (TextView) headerView.findViewById(R.id.text_username);
        TextView navFullName = (TextView) headerView.findViewById(R.id.text_full_name);
        navUsername.setText(Customer.getInstance().getFullName());
        navFullName.setText("@" + Customer.getInstance().getUsername());

        // Update customer balance in the top right of the app bar
        final TextView textViewBalance = findViewById(R.id.text_balance);
        mainViewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(MainViewModel.class);
        mainViewModel.getBalance(Customer.getInstance().getId()).observe(this, new Observer<Double>() {
            @Override
            public void onChanged(Double balance) {
                DecimalFormat df = new DecimalFormat();
                df.setMaximumFractionDigits(2);
                textViewBalance.setText(df.format(balance) + "€");
            }
        });

        // Hide floating action button in cart page
        final FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.nav_cart);
                fab.hide();
            }
        });
    }

    // When logout drawer item is clicked, deletes the customer singleton and changes view to login
    public boolean handleNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.nav_logout) {
            Customer.logout();
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
            return true;
        }

        return false;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        final FloatingActionButton fab = findViewById(R.id.fab);
        fab.show();
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        final FloatingActionButton fab = findViewById(R.id.fab);
        fab.show();
    }
}
