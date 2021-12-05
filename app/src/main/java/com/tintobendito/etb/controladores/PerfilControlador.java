package com.tintobendito.etb.controladores;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.tintobendito.etb.utiles.ConstantesFirebase;

import java.util.HashMap;

public class PerfilControlador {
    public static void actualizarDatos(FragmentActivity activity, String key, String valor) {

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

    public static void actualizarImagen(Context context, Uri result) {
        //Llamar al usuario que está logeado
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        //Obtener la referencia del almacen de datos
        StorageReference archivoPath = FirebaseStorage.getInstance().getReference()
                .child(ConstantesFirebase.USUARIO_IMG_PERFIL).child(firebaseUser.getUid() + ".jpg");
        //Log.i("USUARIO_IMG", archivoPath.toString());
        //Guardar el archivo que llega por parámetro
        archivoPath.putFile(result)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        archivoPath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                actualizarImgDB(context, uri.toString(), firebaseUser.getUid());
                            }
                        });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                        double progreso = 100.0 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount();
                        Toast.makeText(context, "Subiendo imagen: " + (int) progreso + "%", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private static void actualizarImgDB(Context contexto, String urlImagen, String uid) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("imgUser", urlImagen);

        FirebaseFirestore.getInstance()
                .collection(ConstantesFirebase.USUARIO)
                .document(uid)
                .update(map)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(contexto, "Se actualizó la imagen del perfil.", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(contexto, "Error al intentar guardar la imagen.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
