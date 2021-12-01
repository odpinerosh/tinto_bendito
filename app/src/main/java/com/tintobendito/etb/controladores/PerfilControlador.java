package com.tintobendito.etb.controladores;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.tintobendito.etb.utiles.ConstantesFirebase;

import java.util.HashMap;

public class PerfilControlador {
    public static void actualizar(FragmentActivity activity, String key, String valor) {

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        HashMap<String, Object> map = new HashMap<>();
        map.put(key, valor);

        FirebaseFirestore.getInstance()
                .collection(ConstantesFirebase.USUARIO)
                .document(firebaseUser.getUid())
                .set(map, SetOptions.merge())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(activity, "Datos Actualizados", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(activity, "Error al actualizar los datos.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
