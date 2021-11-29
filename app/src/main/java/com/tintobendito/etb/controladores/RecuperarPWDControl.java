package com.tintobendito.etb.controladores;

import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.tintobendito.etb.MainActivity;
import com.tintobendito.etb.vistas.RecuperarPWD;

public class RecuperarPWDControl {
    public static void recuperar(RecuperarPWD recuperarPWD, String nombreUsuario) {
        FirebaseAuth.getInstance().sendPasswordResetEmail(nombreUsuario)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if(task.isSuccessful()) {
                            recuperarPWD.finish();
                            Toast.makeText(recuperarPWD,
                                    "Se ha enviado un correo a su cuenta " + nombreUsuario,
                                    Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(recuperarPWD,
                                    "No se encuentra el correo " + nombreUsuario + " en la base de datos.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
