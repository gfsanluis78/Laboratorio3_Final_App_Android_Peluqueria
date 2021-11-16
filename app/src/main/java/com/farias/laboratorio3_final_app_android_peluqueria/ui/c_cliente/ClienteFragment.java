package com.farias.laboratorio3_final_app_android_peluqueria.ui.c_cliente;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.farias.laboratorio3_final_app_android_peluqueria.databinding.FragmentClienteBinding;
import com.farias.laboratorio3_final_app_android_peluqueria.ui.a_home.adapter.RecyclerAdapterHomeClientes;
import com.farias.laboratorio3_final_app_android_peluqueria.ui.c_cliente.adapter.RecyclerAdapterClientes;

public class ClienteFragment extends Fragment {

    private ClienteViewModel clienteViewModel;
    private FragmentClienteBinding binding;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;


    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        clienteViewModel =
                new ViewModelProvider(this).get(ClienteViewModel.class);

    binding = FragmentClienteBinding.inflate(inflater, container, false);
    clienteViewModel = new ViewModelProvider(this).get(ClienteViewModel.class);
    recyclerView = binding.RVTodosLosClientes;
    linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

    View root = binding.getRoot();

    clienteViewModel.getClientes().observe(getViewLifecycleOwner(), clientes -> {
        RecyclerAdapterClientes mAdapter = new RecyclerAdapterClientes(getContext(), clientes);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(mAdapter);
    });

        //final TextView textView = binding.textCliente;
        clienteViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                // textView.setText(s);
            }
        });

        clienteViewModel.setClientes();

        return root;
    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}