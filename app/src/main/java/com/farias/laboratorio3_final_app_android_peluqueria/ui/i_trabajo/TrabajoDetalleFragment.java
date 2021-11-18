package com.farias.laboratorio3_final_app_android_peluqueria.ui.i_trabajo;

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
import com.farias.laboratorio3_final_app_android_peluqueria.databinding.FragmentTrabajoDetalleBinding;
import com.farias.laboratorio3_final_app_android_peluqueria.modelo.TipoDeTrabajo;
import com.farias.laboratorio3_final_app_android_peluqueria.ui.h_empleado.adapter.RecyclerAdapterEmpleados;

public class TrabajoDetalleFragment extends Fragment {

    private TrabajoDetalleViewModel mViewModel;
    private FragmentTrabajoDetalleBinding binding;
    private TipoDeTrabajo t;
    private View view;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerAdapterEmpleados mAdapter;

    public static TrabajoDetalleFragment newInstance() {
        return new TrabajoDetalleFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        // inicializo
        mViewModel = new ViewModelProvider(this).get(TrabajoDetalleViewModel.class);
        binding = FragmentTrabajoDetalleBinding.inflate(inflater, container, false);
        view = binding.getRoot();
        recyclerView = binding.RVEmpleadosTrabajos;
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        // implemnto viewmodel
        mViewModel.getTipoDeTrabajoMutableLiveData().observe(getViewLifecycleOwner(), tipoDeTrabajo -> {

            binding.TVTitulo.setText(tipoDeTrabajo.getNombre());
            binding.TVTipoTrabajoNombre.setText(tipoDeTrabajo.getNombre());
            binding.TVTipoTrabajoDescripcion.setText(tipoDeTrabajo.getDescripcion());
            binding.TVTipoTrabajoId.setText(tipoDeTrabajo.getIdTipodeTrabajo()+"");
            binding.TVTipoTrabajoDuracion.setText(tipoDeTrabajo.getDuracion());
            binding.TVTipoTrabajoCosto.setText(tipoDeTrabajo.getCosto());
            Glide.with(getContext())
                    .load(tipoDeTrabajo.getUrlFoto())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)               // Llama la imagen remota y la carga en el cache,
                    .into(binding.IVFoto);                          // despues la busca de ahi y es mas rapido

            mViewModel.setEmpleadosByTipoTrabajo(tipoDeTrabajo);
        });

        mViewModel.getListMutableLiveData().observe(getViewLifecycleOwner(), empleados -> {
            // cargo el RV
            mAdapter = new RecyclerAdapterEmpleados(getContext(), empleados);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(mAdapter);

        });

        mViewModel.setTipoDeTrabajoMutableLiveData(getArguments());



        return view;
    }



}