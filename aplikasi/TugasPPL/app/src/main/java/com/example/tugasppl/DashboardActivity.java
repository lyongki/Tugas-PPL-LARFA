package com.example.tugasppl;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.tugasppl.fragment.EvaluasiFragment;
import com.example.tugasppl.fragment.HistoryFragment;
import com.example.tugasppl.fragment.InventarisFragment;
import com.example.tugasppl.fragment.ListPembayaranKasFragment;
import com.example.tugasppl.fragment.RapatFragment;
import com.example.tugasppl.fragment.SuratFragment;
import com.example.tugasppl.fragment.UangFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;

public class DashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private ActionBarDrawerToggle toggle;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        init();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        preferences = getSharedPreferences("pref",MODE_PRIVATE);

        navigationView.setNavigationItemSelectedListener(this);
        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new HistoryFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_history);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(toggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START))
            drawer.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }

    private void init() {
        toolbar = findViewById(R.id.toolbar);
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toggle = new ActionBarDrawerToggle(this, drawer, R.string.open_navigation_drawer, R.string.close_navigation_drawer);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_evaluasi:
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new EvaluasiFragment()).commit();
                closeDrawer();
                break;
            case R.id.nav_history:
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new HistoryFragment()).commit();
                closeDrawer();
                break;
            case R.id.nav_inventaris:
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new InventarisFragment()).commit();
                closeDrawer();
                break;
            case R.id.nav_list:
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new ListPembayaranKasFragment()).commit();
                closeDrawer();
                break;
            case R.id.nav_rapat:
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new RapatFragment()).commit();
                closeDrawer();
                break;
            case R.id.nav_surat:
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new SuratFragment()).commit();
                closeDrawer();
                break;
            case R.id.nav_uang_masuk_keluar:
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new UangFragment()).commit();
                closeDrawer();
                break;
            case R.id.nav_logout:
                preferences.edit().clear().apply();
                Intent intent = new Intent( this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
        }
        return true;
    }

    private void closeDrawer() {
        if(drawer.isDrawerOpen(GravityCompat.START))
            drawer.closeDrawer(GravityCompat.START);
    }
}
