package com.tintobendito.etb.vistas.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.squareup.picasso.Picasso;
import com.tintobendito.etb.R;
import com.tintobendito.etb.controladores.PerfilControlador;
import com.tintobendito.etb.modelos.Usuario;
import com.tintobendito.etb.utiles.ConstantesFirebase;

import java.util.Locale;

public class PerfilFragment extends Fragment {

    View view;
    private ImageView iv_FotoPerfilFrag;
    private EditText et_NombPerfilFrag, et_ApePerfilFrag, et_MovPerfilFrag, et_MailPerfilFrag;
    private Button b_EditPerfilFrag;
    private ListenerRegistration listenerRegistration;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_perfil, container, false);

        iv_FotoPerfilFrag = view.findViewById(R.id.iv_FotoPerfilFrag);
        et_NombPerfilFrag = view.findViewById(R.id.et_NombPerfilFrag);
        et_ApePerfilFrag = view.findViewById(R.id.et_ApePerfilFrag);
        et_MovPerfilFrag = view.findViewById(R.id.et_MovPerfilFrag);
        et_MailPerfilFrag = view.findViewById(R.id.et_MailPerfilFrag);
        b_EditPerfilFrag = view.findViewById(R.id.b_EditPerfilFrag);

        b_EditPerfilFrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getNombre().trim().length() > 2) {
                    PerfilControlador.actualizarDatos(getActivity(), "firstNameUser", getNombre());
                } else {
                    Toast.makeText(getActivity(), "El nombre no puede estar vacío.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(getApellido().trim().length() > 2) {
                    PerfilControlador.actualizarDatos(getActivity(), "lastNameUser", getApellido());
                } else {
                    Toast.makeText(getActivity(), "El apellido no puede estar vacío.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(getMovil().trim().length() > 2) {
                    PerfilControlador.actualizarDatos(getActivity(), "mobileUser", getMovil());
                } else {
                    Toast.makeText(getActivity(), "El número móvil no puede estar vacío.", Toast.LENGTH_SHORT).show();
                    return;
                }

            }
        });

        iv_FotoPerfilFrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(getActivity(), v);
                popupMenu.getMenuInflater().inflate(R.menu.popup_img_perfil, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()) {
                            case R.id.galeria:
                                obtenerImagen.launch("image/*");
                                break;

                            case R.id.camara:
                                break;

                            case R.id.eliminar:
                                break;
                        }

                        return false;
                    }
                });
                popupMenu.show();
            }
        });

        obtenerInfo();
        return view;
    }
    //Este escuchador se está actualizando siempre tomando capturas.
    private EventListener<DocumentSnapshot> informacionUsuario = new EventListener<DocumentSnapshot>() {
        @Override
        public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
            try {
                Usuario usuario = value.toObject(Usuario.class);
                if (usuario != null) {
                    String imgUser = usuario.getImgUser();
                    String firstNameUser = usuario.getFirstNameUser();
                    String lastNameUser = usuario.getLastNameUser();
                    String mobileUser = usuario.getMobileUser();
                    String mailuser = usuario.getMailUser();

                    et_NombPerfilFrag.setText(firstNameUser);
                    et_ApePerfilFrag.setText(lastNameUser);
                    et_MovPerfilFrag.setText(mobileUser);
                    et_MailPerfilFrag.setText(mailuser);

                    if (imgUser != null && imgUser.length() >0) {

                        Picasso.get()
                                .load(imgUser)
                                .placeholder(R.drawable.ic_arrow_right)
                                .error(R.drawable.cafecamin)
                                .into(iv_FotoPerfilFrag);
                    } else {
                        iv_FotoPerfilFrag.setImageResource(R.mipmap.ic_launcher_round);
                    }

                }
            } catch (NullPointerException | IllegalStateException e) {
                e.getCause();
            }
        }
    };

    //Destruir el Fragment
    @Override
    public void onDetach() {
        super.onDetach();
        if(listenerRegistration != null) {
            listenerRegistration.remove();
            listenerRegistration = null;
        }
    }

    private void obtenerInfo() {

        //Obtener el usuario que está conectado
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        //Obtener el ID o documento que contiene toda la información de USERS
        DocumentReference documentReference = FirebaseFirestore.getInstance()
                .collection(ConstantesFirebase.USUARIO)
                .document(firebaseUser.getUid());

        //Escuchador
        listenerRegistration = documentReference.addSnapshotListener(informacionUsuario);

    }

    public String getNombre() {
        return et_NombPerfilFrag.getText().toString();
    }
    public String getApellido() {
        return et_ApePerfilFrag.getText().toString();
    }
    public String getMovil() {
        return et_MovPerfilFrag.getText().toString();
    }

    ActivityResultLauncher<String> obtenerImagen = registerForActivityResult(new ActivityResultContracts.GetContent(),
            new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri result) {
                    Log.i("obtenerImagen", result.toString());
                    PerfilControlador.actualizarImagen(getActivity(), result);
                }
            });


}