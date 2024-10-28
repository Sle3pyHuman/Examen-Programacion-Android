package com.example.examen;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EventoAdapter extends RecyclerView.Adapter<EventoAdapter.EventViewHolder> {
    private List<Evento> eventoList;

    public EventoAdapter(List<Evento> eventoList){
        this.eventoList = eventoList;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.evento_item, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventoAdapter.EventViewHolder holder, int position) {
        Event evento = eventoList.get(position);
        holder.eventTitulo.setText(evento.getTitle());
        holder.eventDescripcion.setText(evento.getDescription());
    }

    @Override
    public int getItemCount() {
        return eventoList.size();
    }

    public static class EventViewHolder extends RecyclerView.ViewHolder {
        TextView eventTitulo, eventDescripcion;

        public EventViewHolder(@NonNull view itemView) {
            super(itemView);
            eventTitulo = itemView.findViewById(R.id.titulo_evento);
            eventDescripcion = itemView.findViewById(R.id.descripcion_evento);
        }
    }
}
