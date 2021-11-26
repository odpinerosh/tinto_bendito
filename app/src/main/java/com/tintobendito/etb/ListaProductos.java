package com.tintobendito.etb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class ListaProductos extends AppCompatActivity {

    private SwipeRefreshLayout srl_listaProductos;
    private ListView lv_listaProductos;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_productos);

        srl_listaProductos = findViewById(R.id.srl_listaProductos);
        lv_listaProductos = findViewById(R.id.lv_listaProductos);

    }


}