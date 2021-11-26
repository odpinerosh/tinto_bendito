package com.tintobendito.etb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DetalleProducto extends AppCompatActivity {

    //Definir las variables
    private TextView tv_detNombreproducto, tv_detPrecioProducto, tv_detDescProducto;
    private ImageView iv_detImagenProducto;
    private Button bt_DetalleProducto;
    private String str_DetNombreProducto, str_DetPrecioProducto, str_DetDescProducto;
    private int int_DetFotoProducto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_producto);

        //Tomar los controles del activity y guardarlos en variables
        tv_detPrecioProducto = findViewById(R.id.tv_detPrecioProducto);
        tv_detNombreproducto = findViewById(R.id.tv_detNombreProducto);
        tv_detDescProducto = findViewById(R.id.tv_detDescProducto);
        iv_detImagenProducto = findViewById(R.id.iv_detFotoProducto);
        bt_DetalleProducto = findViewById(R.id.btn_detProducto);

        //Obtener los parámetros que vienen del Adaptador
        Bundle bundle = getIntent().getExtras();
        str_DetNombreProducto = bundle.getString("nombreProducto");
        str_DetPrecioProducto = bundle.getString("precioProducto");
        str_DetDescProducto = bundle.getString("descProducto");
        int_DetFotoProducto = bundle.getInt("fotoProducto");

        //Asignar los valores a los controles
        tv_detNombreproducto.setText(str_DetNombreProducto);
        tv_detPrecioProducto.setText(str_DetPrecioProducto);
        tv_detDescProducto.setText(str_DetDescProducto);
        iv_detImagenProducto.setImageResource(int_DetFotoProducto);

        bt_DetalleProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetalleProducto.this, VerProductos_RecV.class);
                startActivity(intent);
            }
        });
    }
}