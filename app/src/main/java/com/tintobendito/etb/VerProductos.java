package com.tintobendito.etb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class VerProductos extends AppCompatActivity {

    private Button b_hacerPedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_productos);

        b_hacerPedido = findViewById(R.id.b_hacerPedido);

        b_hacerPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VerProductos.this, ConfirmarPedido.class);
                startActivity(intent);
            }
        });
    }
}