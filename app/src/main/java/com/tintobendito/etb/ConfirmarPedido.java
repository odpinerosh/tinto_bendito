package com.tintobendito.etb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.tintobendito.etb.vistas.Login;

public class ConfirmarPedido extends AppCompatActivity {

    private Button b_confirmarPedido, b_cancelarPedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_pedido);

        b_cancelarPedido = findViewById(R.id.b_cancelarPedido);
        b_confirmarPedido = findViewById(R.id.b_confirmarPedido);

        b_cancelarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConfirmarPedido.this, Login.class);
                startActivity(intent);
            }
        });

        b_confirmarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConfirmarPedido.this, PedidoConfirmado.class);
                startActivity(intent);
            }
        });

    }
}