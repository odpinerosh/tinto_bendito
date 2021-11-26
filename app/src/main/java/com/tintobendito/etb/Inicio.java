package com.tintobendito.etb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Inicio extends AppCompatActivity {

    private EditText et_Nombres, et_Apellidos, et_Correo, et_Movil;
    private String strNombres = "",  strApellidos = "", strCorreo = "", strMovil ="";
    private Button b_editar_inicio, b_Pedidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        Bundle bundle = getIntent().getExtras();

        if(bundle != null){
            strNombres = bundle.getString("nombres");
            strApellidos = bundle.getString("apellidos");
            strCorreo = bundle.getString("correo");
            strMovil = bundle.getString("movil");
        }

        et_Nombres = findViewById(R.id.et_Nombres);
        et_Apellidos = findViewById(R.id.et_Apellidos);
        et_Correo = findViewById(R.id.et_Correo);
        et_Movil = findViewById(R.id.et_Movil);
        b_editar_inicio = findViewById(R.id.b_editar_inicio);
        b_Pedidos = findViewById(R.id.b_Pedidos);

        et_Nombres.setText(strNombres);
        et_Apellidos.setText(strApellidos);
        et_Correo.setText(strCorreo);
        et_Movil.setText(strMovil);

        b_Pedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Inicio.this, VerProductos_RecV.class);
                startActivity(intent);
            }
        });

        b_editar_inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(Inicio.this, Editar.class);
                //Cargar la intención con la información de las variables.
                intent.putExtra("nombres", et_Nombres.getText().toString());
                intent.putExtra("apellidos", et_Apellidos.getText().toString());
                intent.putExtra("correo", et_Correo.getText().toString());
                intent.putExtra("movil", et_Movil.getText().toString());
                startActivity(intent);

            }
        });

    }
}