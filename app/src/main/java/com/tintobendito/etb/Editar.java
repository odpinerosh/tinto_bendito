package com.tintobendito.etb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

public class Editar extends AppCompatActivity {

    private EditText et_Nombres, et_Apellidos, et_Correo, et_Movil;
    private String strNombres = "",  strApellidos = "", strCorreo = "", strMovil ="";

    private Button b_guardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

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

        et_Nombres.setText(strNombres);
        et_Apellidos.setText(strApellidos);
        et_Correo.setText(strCorreo);
        et_Movil.setText(strMovil);

        b_guardar = findViewById(R.id.b_guardar);

        b_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Snackbar.make(view, getResources().getString(R.string.app_name), Snackbar.LENGTH_LONG)
                        .setAction("Toque para guardar los cambios", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(Editar.this, Inicio.class);
                                //Cargar la intención con la información de las variables.
                                intent.putExtra("nombres", et_Nombres.getText().toString());
                                intent.putExtra("apellidos", et_Apellidos.getText().toString());
                                intent.putExtra("correo", et_Correo.getText().toString());
                                intent.putExtra("movil", et_Movil.getText().toString());

                                startActivity(intent);
                            }
                        })
                        .setActionTextColor(ContextCompat.getColor(Editar.this, R.color.button_face))
                        .show();

            }
        });
    }
}