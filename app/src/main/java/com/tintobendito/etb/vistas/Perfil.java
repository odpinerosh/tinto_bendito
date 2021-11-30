package com.tintobendito.etb.vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.tintobendito.etb.R;
import com.tintobendito.etb.vistas.fragments.PerfilFragment;

public class Perfil extends AppCompatActivity {

    private MaterialToolbar topAppBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        topAppBar = findViewById(R.id.tb_perfil);
        topAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fl_perfil, new PerfilFragment(), PerfilFragment.class.getSimpleName())
                .commit();
    }
}