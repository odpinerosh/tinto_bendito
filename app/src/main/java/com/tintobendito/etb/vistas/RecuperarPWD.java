package com.tintobendito.etb.vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tintobendito.etb.R;

public class RecuperarPWD extends AppCompatActivity {

    private EditText et_CorreoRecuPWD;
    private Button b_EnviarCorreo;
    private String stringHabilitar;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_pwd);

        b_EnviarCorreo = findViewById(R.id.b_EnviarCorreo);
        et_CorreoRecuPWD = findViewById(R.id.et_CorreoRecuPWD);
        view = findViewById(R.id.cl_RecuperarPWD);

        b_EnviarCorreo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                stringHabilitar = habilitar();
                if( stringHabilitar == "") {
                    Toast.makeText(RecuperarPWD.this, "A Recuperar & Control", Toast.LENGTH_SHORT).show();
                    //RecuperarPWDControl.recuperar(RecuperarPWD.this, get_CorreoRecuPWD());
                } else {
                    Toast.makeText(RecuperarPWD.this, stringHabilitar, Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

    private String habilitar() {

        String nombre = get_CorreoRecuPWD().trim();

        if(nombre.length() <= 2) {
            return "Escriba su correo electrónico.";
        }

        return "";

    }

    public String get_CorreoRecuPWD() {
        return et_CorreoRecuPWD.getText().toString();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        ocultarTeclado(this, view);
        et_CorreoRecuPWD.clearFocus();

        return true;
    }

    private void ocultarTeclado(Context contexto, View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) contexto.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }
}