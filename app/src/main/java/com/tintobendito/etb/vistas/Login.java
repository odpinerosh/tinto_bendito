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
import android.widget.TextView;
import android.widget.Toast;

import com.tintobendito.etb.R;
import com.tintobendito.etb.controladores.LoginControl;
import com.tintobendito.etb.utiles.ValidarCorreo;

public class Login extends AppCompatActivity {


    private Button b_Registrar, b_IrARegistro;
    private EditText et_NombreUsuario, et_PasswdUsuario;
    private TextView tv_recuperarPwd;
    private String stringHabilitar;
    private View view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        view = findViewById(R.id.cl_Login);
        b_Registrar = findViewById(R.id.b_Registrar);
        b_IrARegistro = findViewById(R.id.b_irARegistro);
        et_NombreUsuario = findViewById(R.id.et_NombreUsuario);
        et_PasswdUsuario = findViewById(R.id.et_PasswdUsuario);
        tv_recuperarPwd = findViewById(R.id.tv_recuperarPwd);

        tv_recuperarPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, RecuperarPWD.class));
            }
        });

        b_Registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                stringHabilitar = habilitar();
                if( stringHabilitar == "") {
                    Toast.makeText(Login.this, "A Login & Control", Toast.LENGTH_SHORT).show();
                    //LoginControl.login(Login.this, get_NombreUsuario(), get_PasswdUsuario());
                } else {
                    Toast.makeText(Login.this, stringHabilitar, Toast.LENGTH_SHORT).show();
                }


            }
        });

        b_IrARegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Registro.class));
            }
        });
    }

    private String habilitar() {

        String nombre = get_NombreUsuario().trim();
        String passwd = get_PasswdUsuario().trim();


        if(nombre.length() <= 2) {
            return "Se requiere el correo para iniciar sesión.";
        }

        if(passwd.length() < 8) {
            return "Escriba su contraseña.";
        }

        return "";

    }

    public String get_NombreUsuario() {
        return et_NombreUsuario.getText().toString();
    }

    public String get_PasswdUsuario() {
        return et_PasswdUsuario.getText().toString();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        ocultarTeclado(this, view);
        et_NombreUsuario.clearFocus();
        et_PasswdUsuario.clearFocus();
        return true;
    }

    private void ocultarTeclado(Context contexto, View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) contexto.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }
}