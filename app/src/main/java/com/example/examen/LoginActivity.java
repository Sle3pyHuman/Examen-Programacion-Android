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

public class LoginActivity extends AppCompatActivity {

    private EditText eTCorreo, eTContraseña;
    private Button btnLogin;
    private TextView registrarCuenta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        eTCorreo = findViewById(R.id.eTCorreo);
        eTContraseña = findViewById(R.id.eTContraseña);

        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUsuario();
            }
        });

        registrarCuenta = findViewById(R.id.txtNoCuenta);
        registrarCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegistroActivity.class);
                startActivity(intent);
            }
        });
    }
    private void loginUsuario() {
        String correo = eTCorreo.getText().toString().trim();
        String contraseña = eTContraseña.getText().toString().trim();

        if (TextUtils.isEmpty(correo) || TextUtils.isEmpty(contraseña)) {
            Toast.makeText(this,"Rellena todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }
        SharedPreferences conpartirPref = getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE);
        String storedPassword = conpartirPref.getString(correo, null);

        if (storedPassword != null && storedPassword.equals(contraseña)) {
            Toast.makeText(this, "Inicio de sesión con éxito", Toast.LENGTH_SHORT).show();
            // Navigate to HomeActivity
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Nombre de usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
        }
    }
}