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
import com.farias.laboratorio3_final_app_android_peluqueria.modelo.TipoDeTrabajo;
import com.farias.laboratorio3_final_app_android_peluqueria.modelo.Trabajo;

import java.util.ArrayList;

/**
 * Created by Genaro Farias el 12/11/2021.
 * Estudiante de la ULP
 * gfsanluis78@gmail.com
 */

public class RecyclerAdapterHomeTrabajos extends RecyclerView.Adapter<RecyclerAdapterHomeTrabajos.HomeTrabajosViewHolder> {

    // declaraciones
    private Context context;
    private ArrayList<TipoDeTrabajo> lista;

    // implemento el holder
    public static class HomeTrabajosViewHolder extends RecyclerView.ViewHolder {

        private ImageView IV_foto;
        private TextView TV_nombre;

        public HomeTrabajosViewHolder(@NonNull View itemView) {
            super(itemView);

            IV_foto = itemView.findViewById(R.id.IV_foto);
            TV_nombre = itemView.findViewById(R.id.TV_nombre);
        }

        public ImageView getIV_foto() {
            return IV_foto;
        }

        public TextView getTV_nombre() {
            return TV_nombre;
        }

    }


    // Se inicializa la informacion del adapter
    public RecyclerAdapterHomeTrabajos(Context context, ArrayList<TipoDeTrabajo> objetos){
        Log.d("mensaje ", "Recycler iniciado");
        this.lista = objetos;
        this.context = context;
    }



    @NonNull
    @Override
    public RecyclerAdapterHomeTrabajos.HomeTrabajosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        int layoutIdParaListItem = R.layout.fragment_home_trabajos_fila;
        boolean attachToParentRapido = false;
        View view = LayoutInflater.from(parent.getContext())
                .inflate(layoutIdParaListItem, parent, attachToParentRapido);

        RecyclerAdapterHomeTrabajos.HomeTrabajosViewHolder homeTrabajosViewHolder = new RecyclerAdapterHomeTrabajos.HomeTrabajosViewHolder(view);

        return homeTrabajosViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterHomeTrabajos.HomeTrabajosViewHolder holder, int position) {

        if (lista.size()==0){
            Toast.makeText(context, "No hay trabajos para mostrar en la lista", Toast.LENGTH_SHORT).show();
        }
        Log.d("mensaje ", "OnBindHolder trabajos: En la posicion " + position );

        TipoDeTrabajo tipoDeTrabajo = lista.get(position);

        holder.TV_nombre.setText(tipoDeTrabajo.getNombre());
        Glide.with(context)
                .load(tipoDeTrabajo.getUrlFoto())
                .diskCacheStrategy(DiskCacheStrategy.ALL)       // Llama la imagen remota y la carga en el cache,
                .into(holder.IV_foto);                          // despues la busca de ahi y es mas rapido
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();                                           // Instancio el bundle a enviar
                Preparacion preparacion = new Preparacion();                            // Comienzo a preparar el turno
                preparacion.setTipoDeTrabajo(tipoDeTrabajo);
                bundle.putSerializable("preparacion", preparacion);                           // Meto la preparacion en el bundle
                Log.d("mensajeViewHolder",preparacion.getCliente().getApellido()+"");
                //Navigation.findNavController(view).navigate(R.id.elegirTrabajoFragment,bundle);       // Meto el bundle en el navigation
            }
        });

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }


}
