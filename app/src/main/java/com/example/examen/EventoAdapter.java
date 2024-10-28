package com.example.examen;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EventoAdapter extends RecyclerView.Adapter<EventoAdapter.EventViewHolder> {
    private List<Evento> eventoList;
    private Context context;

    public EventoAdapter(List<Evento> eventoList, Context context){
        this.eventoList = eventoList;
        this.context = context;
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

        holder.textViewTitulo.setText(evento.getTitulo());
        holder.textViewDescripcion.setText(evento.getDescripcion());
        holder.textViewFecha.setText(evento.getFecha());
        holder.textViewHora.setText(evento.getHora_inicio());

    }

    @Override
    public int getItemCount() {
        return eventoList.size();
    }

    public static class EventViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitulo, textViewDescripcion, textViewFecha, textViewHora;
        ImageView eventImagen;

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitulo = itemView.findViewById(R.id.titulo_evento);
            eventImagen = itemView.findViewById(R.id.flayer);
            textViewDescripcion = itemView.findViewById(R.id.descripcion_evento);
            textViewFecha = itemView.findViewById(R.id.fecha_evento);
            textViewHora = itemView.findViewById(R.id.horaInicio_Evento);
        }
    }
}
