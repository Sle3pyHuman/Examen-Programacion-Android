package com.example.examen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;

import java.util.List;

public class EventoAdapter extends RecyclerView.Adapter<EventoAdapter.EventViewHolder> {
    private Context context;
    private List<Evento> eventoList;

    public EventoAdapter(Context context, List<Evento> eventoList){
        this.context = context;
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
        Evento evento = eventoList.get(position);

        holder.eventTitulo.setText(evento.getTitulo());
        holder.eventDescripcion.setText(evento.getDescripcion());

        Glide.with(context).load(evento.getImagenURL()).placeholder(R.drawable.flayer).into(holder.eventImagen);
    }

    @Override
    public int getItemCount() {
        return eventoList.size();
    }

    public static class EventViewHolder extends RecyclerView.ViewHolder {
        TextView eventTitulo, eventDescripcion;
        ImageView eventImagen;

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            eventTitulo = itemView.findViewById(R.id.titulo_evento);
            eventDescripcion = itemView.findViewById(R.id.descripcion_evento);
            eventImagen = itemView.findViewById(R.id.flayer);
        }
    }
}
