package com.example.examen;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity {

private DrawerLayout dwLayout;
private NavigationView navView;
private ActionBarDrawerToggle toggle;
private BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.home), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        dwLayout = findViewById(R.id.home);
        navView = findViewById(R.id.nav_view);
        bottomNav = findViewById(R.id.bottom_nav_view);

        toggle = new ActionBarDrawerToggle(this, dwLayout, R.string.open, R.string.close);
        dwLayout.addDrawerListener(toggle);
        toggle.syncState();

        getSupportFragmentManager().beginTransaction().replace(R.id.frangment_container, new HomeFragment()).commit();

        // Manejo del navigation
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragmentSeleccionado = null;
                int id = item.getItemId();
                if (id == R.id.nav_ver_eventos) {
                    fragmentSeleccionado = new EventoFragment();
                } else if (id == R.id.nav_filtrar) {
                    fragmentSeleccionado = new FiltroFragment();
                } else if (id == R.id.nav_publicar) {
                    fragmentSeleccionado = new PublicarFragment();
                } else if (id == R.id.nav_logout) {
                    logout();
                    return true;
                }

                if (fragmentSeleccionado != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.frangment_container, fragmentSeleccionado).commit();
                }

                dwLayout.closeDrawers();
                return true;
            }
        });

        // Manejo de bottom navigation
        bottomNav.setOnNavigationItemSelectedListener(item -> {
            Fragment fragmentSeleccionado = null;
            int id = item.getItemId();
            if (id == R.id.bottom_home) {
                fragmentSeleccionado = new HomeFragment();
            } else if ( id == R.id.bottom_map) {
                fragmentSeleccionado = new MapaFragment();
            }

            if (fragmentSeleccionado != null) {
                cargarFragmento(fragmentSeleccionado);
            }
            return true;
        });
    }

    private void cargarFragmento(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frangment_container, fragment).commit();
    }

    private void logout() {
        Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}