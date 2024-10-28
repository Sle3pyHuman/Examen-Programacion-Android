package com.example.examen;

import android.media.metrics.Event;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class EventoFragment extends Fragment {
    private RecyclerView recyclerView;
    private EventoAdapter eventoAdapter;
    private List<Evento> eventoList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_evento, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        eventoList = new ArrayList<>();
        eventoList.add(new Evento("Evento 1", "Descripción del Evento 1", "2024-10-30", "18:00", ""));
        eventoList.add(new Evento("Evento 2", "Descripción del Evento 2", "2024-11-01", "20:00", ""));

        eventoAdapter = new EventoAdapter(eventoList);
        recyclerView.setAdapter(eventoAdapter);

        return view;
    }
}