package com.tintobendito.etb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        //Revisar si se tiene un Usuario en FireBase
        FirebaseUser usuario = FirebaseAuth.getInstance().getCurrentUser();

        if (usuario == null) {
            iniciarNuevaActividad(Login.class);
        } else {
            iniciarNuevaActividad(VerProductos_RecV.class);
        }


    }

    private void iniciarNuevaActividad(Class clase) {
        startActivity(new Intent(this, clase));
        //Finalizar este Splash
        finish();
    }
}