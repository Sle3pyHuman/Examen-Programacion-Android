package com.example.examen.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteDB extends SQLiteOpenHelper {

    // Nombre y versión de la base de datos
    private static final String DATABASE_NAME = "ProgramaYa.db";
    private static final int DATABASE_VERSION = 1;

    // Tabla Usuario
    private static final String TABLA_USUARIOS = "Usuarios";
    private static final String COLUMNA_USUARIO_ID = "id";
    private static final String COLUMNA_USUARIO_NOMBRE = "nombre";
    private static final String COLUMNA_USUARIO_EMAIL = "email";
    private static final String COLUMNA_USUARIO_CONTRASEÑA = "contraseña";
    private static final String COLUMNA_USUARIO_ROL = "rol";

    // Tabla Evento
    private static final String TABLA_EVENTOS = "Eventos";
    private static final String COLUMNA_EVENTO_ID = "id";
    private static final String COLUMNA_EVENTO_NOMBRE = "nombre";
    private static final String COLUMNA_EVENTO_DESCRIPCION = "descripcion";
    private static final String COLUMNA_EVENTO_TIPO = "tipo_evento";
    private static final String COLUMNA_EVENTO_FECHA = "fecha";
    private static final String COLUMNA_EVENTO_HORA_INICIO = "hora_inicio";
    private static final String COLUMNA_EVENTO_LUGAR_ID = "lugar_id";

    // Tabla Lugar
    private static final String TABLA_LUGARS = "Lugar";
    private static final String COLUMNA_LUGAR_ID = "id";
    private static final String COLUMNA_LUGAR_NOMBRE = "nombre";
    private static final String COLUMNA_LUGAR_DIRECCION = "direccion";
    private static final String COLUMNA_LUGAR_LATITUD = "latitud";
    private static final String COLUMNA_LUGAR_LONGITUD = "longitud";

    // Tabla NotificarEvento
    private static final String TABLA_NOTIFICACION = "Usuarios";
    private static final String COLUMNA_NOTIFICACION_USUARIO_ID = "usuario_id";
    private static final String COLUMNA_NOTIFICACION_EVENTO_ID = "evento_id";
    private static final String COLUMNA_NOTIFICACION_FECHA = "fecha";
    private static final String COLUMNA_NOTIFICACION_ESTADO = "estado";

    // Constructor
    public SQLiteDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Método para crear la base de datos (se ejecuta cuando la base de datos es creada por primera vez)
    @Override
    public void onCreate(android.database.sqlite.SQLiteDatabase db) {
        // Crear tabla Usuarios
        String sqlCrearTablaUsuarios = "CREATE TABLE " + TABLA_USUARIOS + " (" +
                COLUMNA_USUARIO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMNA_USUARIO_NOMBRE + " TEXT, " +
                COLUMNA_USUARIO_EMAIL + " TEXT, " +
                COLUMNA_USUARIO_CONTRASEÑA + " TEXT, " +
                COLUMNA_USUARIO_ROL + " TEXT " +
                ");";
        db.execSQL(sqlCrearTablaUsuarios);

        // Crear tabla Evento
        String sqlCrearTablaEvetos = "CREATE TABLE " + TABLA_EVENTOS + " (" +
                COLUMNA_EVENTO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMNA_EVENTO_NOMBRE + " TEXT, " +
                COLUMNA_EVENTO_DESCRIPCION + " TEXT, " +
                COLUMNA_EVENTO_TIPO + " TEXT, " +
                COLUMNA_EVENTO_FECHA + " TEXT, " +
                COLUMNA_EVENTO_HORA_INICIO + " TEXT, " +
                COLUMNA_EVENTO_LUGAR_ID + " INTEGER, " +
                "FOREIGN KEY (" + COLUMNA_EVENTO_LUGAR_ID + ") REFERENCES " + TABLA_LUGARS + "(" + COLUMNA_LUGAR_ID + ")" +
                ");";
        db.execSQL(sqlCrearTablaEvetos);

        // Crear tabla Lugar
        String sqlCrearTablaLugares = "CREATE TABLE " + TABLA_LUGARS + " (" +
                COLUMNA_LUGAR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMNA_LUGAR_NOMBRE + " TEXT, " +
                COLUMNA_LUGAR_DIRECCION + " TEXT, " +
                COLUMNA_LUGAR_LATITUD + " TEXT, " +
                COLUMNA_LUGAR_LONGITUD + " TEXT " +
                ");";
        db.execSQL(sqlCrearTablaLugares);

        // Crear tabla NotificarEvento
        String sqlCrearTablaNotificacion = "CREATE TABLE " + TABLA_NOTIFICACION + " (" +
                COLUMNA_NOTIFICACION_USUARIO_ID + " INTEGER, " +
                COLUMNA_NOTIFICACION_EVENTO_ID + " INTEGER, " +
                COLUMNA_NOTIFICACION_FECHA + " TEXT, " +
                COLUMNA_NOTIFICACION_ESTADO + " TEXT, " +
                // Clave primaria compuesta
                "PRIMARY KEY (" + COLUMNA_NOTIFICACION_USUARIO_ID + ", " + COLUMNA_NOTIFICACION_EVENTO_ID + "), " +
                "FOREIGN KEY (" + COLUMNA_NOTIFICACION_USUARIO_ID + ") REFERENCES " + TABLA_USUARIOS + "(" + COLUMNA_USUARIO_ID + "), " +
                "FOREIGN KEY (" + COLUMNA_NOTIFICACION_EVENTO_ID + ") REFERENCES " + TABLA_EVENTOS + "(" + COLUMNA_EVENTO_ID + ")" +
                ");";
        db.execSQL(sqlCrearTablaNotificacion);
    }

    // Método para actualizar la base de datos (cuando se cambia la versión de la base de datos)
    @Override
    public void onUpgrade(android.database.sqlite.SQLiteDatabase db, int i, int i1) {
        // Eliminar tablas si existen y volver a crearlas
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_USUARIOS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_EVENTOS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_LUGARS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_NOTIFICACION);
        onCreate(db);
    }

// USUARIOS
    // Método para insertar un usuario en la base de datos
    public long insertarUsuario(String nombre, String email, String contraseña, String rol) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMNA_USUARIO_NOMBRE, nombre);
        values.put(COLUMNA_USUARIO_EMAIL, email);
        values.put(COLUMNA_USUARIO_CONTRASEÑA, contraseña);
        values.put(COLUMNA_USUARIO_ROL, rol);
        return db.insert(TABLA_USUARIOS, null, values);
    }

    // Método para actualizar un usuario
    public int actualizarUsuario(int id, String nombre, String email, String contraseña, String rol) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMNA_USUARIO_NOMBRE, nombre);
        values.put(COLUMNA_USUARIO_EMAIL, email);
        values.put(COLUMNA_USUARIO_CONTRASEÑA, contraseña);
        values.put(COLUMNA_USUARIO_ROL, rol);

        String selection = COLUMNA_USUARIO_ID + " = ?";
        String[] selectionArgs = { String.valueOf(id) };

        return db.update(TABLA_USUARIOS, values, selection, selectionArgs);
    }

    // Método para eliminar un usuario
    public int eliminarUsuario(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = COLUMNA_USUARIO_ID + " = ?";
        String[] selectionArgs = { String.valueOf(id) };

        return db.delete(TABLA_USUARIOS, selection, selectionArgs);
    }

// EVENTOS
    public long insertarEventos(String nombre, String descripcion, String tipo_evento, String fecha, String hora_inicio, int lugar) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMNA_EVENTO_NOMBRE, nombre);
        values.put(COLUMNA_EVENTO_DESCRIPCION, descripcion);
        values.put(COLUMNA_EVENTO_TIPO, tipo_evento);
        values.put(COLUMNA_EVENTO_FECHA, fecha);
        values.put(COLUMNA_EVENTO_HORA_INICIO, hora_inicio);
        values.put(COLUMNA_EVENTO_LUGAR_ID, lugar);

        return db.insert(TABLA_EVENTOS, null, values);
    }

    public int eliminarEventos(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = COLUMNA_EVENTO_ID + " = ?";
        String[] selectionArgs = { String.valueOf(id) };

        return db.delete(TABLA_EVENTOS, selection, selectionArgs);
    }

// LUGARES
    public long insertarLugar(String nombre, String direccion, String latitud, String longitud) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMNA_LUGAR_NOMBRE, nombre);
        values.put(COLUMNA_LUGAR_DIRECCION, direccion);
        values.put(COLUMNA_LUGAR_LATITUD, latitud);
        values.put(COLUMNA_LUGAR_LONGITUD, longitud);

        return db.insert(TABLA_LUGARS, null, values);
    }

// NOTIFICACION
    public long insertarNotificacion(int usuario_id, int evento_id, String fecha, String estado) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Verificar si ya existe la notificación
        String selection = COLUMNA_NOTIFICACION_USUARIO_ID + " = ? AND " + COLUMNA_NOTIFICACION_EVENTO_ID + " = ?";
        String[] selectionArgs = { String.valueOf(usuario_id), String.valueOf(evento_id) };

        Cursor cursor = db.query(TABLA_NOTIFICACION, null, selection, selectionArgs, null, null, null);
        if (cursor.moveToFirst()) {
            cursor.close();
            return -1;  // Retornar -1 si ya existe una notificación
        }
        cursor.close();

        // Insertar nueva notificación si no existe
        ContentValues values = new ContentValues();
        values.put(COLUMNA_NOTIFICACION_USUARIO_ID, usuario_id);
        values.put(COLUMNA_NOTIFICACION_EVENTO_ID, evento_id);
        values.put(COLUMNA_NOTIFICACION_FECHA, fecha);
        values.put(COLUMNA_NOTIFICACION_ESTADO, estado);

        return db.insert(TABLA_NOTIFICACION, null, values);
    }


    public int eliminarNotificacion(int usuario_id, int evento_id) {
        SQLiteDatabase db = this.getWritableDatabase();

        String selection = COLUMNA_NOTIFICACION_USUARIO_ID + " = ? AND " + COLUMNA_NOTIFICACION_EVENTO_ID + " = ?";
        String[] selectionArgs = { String.valueOf(usuario_id), String.valueOf(evento_id) };

        return db.delete(TABLA_NOTIFICACION, selection, selectionArgs);
    }
}
