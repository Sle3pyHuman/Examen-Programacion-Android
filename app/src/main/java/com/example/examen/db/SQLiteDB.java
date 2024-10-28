package com.example.examen.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteData extends SQLiteOpenHelper {

    // Nombre y versión de la base de datos
    private static final String DATABASE_NAME = "ProgramaYa.db";
    private static final int DATABASE_VERSION = 1;

    // Tabla Usuario
    private static final String TABLA_USUARIOS = "Usuarios";
    private static final String COLUMNA_USUARIO_ID = "id";
    private static final String COLUMNA_USUARIO_NOMBRE = "nombre";
    private static final String COLUMNA_USUARIO_EMAIL = "email";
    private static final String COLUMNA_USUARIO_CONTRASEÑA = "contraseña";
    private static final String COLUMNA_USUARIO_TIPO_CUENTA = "rol";

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
    public SQLiteData(Context context) {
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
                COLUMNA_USUARIO_TIPO_CUENTA + " TEXT " +
                ");";
        db.execSQL(sqlCrearTablaUsuarios);

        // Crear tabla Evento
        String sqlCrearTablaEvetos = "CREATE TABLE " + TABLA_USUARIOS + " (" +
                COLUMNA_EVENTO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMNA_EVENTO_NOMBRE + " TEXT, " +
                COLUMNA_EVENTO_DESCRIPCION + " TEXT, " +
                COLUMNA_EVENTO_TIPO + " TEXT, " +
                COLUMNA_EVENTO_FECHA + " TEXT, " +
                COLUMNA_EVENTO_HORA_INICIO + " TEXT, " +
                COLUMNA_EVENTO_LUGAR_ID + " TEXT " +
                ");";
        db.execSQL(sqlCrearTablaEvetos);

        // Crear tabla Lugar

        // Crear tabla NotificarEvento

    }

    // Método para actualizar la base de datos (cuando se cambia la versión de la base de datos)
    @Override
    public void onUpgrade(android.database.sqlite.SQLiteDatabase db, int i, int i1) {
        // Eliminar tablas si existen y volver a crearlas
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_USUARIOS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_EVENTOS);
        onCreate(db);
    }

    // Método para insertar un usuario en la base de datos
    public long insertarUsuario(String nombre, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMNA_USUARIO_NOMBRE, nombre);
        values.put(COLUMNA_USUARIO_EMAIL, email);
        return db.insert(TABLA_USUARIOS, null, values);
    }

    // Método para actualizar un usuario
    public int actualizarUsuario(int id, String nombre, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMNA_USUARIO_NOMBRE, nombre);
        values.put(COLUMNA_USUARIO_EMAIL, email);

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
}
