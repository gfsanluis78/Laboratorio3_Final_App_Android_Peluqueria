package com.farias.laboratorio3_final_app_android_peluqueria.ui.c_cliente;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.farias.laboratorio3_final_app_android_peluqueria.R;
import com.farias.laboratorio3_final_app_android_peluqueria.databinding.FragmentClienteTurnosBinding;
import com.farias.laboratorio3_final_app_android_peluqueria.ui.b_turno.adapter.RecyclerAdapterTurnoTodos;

public class ClienteTurnosFragment extends Fragment {

    private ClienteTurnosViewModel mViewModel;
    private FragmentClienteTurnosBinding binding;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private View view;

    public static ClienteTurnosFragment newInstance() {
        return new ClienteTurnosFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mViewModel = new ViewModelProvider(this).get(ClienteTurnosViewModel.class);
        binding = FragmentClienteTurnosBinding.inflate(inflater, container,false);
        recyclerView = binding.RVTurnosCliente;
        view = binding.getRoot();
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        mViewModel.getTurnosMutableLiveData().observe(getViewLifecycleOwner(), turnos -> {

            if(turnos.size() != 0 ){
                binding.TVTituloTurnosCliente.setText("Todos los turnos de "+turnos.get(0).getCliente().getNombreCompleto() );

                Log.d("mensaje", "llegan los tunos " + turnos );
                RecyclerAdapterTurnoTodos mAdapter = new RecyclerAdapterTurnoTodos(getContext(), turnos);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(mAdapter);
            }
        });

        mViewModel.setClienteMutableLiveData(getArguments());
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel
    }

}