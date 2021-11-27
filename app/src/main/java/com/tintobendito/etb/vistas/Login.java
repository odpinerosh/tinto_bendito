package com.tintobendito.etb.vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.tintobendito.etb.R;
import com.tintobendito.etb.controladores.LoginControl;

public class Login extends AppCompatActivity {


    private Button b_Registrar, b_IrARegistro;
    private EditText et_NombreUsuario, et_PasswdUsuario;
    private TextView tv_recuperarPwd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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
                LoginControl.login(Login.this, getEt_NombreUsuario(), getEt_PasswdUsuario());

            }
        });

        b_IrARegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Registro.class));
            }
        });
    }

    public String getEt_NombreUsuario() {
        return et_NombreUsuario.getText().toString();
    }

    public String getEt_PasswdUsuario() {
        return et_PasswdUsuario.getText().toString();
    }
}