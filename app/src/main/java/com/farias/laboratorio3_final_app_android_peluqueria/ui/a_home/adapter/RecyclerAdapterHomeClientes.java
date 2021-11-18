package com.farias.laboratorio3_final_app_android_peluqueria.ui.a_home.adapter;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
import com.farias.laboratorio3_final_app_android_peluqueria.modelo.Turno;

import java.util.ArrayList;

/**
 * Created by Genaro Farias el 10/11/2021.
 * Estudiante de la ULP
 * gfsanluis78@gmail.com
 */

public class RecyclerAdapterHomeClientes extends RecyclerView.Adapter<RecyclerAdapterHomeClientes.HomeClientesViewHolder> {

    // Declaraciones
    private Context context;
    private ArrayList<Cliente> lista;


    // ######################################
    //           implemento el holder
    // ######################################
    public static class HomeClientesViewHolder extends RecyclerView.ViewHolder {

        private ImageView IV_foto;
        private TextView TV_nombre, TV_telefono;

        public HomeClientesViewHolder(@NonNull View itemView) {
            super(itemView);

            IV_foto = itemView.findViewById(R.id.IV_foto);
            TV_nombre = itemView.findViewById(R.id.TV_nombre);
            TV_telefono = itemView.findViewById(R.id.TV_Telefono);
        }

        public ImageView getIV_foto() {
            return IV_foto;
        }

        public TextView getTV_nombre() {
            return TV_nombre;
        }

        public TextView getTV_telefono() {
            return TV_telefono;
        }
    }



    // ####################################
    //             Constructor
    // ####################################
    // Se inicializa la informacion del adapter
    public RecyclerAdapterHomeClientes(Context context, ArrayList<Cliente> objetos){
        this.lista = objetos;
        this.context = context;
    }

    // #################################
    //         OnCreateViewHolder
    // #################################

    @NonNull
    @Override
    public RecyclerAdapterHomeClientes.HomeClientesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        int layoutIdParaListItem = R.layout.fragment_home_fila;
        boolean attachToParentRapido = false;
        View view = LayoutInflater.from(parent.getContext())
                .inflate(layoutIdParaListItem, parent, attachToParentRapido);

        HomeClientesViewHolder homeClientesViewHolder = new HomeClientesViewHolder(view);

        return homeClientesViewHolder;
    }

    // #################################
    //         OnBindViewHolder
    // #################################

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterHomeClientes.HomeClientesViewHolder holder, int position) {

        if (lista.size()==0){
            Toast.makeText(context, "No hay clientes para mostrar en la lista", Toast.LENGTH_SHORT).show();
        }
        Log.d("mensaje", "En la posicion " + position );

        Cliente cliente = lista.get(position);

        holder.TV_nombre.setText(cliente.getNombreCompleto());
        holder.TV_telefono.setText(cliente.getTelefono());
        Glide.with(context)
                .load(cliente.getUrlFoto())
                .diskCacheStrategy(DiskCacheStrategy.ALL)       // Llama la imagen remota y la carga en el cache,
                .into(holder.IV_foto);                          // despues la busca de ahi y es mas rapido
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();                                           // Instancio el bundle a enviar
                Preparacion preparacion = new Preparacion();                            // Comienzo a preparar el turno
                preparacion.setCliente(cliente);
                bundle.putSerializable("preparacion", preparacion);                           // Meto la preparacion en el bundle
                Log.d("mensajeViewHolder",preparacion.getCliente().getApellido()+"");
                Navigation.findNavController(view).navigate(R.id.elegirTrabajoFragment,bundle);       // Meto el bundle en el navigation
            }
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }


}
