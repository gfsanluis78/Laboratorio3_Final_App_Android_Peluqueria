package com.farias.laboratorio3_final_app_android_peluqueria.ui.a_home.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import com.farias.laboratorio3_final_app_android_peluqueria.modelo.Bloque;
import com.farias.laboratorio3_final_app_android_peluqueria.modelo.Preparacion;

import java.util.ArrayList;

/**
 * Created by Genaro Farias el 13/11/2021.
 * Estudiante de la ULP
 * gfsanluis78@gmail.com
 */

public class RecyclerAdapterHomeBloques extends RecyclerView.Adapter<RecyclerAdapterHomeBloques.HomeBloquesViewHolder> {

    // declaraciones
    private Context context;
    private ArrayList<Bloque> lista;
    private Preparacion preparacion;

    // implemento el holder
    public static class HomeBloquesViewHolder extends RecyclerView.ViewHolder {

        private TextView TV_nombre;

        public HomeBloquesViewHolder(@NonNull View itemView) {
            super(itemView);

            TV_nombre = itemView.findViewById(R.id.TV_nombre);
        }

        public TextView getTV_nombre() {
            return TV_nombre;
        }

    }


    // Se inicializa la informacion del adapter
    public RecyclerAdapterHomeBloques(Context context, ArrayList<Bloque> objetos, Preparacion preparacion){
        Log.d("mensaje ", "Recycler iniciado");
        this.lista = objetos;
        this.context = context;
        this.preparacion = preparacion;
    }



    @NonNull
    @Override
    public RecyclerAdapterHomeBloques.HomeBloquesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        int layoutIdParaListItem = R.layout.fragment_home_bloque_fila;
        boolean attachToParentRapido = false;
        View view = LayoutInflater.from(parent.getContext())
                .inflate(layoutIdParaListItem, parent, attachToParentRapido);

        RecyclerAdapterHomeBloques.HomeBloquesViewHolder homeBloquesViewHolder = new RecyclerAdapterHomeBloques.HomeBloquesViewHolder(view);

        return homeBloquesViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterHomeBloques.HomeBloquesViewHolder holder, int position) {

        if (lista.size()==0){
            Toast.makeText(context, "No hay Bloques para mostrar en la lista", Toast.LENGTH_SHORT).show();
        }
        Log.d("mensaje ", "OnBindHolder Bloques: En la posicion " + position );

        Bloque bloque = lista.get(position);

        holder.TV_nombre.setText(bloque.getDesdeHasta());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Confirmacion de turno");
                builder.setMessage("Seguro desea el crear turno?");
                builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Bundle bundle = new Bundle();                                           // Instancio el bundle a enviar
                        //Preparacion preparacion = new Preparacion();                            // Comienzo a preparar el Bloque
                        preparacion.setBloque(bloque);
                        bundle.putSerializable("preparacion", preparacion);                           // Meto la preparacion en el bundle
                        Log.d("mensaje ","OnBindViewHolder Cliente: " + preparacion.toString());
                        Navigation.findNavController(view).navigate(R.id.confirmacionTurnoFragment,bundle);       // Meto el bundle en el navigation
                    }
                });
                // y si no quiero
                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                //Inicializar el alert dialog
                AlertDialog alertDialog = builder.create();
                // Mostrar el alert
                alertDialog.show();



            }
        });

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }


}
