package com.farias.laboratorio3_final_app_android_peluqueria.ui.a_home;

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
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.farias.laboratorio3_final_app_android_peluqueria.R;
import com.farias.laboratorio3_final_app_android_peluqueria.databinding.FragmentHomeBinding;
import com.farias.laboratorio3_final_app_android_peluqueria.ui.a_home.adapter.RecyclerAdapterHomeClientes;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    // Rview
    private RecyclerView recyclerView;
    private FragmentHomeBinding binding;
    private View root;
    private RecyclerAdapterHomeClientes mAdapter;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView.LayoutManager layoutManager;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        // inicializo el recycler view
        recyclerView = binding.recyclerView;

        // opcion linear
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        // o opcion grid
        layoutManager = new GridLayoutManager(getContext(), 2);

        root = binding.getRoot();
        homeViewModel.getClientes().observe(getViewLifecycleOwner(), clientes -> {

        mAdapter = new RecyclerAdapterHomeClientes(getContext(), clientes);
        // uso la grid
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);

    });

    binding.button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Navigation.findNavController(view).navigate(R.id.clienteCrearFragment);
        }
    });


    homeViewModel.setClientes();

        return root;
    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}