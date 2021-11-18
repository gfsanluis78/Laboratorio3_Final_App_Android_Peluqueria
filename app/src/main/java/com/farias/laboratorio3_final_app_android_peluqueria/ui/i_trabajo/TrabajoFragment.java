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

import com.farias.laboratorio3_final_app_android_peluqueria.databinding.FragmentTrabajoBinding;
import com.farias.laboratorio3_final_app_android_peluqueria.ui.i_trabajo.adapter.RecyclerAdapterTrabajos;

public class TrabajoFragment extends Fragment {

    private TrabajoViewModel mViewModel;
    private FragmentTrabajoBinding binding;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private View view;
    private RecyclerAdapterTrabajos mAdapter;

    public static TrabajoFragment newInstance() {
        return new TrabajoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mViewModel = new ViewModelProvider(this).get(TrabajoViewModel.class);
        binding = FragmentTrabajoBinding.inflate(inflater,container,false);
        view = binding.getRoot();
        recyclerView = binding.RVTodosLosTrabajos;
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        mViewModel.getTrabajos().observe(getViewLifecycleOwner(), tipoDeTrabajos -> {
            mAdapter = new RecyclerAdapterTrabajos(getContext(), tipoDeTrabajos);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(mAdapter);
        });

        mViewModel.setTipoTrabajos();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

}