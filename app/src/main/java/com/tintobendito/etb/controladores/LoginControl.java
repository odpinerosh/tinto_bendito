package com.tintobendito.etb.controladores;

import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.tintobendito.etb.MainActivity;
import com.tintobendito.etb.vistas.Login;

public class LoginControl {
    public static void login(Login login, String et_nombreUsuario, String et_passwdUsuario) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(et_nombreUsuario, et_passwdUsuario)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            login.startActivity(new Intent(login, MainActivity.class));
                            login.finish();
                        } else {
                            Toast.makeText(login, "Error al intentar iniciar sesión.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
