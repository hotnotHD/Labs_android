package com.example.lab3_rework;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity{

    NavController navContr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavigationView view = findViewById(R.id.drawer_nav_view);
        navContr = Navigation.findNavController(this, R.id.nav_host);
        NavigationUI.setupActionBarWithNavController(this, navContr);
        NavigationUI.setupWithNavController(view, navContr);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return navContr.navigateUp();
    }

}