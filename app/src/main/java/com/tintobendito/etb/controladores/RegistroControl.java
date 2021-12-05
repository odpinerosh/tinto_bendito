package com.tintobendito.etb.controladores;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.tintobendito.etb.MainActivity;
import com.tintobendito.etb.modelos.Usuario;
import com.tintobendito.etb.utiles.ConstantesFirebase;

public class RegistroControl {
    public static void registrar(Context contexto, String nombresRegis, String apellidosRegis, String correoRegis, String movilRegis, String pwdRegis) {

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(correoRegis, pwdRegis)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            usuarioFirestore(contexto, nombresRegis, apellidosRegis, correoRegis, movilRegis, pwdRegis);
                        } else{
                            Toast.makeText(contexto,"Error al intentar registrar el usuario", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private static void usuarioFirestore(Context contexto, String nombresRegis, String apellidosRegis, String correoRegis, String movilRegis, String pwdRegis) {
        try {
            FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
            String id = firebaseUser.getUid();
            long timeStamp = firebaseUser.getMetadata().getCreationTimestamp();
            Usuario usuario = new Usuario(id, nombresRegis, apellidosRegis, movilRegis, correoRegis, "", timeStamp);
            FirebaseFirestore.getInstance().collection(ConstantesFirebase.USUARIO)
                    .document(id)
                    .set(usuario, SetOptions.merge())
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()) {
                                Intent intent = new Intent(contexto, MainActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                contexto.startActivity(intent);
                            } else {
                                Toast.makeText(contexto,"Error al intentar guardar los datos del usuario", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        } catch (Exception e) {
            e.getCause();
        }
    }
}
