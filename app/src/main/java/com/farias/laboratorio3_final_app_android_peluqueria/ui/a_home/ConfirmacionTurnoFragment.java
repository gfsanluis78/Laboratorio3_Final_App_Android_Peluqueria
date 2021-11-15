package com.farias.laboratorio3_final_app_android_peluqueria.ui.a_home;

import androidx.lifecycle.ViewModelProvider;

import android.graphics.Color;
import android.graphics.ColorSpace;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.farias.laboratorio3_final_app_android_peluqueria.R;
import com.farias.laboratorio3_final_app_android_peluqueria.databinding.FragmentConfirmacionTurnoBinding;
import com.farias.laboratorio3_final_app_android_peluqueria.databinding.FragmentElegirBloqueBinding;
import com.farias.laboratorio3_final_app_android_peluqueria.modelo.Bloque;
import com.farias.laboratorio3_final_app_android_peluqueria.modelo.Preparacion;

public class ConfirmacionTurnoFragment extends Fragment {

    private ConfirmacionTurnoViewModel mViewModel;
    private FragmentConfirmacionTurnoBinding binding;
    private Preparacion p;
    private TextView TV_confirmacion;
    private Button volver;

    public static ConfirmacionTurnoFragment newInstance() {
        return new ConfirmacionTurnoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentConfirmacionTurnoBinding.inflate(inflater, container, false);
        mViewModel = new ViewModelProvider(this).get(ConfirmacionTurnoViewModel.class);
        TV_confirmacion = binding.TVMensaje;
        volver = binding.BTVolver;

        View view = binding.getRoot();


        mViewModel.getPreparacionMutableLiveData().observe( getViewLifecycleOwner(), preparacion -> {
            if(preparacion != null){
                p = preparacion;
                binding.TVNombre.setText("Nombre: " + preparacion.getCliente().getNombreCompleto());
                binding.TVDni.setText("Dni: " + preparacion.getCliente().getDni());
                binding.TVTelefono.setText("Telefono: " + preparacion.getCliente().getTelefono());
                binding.TVTrabajo.setText(preparacion.getTipoDeTrabajo().getNombre());
                Glide.with(getContext())
                        .load(preparacion.getTipoDeTrabajo().getUrlFoto())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)               // Llama la imagen remota y la carga en el cache,
                        .into(binding.IVFotoTrabajo);                          // despues la busca de ahi y es mas rapido
                Glide.with(getContext())
                        .load(preparacion.getCliente().getUrlFoto())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)               // Llama la imagen remota y la carga en el cache,
                        .into(binding.IVFotoDelCliente);                          // despues la busca de ahi y es mas rapido
                binding.TVProfesional.setText(preparacion.getEmpleado().getNombreCompleto());
                Glide.with(getContext())
                        .load(preparacion.getEmpleado().getUrlFoto())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)               // Llama la imagen remota y la carga en el cache,
                        .into(binding.IVFotoProfesional);
                binding.TVFecha.setText(p.getFecha().getDate());
                binding.TVTurno.setText(p.getBloque().getDesde() + " a " + p.getBloque().getHasta());
                volver.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        p = null;
                        Navigation.findNavController(view).navigate(R.id.nav_home);
                    }
                });
                mViewModel.setTurno(p);

            }
        });

        mViewModel.getTurnoMutableLiveData().observe(getViewLifecycleOwner(), turno -> {
            Log.d("mensaje", "##### El turno creado es: " + turno);

            TV_confirmacion.setTextColor(Color.GREEN);
            TV_confirmacion.setText("El turno con el id " + turno.getIdTurno() + " se creo con exito.");
        });


        mViewModel.setPreparacion(getArguments());
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ConfirmacionTurnoViewModel.class);
        // TODO: Use the ViewModel
    }

}