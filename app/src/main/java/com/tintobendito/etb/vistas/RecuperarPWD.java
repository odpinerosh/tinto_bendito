package com.tintobendito.etb.vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.tintobendito.etb.R;

public class RecuperarPWD extends AppCompatActivity {

    private EditText et_NombreUsuario;
    private Button b_EnviarCorreo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_pwd);

        b_EnviarCorreo = findViewById(R.id.b_EnviarCorreo);
        et_NombreUsuario = findViewById(R.id.et_NombreUsuario);

        b_EnviarCorreo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecuperarPWDControl.recuperar(RecuperarPWD.this, get_NombreUsuario());
            }
        });
    }

    public String get_NombreUsuario() {
        return et_NombreUsuario.getText().toString();
    }
}