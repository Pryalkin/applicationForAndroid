package com.Kaftanchokova.applicationforstudents.application;

import static com.Kaftanchokova.applicationforstudents.constants.Role.ROLE_APPLICANT;
import static com.Kaftanchokova.applicationforstudents.constants.Role.ROLE_SUPER_ADMIN;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.Kaftanchokova.applicationforstudents.R;
import com.Kaftanchokova.applicationforstudents.constants.Constants;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class ApplicationActivity extends AppCompatActivity {

    private NavController navController;
    private NavigationView navigationView;
    private AppBarConfiguration appBarConfiguration;
    private DrawerLayout drawerLayout;
    private NavController.OnDestinationChangedListener listener;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application);
        if (Constants.role.equals(ROLE_SUPER_ADMIN.name())) {
            Navigation.findNavController(this, R.id.fragment).setGraph(R.navigation.application_for_super_admin);
        } else if (Constants.role.equals(ROLE_APPLICANT.name())) {
            Navigation.findNavController(this, R.id.fragment).setGraph(R.navigation.application_for_applicant);
        }
        navController = Navigation.findNavController(this, R.id.fragment);
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);
        if (Constants.role.equals(ROLE_SUPER_ADMIN.name())){
            navigationView.inflateMenu(R.menu.application_menu_for_super_admin);
        } else if (Constants.role.equals(ROLE_APPLICANT.name())) {
            navigationView.inflateMenu(R.menu.application_menu_for_applicant);
        } else {
            navigationView.inflateMenu(R.menu.application_menu);
        }
        NavigationUI.setupWithNavController(navigationView, navController);

        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).setDrawerLayout(drawerLayout).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNav);
        NavController navCon = Navigation.findNavController(this, R.id.fragment);
        NavigationUI.setupWithNavController(bottomNavigationView, navCon);
        bottomNavigationView.setVisibility(View.INVISIBLE);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.fragment);
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp();
    }

}