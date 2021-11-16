package com.farias.laboratorio3_final_app_android_peluqueria.ui.b_turno.adapter;

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
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;


import com.farias.laboratorio3_final_app_android_peluqueria.R;
import com.farias.laboratorio3_final_app_android_peluqueria.databinding.FragmentTodosLosTurnosFilaBinding;
import com.farias.laboratorio3_final_app_android_peluqueria.modelo.Turno;

import java.util.ArrayList;

/**
 * Created by Genaro Farias el 15/11/2021.
 * Estudiante de la ULP
 * gfsanluis78@gmail.com
 */

public class RecyclerAdapterTurnoTodos extends RecyclerView.Adapter<RecyclerAdapterTurnoTodos.TurnoTodosViewHolder> {

    // Declaraciones
    private Context context;
    private ArrayList<Turno> lista;


    // ######################################
    //           implemento el holder
    // ######################################
    public static class TurnoTodosViewHolder extends RecyclerView.ViewHolder {

        private TextView TV_fecha, TV_bloque, TV_cliente, TV_profesional, TV_id ;
        private CardView CV_turno;

        public TurnoTodosViewHolder(@NonNull View itemView) {
            super(itemView);

            TV_fecha = itemView.findViewById(R.id.TV_fecha);
            TV_bloque = itemView.findViewById(R.id.TV_bloque);
            TV_cliente = itemView.findViewById(R.id.TV_cliente);
            TV_profesional = itemView.findViewById(R.id.TV_profesional);
            CV_turno = itemView.findViewById(R.id.CV_turno);
            TV_id = itemView.findViewById(R.id.TV_id);
        }

        public TextView getTV_fecha() {
            return TV_fecha;
        }

        public TextView getTV_bloque() {
            return TV_bloque;
        }

        public TextView getTV_cliente() {
            return TV_cliente;
        }

        public TextView getTV_profesional() {
            return TV_profesional;
        }

        public CardView getCV_turno() { return CV_turno; }

        public TextView getTV_id() { return TV_id; }

    }



    // ####################################
    //             Constructor
    // ####################################
    // Se inicializa la informacion del adapter
    public RecyclerAdapterTurnoTodos(Context context, ArrayList<Turno> objetos){
        this.lista = objetos;
        this.context = context;
    }

    // #################################
    //         OnCreateViewHolder
    // #################################

    @NonNull
    @Override
    public RecyclerAdapterTurnoTodos.TurnoTodosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        int layoutIdParaListItem = R.layout.fragment_todos_los_turnos_fila;
        boolean attachToParentRapido = false;
        View view = LayoutInflater.from(parent.getContext())
                .inflate(layoutIdParaListItem, parent, attachToParentRapido);

        TurnoTodosViewHolder turnosViewHolder = new TurnoTodosViewHolder(view);

        return turnosViewHolder;
    }

    // #################################
    //         OnBindViewHolder
    // #################################

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterTurnoTodos.TurnoTodosViewHolder holder, int position) {

        if (lista.size()==0){
            Toast.makeText(context, "No hay Turnos para mostrar en la lista", Toast.LENGTH_SHORT).show();
        }
        Log.d("mensaje", "En la posicion " + position );

        Turno turno = lista.get(position);

        holder.TV_id.setText("Identificacion n°: " +turno.getIdTurno());
        holder.TV_fecha.setText("Fecha: " + turno.getFecha());
        holder.TV_bloque.setText("Horario: " + turno.getBloque().getDesdeHasta());
        holder.TV_cliente.setText("Cliente :" + turno.getCliente().getNombreCompleto());
        holder.TV_profesional.setText("Profesional: " + turno.getTrabajo().getEmpleado().getNombreCompleto());
        holder.CV_turno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();                                           // Instancio el bundle a enviar
                bundle.putSerializable("turno", turno);                           // Meto la inmueble en el bundle
                Log.d("mensaje","Se envia el bundle con: " + turno.toString());
                Navigation.findNavController(view).navigate(R.id.turnoDetalleFragment,bundle);       // Meto el bundle clave budle en el navigation
            }
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
}

