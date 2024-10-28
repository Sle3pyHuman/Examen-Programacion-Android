package com.example.examen;

public class Evento {
    private String titulo;
    private String descripcion;
    private String fecha;
    private String hora_inicio;
    private String imagenURL;


    public Evento(String titulo, String descripcion, String fecha, String horaInicio, String imagenURL) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha = fecha;
        hora_inicio = horaInicio;
        this.imagenURL = imagenURL;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(String hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getImagenURL() {
        return imagenURL;
    }

    public void setImagenURL(String imagenURL) {
        this.imagenURL = imagenURL;
    }
}
