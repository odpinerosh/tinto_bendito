package com.tintobendito.etb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.tintobendito.etb.vistas.Login;

public class MainActivity extends AppCompatActivity {

    private Button b_login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*Obtener el Botòón*/
        b_login = findViewById(R.id.b_login);

        /*Agregar el listener para el evento clic*/
        b_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Se hace una intención*/
                //Intent intent = new Intent(ClaseOrigen, ClaseDestino);
                Intent intent = new Intent(MainActivity.this, Login.class);
                //Actividad que se inicia
                startActivity(intent);

            }
        });

    }
}