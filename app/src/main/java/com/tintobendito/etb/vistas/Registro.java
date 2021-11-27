package com.tintobendito.etb.vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.tintobendito.etb.R;

public class Registro extends AppCompatActivity {

    private EditText et_NombresRegis, et_ApellidosRegis, et_MovilRegis, et_CorreoRegis, et_PwdRegis, et_PwdConfirmRegis;
    private Button b_Registrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        et_NombresRegis = findViewById(R.id.et_NombresRegis);
        et_ApellidosRegis = findViewById(R.id.et_ApellidosRegis);
        et_MovilRegis = findViewById(R.id.et_MovilRegis);
        et_CorreoRegis = findViewById(R.id.et_CorreoRegis);
        et_PwdRegis = findViewById(R.id.et_PwdRegis);
        et_PwdConfirmRegis = findViewById(R.id.et_PwdConfirmRegis);
        b_Registrar = findViewById(R.id.b_Registrar);

        b_Registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegistroControl.registrar(Registro.this, get_NombresRegis(), get_ApellidosRegis(), get_CorreoRegis(), get_MovilRegis(), get_PwdRegis() );
            }
        });

    }

    public String get_NombresRegis() {
        return et_NombresRegis.getText().toString();
    }

    public String get_ApellidosRegis() {
        return et_ApellidosRegis.getText().toString();
    }

    public String get_MovilRegis() {
        return et_MovilRegis.getText().toString();
    }

    public String get_CorreoRegis() {
        return et_CorreoRegis.getText().toString();
    }

    public String get_PwdRegis() {
        return et_PwdRegis.getText().toString();
    }

    public String get_PwdConfirmRegis() {
        return et_PwdConfirmRegis.getText().toString();
    }
}