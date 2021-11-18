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

import com.farias.laboratorio3_final_app_android_peluqueria.R;
import com.farias.laboratorio3_final_app_android_peluqueria.databinding.FragmentEmpleadoBinding;
import com.farias.laboratorio3_final_app_android_peluqueria.ui.c_cliente.adapter.RecyclerAdapterClientes;
import com.farias.laboratorio3_final_app_android_peluqueria.ui.h_empleado.adapter.RecyclerAdapterEmpleados;

public class EmpleadoFragment extends Fragment {

    private EmpleadoViewModel mViewModel;
    private FragmentEmpleadoBinding binding;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private View view;
    private RecyclerAdapterEmpleados mAdapter;


    public static EmpleadoFragment newInstance() {
        return new EmpleadoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(EmpleadoViewModel.class);
        binding = FragmentEmpleadoBinding.inflate(inflater, container, false);
        view = binding.getRoot();
        recyclerView = binding.RVTodosLosEmpleados;
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        mViewModel.getEmpleadosMutableLiveData().observe(getViewLifecycleOwner(), empleados -> {
            mAdapter = new RecyclerAdapterEmpleados(getContext(), empleados);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(mAdapter);
        });

        mViewModel.setEmpleados();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // TODO: Use the ViewModel
    }

}