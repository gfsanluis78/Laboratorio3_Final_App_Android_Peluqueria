package com.farias.laboratorio3_final_app_android_peluqueria.ui.h_empleado.adapter;

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
import com.farias.laboratorio3_final_app_android_peluqueria.modelo.Empleado;


import java.util.ArrayList;

/**
 * Created by Genaro Farias el 17/11/2021.
 * Estudiante de la ULP
 * gfsanluis78@gmail.com
 */

public class RecyclerAdapterEmpleados extends RecyclerView.Adapter<RecyclerAdapterEmpleados.EmpleadosViewHolder> {

    // Declaraciones
    private Context context;
    private ArrayList<Empleado> lista;

    public class EmpleadosViewHolder extends RecyclerView.ViewHolder {

        private TextView TV_nombre, TV_dni,  TV_telefono;

        public EmpleadosViewHolder(@NonNull View itemView) {
            super(itemView);

            TV_nombre = itemView.findViewById(R.id.TV_nombre_empleado);
            TV_dni = itemView.findViewById(R.id.TV_dni_empleado);
            TV_telefono = itemView.findViewById(R.id.TV_telefono_empleado);
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
    public RecyclerAdapterEmpleados(Context context, ArrayList<Empleado> objetos){
        this.lista = objetos;
        this.context = context;

    }



    @NonNull
    @Override
    public EmpleadosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layoutIdParaListItem = R.layout.fragment_empleado_fila;
        boolean attachToParentRapido = false;
        View view = LayoutInflater.from(parent.getContext())
                .inflate(layoutIdParaListItem, parent, attachToParentRapido);

        RecyclerAdapterEmpleados.EmpleadosViewHolder empleadosViewHolder = new RecyclerAdapterEmpleados.EmpleadosViewHolder(view);

        return empleadosViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull EmpleadosViewHolder holder, int position) {
        if (lista.size()==0){
            Toast.makeText(context, "No hay empleados para mostrar en la lista", Toast.LENGTH_SHORT).show();
        }
        Log.d("mensaje", "En la posicion " + position );

        Empleado empleado = lista.get(position);

        holder.TV_nombre.setText("Nombre: " + empleado.getNombreCompleto());
        holder.TV_dni.setText("Dni: " + empleado.getDni());
        holder.TV_telefono.setText("Telefono: " + empleado.getTelefono());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();                                           // Instancio el bundle a enviar
                bundle.putSerializable("empleado", empleado);                           // Meto la preparacion en el bundle
                Log.d("mensaje","RV empleados: " + empleado.toString());
                Navigation.findNavController(view).navigate(R.id.empleadoDetalleFragment,bundle);       // Meto el bundle en el navigation
            }
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }


}
