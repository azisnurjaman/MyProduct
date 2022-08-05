package com.example.myproduct;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private String TAG = MainActivity.class.getSimpleName();

    private FrameLayout container;
    private BottomNavigationView nav;

    private final BerandaFragment berandaFragment = new BerandaFragment();
    private final PelangganFragment pelangganFragment = new PelangganFragment();
    private final ProdukFragment produkFragment = new ProdukFragment();
    private final UserFragment userFragment = new UserFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        loadFragment(berandaFragment);

        nav.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.action_beranda : loadFragment(berandaFragment);
                                return true;
                            case R.id.action_pelanggan: loadFragment(pelangganFragment);
                                return true;
                            case R.id.action_produk : loadFragment(produkFragment);
                                return true;
                            case R.id.action_profile : loadFragment(userFragment);
                                return true;
                        }
                        return false;
                    }
                }
        );
    }

    private void initView(){
        container = findViewById(R.id.container);
        nav = findViewById(R.id.nav);
    }

    private void loadFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();
    }
}