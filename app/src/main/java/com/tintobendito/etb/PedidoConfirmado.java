package com.tintobendito.etb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.tintobendito.etb.vistas.Login;

public class PedidoConfirmado extends AppCompatActivity {

    private Button b_ALogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido_confirmado);

        b_ALogin = findViewById(R.id.b_ALogin);

        b_ALogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PedidoConfirmado.this, Login.class);
                startActivity(intent);
            }
        });

    }
}