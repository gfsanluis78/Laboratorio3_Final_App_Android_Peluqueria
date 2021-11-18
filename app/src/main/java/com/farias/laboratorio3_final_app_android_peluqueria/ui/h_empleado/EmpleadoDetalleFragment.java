package com.farias.laboratorio3_final_app_android_peluqueria.ui.h_empleado;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.farias.laboratorio3_final_app_android_peluqueria.databinding.FragmentEmpleadoDetalleBinding;
import com.farias.laboratorio3_final_app_android_peluqueria.modelo.Empleado;
import com.farias.laboratorio3_final_app_android_peluqueria.ui.i_trabajo.adapter.RecyclerAdapterTrabajos;

public class EmpleadoDetalleFragment extends Fragment {

    private EmpleadoDetalleViewModel mViewModel;
    private FragmentEmpleadoDetalleBinding binding;
    private Empleado e;
    private View view;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerAdapterTrabajos mAdapter;

    public static EmpleadoDetalleFragment newInstance() {
        return new EmpleadoDetalleFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mViewModel = new ViewModelProvider(this).get(EmpleadoDetalleViewModel.class);
        binding = FragmentEmpleadoDetalleBinding.inflate(inflater, container, false);
        view = binding.getRoot();
        recyclerView = binding.RVTipoTrabajos;
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);


        mViewModel.getEmpleadoMutableLiveData().observe(getViewLifecycleOwner(), empleado -> {

            binding.TVTitulo.setText(empleado.getNombreCompleto());
            binding.TVEmpleadoDni.setText(empleado.getDni());
            binding.TVNombreNombre.setText(empleado.getNombre());
            binding.TVEmpleadoApellido.setText(empleado.getApellido());
            binding.TVEmpleadoEmail.setText(empleado.getEmail());
            binding.TVEmpleadoTelefono.setText(empleado.getTelefono());
            Glide.with(getContext())
                    .load(empleado.getUrlFoto())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)               // Llama la imagen remota y la carga en el cache,
                    .into(binding.IVFoto);                          // despues la busca de ahi y es mas rapido

            mViewModel.setTiposDeTrabajos(empleado);
            mViewModel.setCantidadTurnosRealizados(empleado);



        });

        mViewModel.getCantidadTurnosRealizados().observe(getViewLifecycleOwner(), cantidad ->{
            binding.TVEmpleadoCantidadTurnos.setText(cantidad);

        });

        mViewModel.getTipoTrabajosMutableLiveData().observe(getViewLifecycleOwner(), tipoDeTrabajos -> {
            // Cargo el rv
            mAdapter = new RecyclerAdapterTrabajos(getContext(), tipoDeTrabajos);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(mAdapter);
        });

        mViewModel.setEmpleadoMutableLiveData(getArguments());

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // TODO: Use the ViewModel
    }

}