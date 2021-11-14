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
import com.farias.laboratorio3_final_app_android_peluqueria.modelo.Empleado;
import com.farias.laboratorio3_final_app_android_peluqueria.modelo.Preparacion;

import java.util.ArrayList;

/**
 * Created by Genaro Farias el 12/11/2021.
 * Estudiante de la ULP
 * gfsanluis78@gmail.com
 */

public class RecyclerAdapterHomeEmpleados extends RecyclerView.Adapter<RecyclerAdapterHomeEmpleados.HomeEmpleadosViewHolder> {

    // declaraciones
    private Context context;
    private ArrayList<Empleado> lista;
    private Preparacion preparacion;

    // implemento el holder
    public static class HomeEmpleadosViewHolder extends RecyclerView.ViewHolder {

        private ImageView IV_foto;
        private TextView TV_nombre;

        public HomeEmpleadosViewHolder(@NonNull View itemView) {
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
    public RecyclerAdapterHomeEmpleados(Context context, ArrayList<Empleado> objetos, Preparacion preparacion){
        Log.d("mensaje ", "Recycler iniciado");
        this.lista = objetos;
        this.context = context;
        this.preparacion = preparacion;
    }



    @NonNull
    @Override
    public RecyclerAdapterHomeEmpleados.HomeEmpleadosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        int layoutIdParaListItem = R.layout.fragment_home_profesional_fila;
        boolean attachToParentRapido = false;
        View view = LayoutInflater.from(parent.getContext())
                .inflate(layoutIdParaListItem, parent, attachToParentRapido);

        RecyclerAdapterHomeEmpleados.HomeEmpleadosViewHolder homeEmpleadosViewHolder = new RecyclerAdapterHomeEmpleados.HomeEmpleadosViewHolder(view);

        return homeEmpleadosViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterHomeEmpleados.HomeEmpleadosViewHolder holder, int position) {

        if (lista.size()==0){
            Toast.makeText(context, "No hay Empleados para mostrar en la lista", Toast.LENGTH_SHORT).show();
        }
        Log.d("mensaje ", "OnBindHolder Empleados: En la posicion " + position );

        Empleado empleado = lista.get(position);

        holder.TV_nombre.setText(empleado.getNombreCompleto());
        Glide.with(context)
                .load(empleado.getUrlFoto())
                .diskCacheStrategy(DiskCacheStrategy.ALL)       // Llama la imagen remota y la carga en el cache,
                .into(holder.IV_foto);                          // despues la busca de ahi y es mas rapido

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();                                           // Instancio el bundle a enviar
                //Preparacion preparacion = new Preparacion();                            // Comienzo a preparar el turno
                preparacion.setEmpleado(empleado);
                bundle.putSerializable("preparacion", preparacion);                           // Meto la preparacion en el bundle
                Log.d("mensaje ","OnBindViewHolder Cliente: " + preparacion.getCliente().getApellido()+". Tipo de trabajo: " + preparacion.getTipoDeTrabajo().getNombre());
                Navigation.findNavController(view).navigate(R.id.elegirFechaFragment,bundle);       // Meto el bundle en el navigation
            }
        });

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }


}
