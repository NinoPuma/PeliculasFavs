package com.example.peliculasfavs;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    public EditText txtAgregar, txtCargar, txtNuevoDato;
    public Button btnAgregar, btnCargar;
    public SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txtNuevoDato = findViewById(R.id.txtNuevoDato);
        txtAgregar = findViewById(R.id.txtAgregar);
        txtCargar = findViewById(R.id.txtCargar);
        btnAgregar = findViewById(R.id.btnAgregar);
        btnCargar = findViewById(R.id.btnCargar);

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fileName = txtNuevoDato.getText().toString();
                sharedPreferences = getSharedPreferences(fileName, MODE_PRIVATE);
                String key = txtAgregar.getText().toString();
                String value = txtCargar.getText().toString();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(key, value);
                editor.apply();
            }
        });

        btnCargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fileName = txtNuevoDato.getText().toString();
                sharedPreferences = getSharedPreferences(fileName, MODE_PRIVATE);
                String key = txtAgregar.getText().toString();
                String value = sharedPreferences.getString(key, "las dos torres");
                if (value.equals("I am Error")) {
                    Toast.makeText(MainActivity.this, "Error: Película No Encontrada", Toast.LENGTH_SHORT).show();
                }
                txtCargar.setText(value);
            }
        });
    }
}