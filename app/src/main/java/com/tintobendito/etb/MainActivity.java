package com.tintobendito.etb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.auth.FirebaseAuth;
import com.tintobendito.etb.vistas.Login;
import com.tintobendito.etb.vistas.Perfil;
import com.tintobendito.etb.vistas.RecuperarPWD;

public class MainActivity extends AppCompatActivity {

    private Button b_login;
    private MaterialToolbar mainTopAppBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainTopAppBar = findViewById(R.id.tb_main);

        /*Obtener el Botón*/
        b_login = findViewById(R.id.b_login);

        /*Agregar el listener para el evento clic*/
        b_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Se hace una intención*/
                //Intent intent = new Intent(ClaseOrigen, ClaseDestino);
                Intent intent = new Intent(MainActivity.this, VerProductos_RecV.class);
                //Actividad que se inicia
                startActivity(intent);

            }
        });

        mainTopAppBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.perfil: {
                        startActivity(new Intent(MainActivity.this, Perfil.class));
                        return true;
                    }

                    case R.id.ajustes: {
                        Toast.makeText(MainActivity.this,"Caracterìstica en desarrollo", Toast.LENGTH_SHORT).show();
                        return true;
                    }

                    case R.id.cerrar: {
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(MainActivity.this, Login.class));
                        finish();
                        return true;
                    }

                    default:
                        return false;
                }
             }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.opciones, menu);
        return true;
    }

/*    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        Intent intent;

        switch (item.getItemId()) {
            case R.id.perfil: {
                startActivity(new Intent(MainActivity.this, Perfil.class));
                return true;
            }

            case R.id.ajustes: {
                Toast.makeText(this,"Caracterìstica en desarrollo", Toast.LENGTH_SHORT).show();
                return true;
            }

            default:
                return super.onOptionsItemSelected(item);
        }

    }
*/

}