package com.example.examen;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RegistroActivity extends AppCompatActivity {

    private EditText eTUsuario, eTCorreo, etContraseña;
    private Button btnRegistrar;
    private TextView irALogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registro);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        eTUsuario = findViewById(R.id.eTNombre);
        eTCorreo = findViewById(R.id.eTCorreo);
        etContraseña = findViewById(R.id.eTContraseña);
        
        btnRegistrar = findViewById(R.id.btnRegistrar);
        btnRegistrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                registrarUsuario();
            }
        });

        irALogin = findViewById(R.id.txtNoCuenta);
        irALogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistroActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }
    
    private void registrarUsuario() {
        String usuario = eTUsuario.getText().toString().trim();
        String correo = eTCorreo.getText().toString().trim();
        String contraseña = etContraseña.getText().toString().trim();
        
        if (TextUtils.isEmpty(usuario) || TextUtils.isEmpty(correo) || TextUtils.isEmpty(contraseña)) {
            Toast.makeText(this, "Rellene todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        SharedPreferences compartirPref = getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = compartirPref.edit();
        editor.putString(correo, contraseña);
        editor.apply();

        Toast.makeText(this,"Registro realizado con exito", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(RegistroActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}