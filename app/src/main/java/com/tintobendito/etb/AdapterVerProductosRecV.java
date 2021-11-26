package com.tintobendito.etb;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterVerProductosRecV extends RecyclerView.Adapter<AdapterVerProductosRecV.ProductosViewHolder> {

    //Aquí se define el contexto en el cual trabaja esta clase.
    private Activity contextoAct;

    //Este es el arreglo que tendrá los Pojos. Cada producto es un Pojo.
    private ArrayList<PojoProducto> pojoProductoArrayList;

    //Constructor
    public AdapterVerProductosRecV(Activity contextoAct, ArrayList<PojoProducto> pojoProductoArrayList) {
        this.contextoAct = contextoAct;
        this.pojoProductoArrayList = pojoProductoArrayList;
    }

    //Sincroniza el view holder con el recycler view
    @NonNull
    @Override
    public ProductosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //Crear una vista donde se "infle" (se muestre) el CardView dentro del RecyclerView
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_productos_recv, parent, false);

        //Crear una instancia del ViewHolder
        ProductosViewHolder productosViewHolder = new ProductosViewHolder(vista);

        return productosViewHolder;
    }

    //Pone los datos en el Pojo
    @Override
    public void onBindViewHolder(@NonNull ProductosViewHolder holder, int position) {

        //Usar los datos en el Pojo
        //Esto es como un ciclo
        final PojoProducto pojoProducto = pojoProductoArrayList.get(position);

        Picasso.get()
                .load(pojoProducto.getFoto())
                .placeholder(R.drawable.tintobendito)
                .into(holder.iv_fotoProducto);

        holder.iv_fotoProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(contextoAct, "DETALLE DEL PRODUCTO", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(contextoAct, DetalleProducto.class);
                intent.putExtra("nombreProducto", pojoProducto.getNomProducto());
                intent.putExtra("precioProducto", pojoProducto.getPrecProducto());
                intent.putExtra("fotoProducto", pojoProducto.getFoto());
                intent.putExtra("descProducto", pojoProducto.getDescProducto());
                //Log.println(Log.VERBOSE, String.valueOf(pojoProducto.getFoto()), "Piojo Producto.getFoto()");
                contextoAct.startActivity(intent);
            }
        });

        holder.tv_nombreProducto.setText(pojoProducto.getNomProducto());
        holder.tv_precioProducto.setText(pojoProducto.getPrecProducto());
    }

    //Devolver la cantidad de vistas
    @Override
    public int getItemCount() {
        return pojoProductoArrayList.size();
    }

    //Tomar el control de cada uno de los elementos del CardView
    public class ProductosViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv_fotoProducto;
        private TextView tv_nombreProducto, tv_precioProducto;

        public ProductosViewHolder(@NonNull View itemView) {
            super(itemView);

            iv_fotoProducto = itemView.findViewById(R.id.iv_fotoProducto);
            tv_nombreProducto = itemView.findViewById(R.id.tv_nombreProducto);
            tv_precioProducto = itemView.findViewById(R.id.tv_precioProducto);
        }
    }
}
