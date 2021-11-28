package com.tintobendito.etb.vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tintobendito.etb.R;
import com.tintobendito.etb.utiles.ValidarCorreo;

public class Registro extends AppCompatActivity {

    private EditText et_NombresRegis, et_ApellidosRegis, et_MovilRegis, et_CorreoRegis, et_PwdRegis, et_PwdConfirmRegis;
    private Button b_Registrar;
    private String stringHabilitar;
    private View view;

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
        view = findViewById(R.id.cl_Registro);

        et_NombresRegis.addTextChangedListener(textWatcher);
        et_ApellidosRegis.addTextChangedListener(textWatcher);
        et_MovilRegis.addTextChangedListener(textWatcher);
        et_CorreoRegis.addTextChangedListener(textWatcher);
        et_PwdRegis.addTextChangedListener(textWatcher);
        et_PwdConfirmRegis.addTextChangedListener(textWatcher);

        b_Registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                stringHabilitar = habilitar();
                if( stringHabilitar == "") {
                    RegistroControl.registrar(Registro.this, get_NombresRegis(), get_ApellidosRegis(), get_CorreoRegis(), get_MovilRegis(), get_PwdRegis() );
                    //Toast.makeText(Registro.this, "A registro y control", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Registro.this, stringHabilitar, Toast.LENGTH_SHORT).show();
                }


            }
        });

    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private String habilitar() {

        String nombre = get_NombresRegis().trim();
        String apellido = get_ApellidosRegis().trim();
        String movil = get_MovilRegis().trim();
        String correo = get_CorreoRegis().trim();
        String passwd = get_PwdRegis().trim();
        String confirmPasswd = get_PwdConfirmRegis().trim();

        if(nombre.length() <= 2) {
            return "El nombre es un dato requerido.";
        }

        if(apellido.length() <= 2) {
            return "El apellido es un dato requerido.";
        }

        if(!ValidarCorreo.validar(correo)) {
            return "El correo no es válido.";
        }

        if(passwd.length() < 8) {
            return "La contraseña debe contener mínimo 8 caracteres.";
        }

        if( !confirmPasswd.equals(passwd) )  {
            return "Las contraseñas no son iguales.";

        }

        return "";

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

    //Para ocultar el teclado haciendo clic en cualquier parte de la pantalla
    //y quitar el foco. Se usa con el view definido arriba como
    //private View view;
    //Y asignando un id al ConstraintLayout, que se asigna al view.

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        ocultarTeclado(this, view);
        et_NombresRegis.clearFocus();
        et_ApellidosRegis.clearFocus();
        et_MovilRegis.clearFocus();
        et_CorreoRegis.clearFocus();
        et_PwdRegis.clearFocus();
        et_PwdConfirmRegis.clearFocus();

        return true;
    }

    private void ocultarTeclado(Context contexto, View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) contexto.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }
}