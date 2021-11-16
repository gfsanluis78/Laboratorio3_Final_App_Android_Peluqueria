package com.farias.laboratorio3_final_app_android_peluqueria.ui.c_cliente.adapter;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.farias.laboratorio3_final_app_android_peluqueria.R;
import com.farias.laboratorio3_final_app_android_peluqueria.modelo.Cliente;
import com.farias.laboratorio3_final_app_android_peluqueria.modelo.Preparacion;
import com.farias.laboratorio3_final_app_android_peluqueria.ui.a_home.adapter.RecyclerAdapterHomeClientes;

import java.util.ArrayList;

/**
 * Created by Genaro Farias el 16/11/2021.
 * Estudiante de la ULP
 * gfsanluis78@gmail.com
 */

public class RecyclerAdapterClientes extends RecyclerView.Adapter<RecyclerAdapterClientes.ClientesViewHolder>{

    // Declaraciones
    private Context context;
    private ArrayList<Cliente> lista;

    public class ClientesViewHolder extends RecyclerView.ViewHolder {

        private TextView TV_nombre, TV_dni,  TV_telefono;

        public ClientesViewHolder(@NonNull View itemView) {
            super(itemView);

            TV_nombre = itemView.findViewById(R.id.TV_nombre_cliente);
            TV_dni = itemView.findViewById(R.id.TV_dni_cliente);
            TV_telefono = itemView.findViewById(R.id.TV_telefono_cliente);
      }

        public TextView getTV_nombre() {
            return TV_nombre;
        }

        public TextView getTV_dni() {
            return TV_dni;
        }

        public TextView getTV_telefono() {
            return TV_telefono;
        }
    }

    // constructor recycler
    public RecyclerAdapterClientes(Context context, ArrayList<Cliente> objetos){
        this.lista = objetos;
        this.context = context;

    }


    @NonNull
    @Override
    public ClientesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        int layoutIdParaListItem = R.layout.fragment_cliente_fila;
        boolean attachToParentRapido = false;
        View view = LayoutInflater.from(parent.getContext())
                .inflate(layoutIdParaListItem, parent, attachToParentRapido);

        RecyclerAdapterClientes.ClientesViewHolder clientesViewHolder = new RecyclerAdapterClientes.ClientesViewHolder(view);

        return clientesViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ClientesViewHolder holder, int position) {
        if (lista.size()==0){
            Toast.makeText(context, "No hay clientes para mostrar en la lista", Toast.LENGTH_SHORT).show();
        }
        Log.d("mensaje", "En la posicion " + position );

        Cliente cliente = lista.get(position);

        holder.TV_nombre.setText("Nombre: " + cliente.getNombreCompleto());
        holder.TV_dni.setText("Dni: " + cliente.getDni());
        holder.TV_telefono.setText("Telefono: " + cliente.getTelefono());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();                                           // Instancio el bundle a enviar
                bundle.putSerializable("cliente", cliente);                           // Meto la preparacion en el bundle
                Log.d("mensaje","RV clientes: " + cliente.toString());
                Navigation.findNavController(view).navigate(R.id.clienteDetalleFragment,bundle);       // Meto el bundle en el navigation
            }
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }


}
