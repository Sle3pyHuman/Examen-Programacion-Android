package com.example.examen;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerViewEvents;
    private EventoAdapter eventoAdapter;
    private Button btnVerMapa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerViewEvents = findViewById(R.id.recyclerView);
        btnVerMapa = findViewById(R.id.bottom_map);

        // Initialize the list of events
        List<Evento> eventList = getEvents();

        // Set up RecyclerView
        recyclerViewEvents.setLayoutManager(new LinearLayoutManager(this));
        eventoAdapter = new EventoAdapter(eventList, this); // Pass List<Evento> and Context
        recyclerViewEvents.setAdapter(eventoAdapter);

        // Button to navigate to map
        btnVerMapa.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, MapaActivity.class);
            startActivity(intent);
        });

        // Bottom Navigation setup
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.bottom_home:
                    // Stay on the same activity
                    return true;
                case R.id.bottom_map:
                    Intent intent = new Intent(HomeActivity.this, MapaActivity.class);
                    startActivity(intent);
                    return true;
            }
            return false;
        });
    }

    private List<Evento> getEvents() {
        List<Evento> events = new ArrayList<>();
        // Populate list with sample data for testing
        events.add(new Evento("Title 1", "Description 1", R.drawable.flayer));
        events.add(new Evento("Title 2", "Description 2", R.drawable.sample_image2));
        return events;
    }
}