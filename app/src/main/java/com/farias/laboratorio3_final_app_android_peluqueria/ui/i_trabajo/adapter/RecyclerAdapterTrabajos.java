package com.farias.laboratorio3_final_app_android_peluqueria.ui.i_trabajo.adapter;

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

import com.farias.laboratorio3_final_app_android_peluqueria.R;
import com.farias.laboratorio3_final_app_android_peluqueria.modelo.TipoDeTrabajo;

import java.util.ArrayList;

/**
 * Created by Genaro Farias el 17/11/2021.
 * Estudiante de la ULP
 * gfsanluis78@gmail.com
 */

public class RecyclerAdapterTrabajos extends RecyclerView.Adapter<RecyclerAdapterTrabajos.TrabajosViewHolder> {

    // Declaraciones
    private Context context;
    private ArrayList<TipoDeTrabajo> lista;

    public class TrabajosViewHolder extends RecyclerView.ViewHolder {

        private TextView TV_nombre, TV_id,  TV_costo;

        public TrabajosViewHolder(@NonNull View itemView) {
            super(itemView);

            TV_nombre = itemView.findViewById(R.id.TV_nombre_tipo_trabajo_fila);
            TV_id = itemView.findViewById(R.id.TV_id_tipo_trabajo_fila);
            TV_costo = itemView.findViewById(R.id.TV_tipo_trabajo_costo_fila);
        }

        public TextView getTV_nombre() {
            return TV_nombre;
        }

        public TextView getTV_id() {
            return TV_id;
        }

        public TextView getTV_costo() {
            return TV_costo;
        }
    }

    // constructor recycler
    public RecyclerAdapterTrabajos(Context context, ArrayList<TipoDeTrabajo> objetos){
        this.lista = objetos;
        this.context = context;

    }



    @NonNull
    @Override
    public TrabajosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layoutIdParaListItem = R.layout.fragment_trabajo_fila;
        boolean attachToParentRapido = false;
        View view = LayoutInflater.from(parent.getContext())
                .inflate(layoutIdParaListItem, parent, attachToParentRapido);

        RecyclerAdapterTrabajos.TrabajosViewHolder trabajosViewHolder = new RecyclerAdapterTrabajos.TrabajosViewHolder(view);

        return trabajosViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TrabajosViewHolder holder, int position) {
        if (lista.size()==0){
            Toast.makeText(context, "No hay trabajos para mostrar en la lista", Toast.LENGTH_SHORT).show();
        }
        Log.d("mensaje", "En la posicion " + position );

        TipoDeTrabajo tipoDeTrabajo = lista.get(position);

        Log.d("mensaje", "El tipo de trabajo es : " + tipoDeTrabajo.toString());

        holder.TV_nombre.setText("Nombre: " + tipoDeTrabajo.getNombre());
        holder.TV_id.setText("Identificacion: " + tipoDeTrabajo.getIdTipodeTrabajo());
        holder.TV_costo.setText("Costo: " + tipoDeTrabajo.getCosto());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();                                           // Instancio el bundle a enviar
                bundle.putSerializable("trabajo", tipoDeTrabajo);                           // Meto la preparacion en el bundle
                Log.d("mensaje","RV trabajos: " + tipoDeTrabajo.toString());
                Navigation.findNavController(view).navigate(R.id.trabajoDetalleFragment,bundle);       // Meto el bundle en el navigation
            }
        });


    }

    @Override
    public int getItemCount() {
        return lista.size();
    }


}
