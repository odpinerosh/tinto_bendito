package com.tintobendito.etb.vistas.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.tintobendito.etb.R;
import com.tintobendito.etb.controladores.PerfilControlador;
import com.tintobendito.etb.modelos.Usuario;
import com.tintobendito.etb.utiles.ConstantesFirebase;

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
                    PerfilControlador.actualizar(getActivity(), "firstNameUser", getNombre());
                } else {
                    Toast.makeText(getActivity(), "Los campos no pueden estar vacíos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        obtenerInfo();
        return view;
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

    private EventListener<DocumentSnapshot> informacionUsuario = new EventListener<DocumentSnapshot>() {
        @Override
        public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
            try {
                Usuario usuario = value.toObject(Usuario.class);
                if (usuario != null) {
                    String firstNameUser = usuario.getFirstNameUser();
                    String lastNameUser = usuario.getLastNameUser();
                    String mobileUser = usuario.getMobileUser();
                    String mailuser = usuario.getMailUser();

                    et_NombPerfilFrag.setText(firstNameUser);
                    et_ApePerfilFrag.setText(lastNameUser);
                    et_MovPerfilFrag.setText(mobileUser);
                    et_MailPerfilFrag.setText(mailuser);

                }
            } catch (NullPointerException | IllegalStateException e) {
                e.getCause();
            }
        }
    };

    //Destuir el Fragment
    @Override
    public void onDetach() {
        super.onDetach();
        if(listenerRegistration != null) {
            listenerRegistration.remove();
            listenerRegistration = null;
        }
    }

    public String getNombre() {
        return et_NombPerfilFrag.getText().toString();
    }
}