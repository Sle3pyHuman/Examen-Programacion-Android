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
    private List<Event> eventList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_evento, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        eventList = new ArrayList<>();
        eventList.add(new Event("Evento 1", "Descripcion 1"));
        eventList.add(new Event("Evento 2", "Descripcion 2"));

        eventoAdapter = new EventoAdapter(eventList);
        recyclerView.setAdapter(eventoAdapter);

        return view;
    }
}