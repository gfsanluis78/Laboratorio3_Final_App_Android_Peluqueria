package com.farias.laboratorio3_final_app_android_peluqueria.ui.a_home;

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
import com.farias.laboratorio3_final_app_android_peluqueria.databinding.FragmentElegirTrabajoBinding;
import com.farias.laboratorio3_final_app_android_peluqueria.modelo.Cliente;
import com.farias.laboratorio3_final_app_android_peluqueria.modelo.Preparacion;
import com.farias.laboratorio3_final_app_android_peluqueria.modelo.Trabajo;
import com.farias.laboratorio3_final_app_android_peluqueria.ui.a_home.adapter.RecyclerAdapterHomeTrabajos;

public class ElegirTrabajoFragment extends Fragment {

    private RecyclerView recyclerView;
    private ElegirTrabajoViewModel mViewModel;
    private FragmentElegirTrabajoBinding binding;
    private Cliente c;
    private Trabajo t;
    private Preparacion p;

    public static ElegirTrabajoFragment newInstance() {
        return new ElegirTrabajoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentElegirTrabajoBinding.inflate(inflater, container,false);
        mViewModel = new ViewModelProvider(this).get(ElegirTrabajoViewModel.class);
        View view = binding.getRoot();
        recyclerView = view.findViewById(R.id.RV_trabajos);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),       // cambie el getContext por el getActivity y lo volvi atras
                LinearLayoutManager.VERTICAL,
                false);
        mViewModel.getPreparacionMutableLiveData().observe( getViewLifecycleOwner(), preparacion -> {
            if(preparacion != null){
                p = preparacion;
                binding.TVNombre.setText("Nombre: " + preparacion.getCliente().getNombreCompleto());
                binding.TVDni.setText("Dni: " + preparacion.getCliente().getDni());
                binding.TVTelefono.setText("Telefono: " + preparacion.getCliente().getTelefono());
            }
        });

        mViewModel.getTipoDeTrabajoMutableLiveData().observe( getViewLifecycleOwner(), trabajos -> {               // cambie el this.getActivity por un getViewLifeycleOwner porqu es fragment
            {

                RecyclerAdapterHomeTrabajos mAdapter = new RecyclerAdapterHomeTrabajos(getContext(),trabajos);
                recyclerView.setLayoutManager((linearLayoutManager));
                recyclerView.setAdapter(mAdapter);
            }
        });
        mViewModel.setTipoDeTrabajoMutableLiveData();
        mViewModel.setPreparacion(getArguments());
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ElegirTrabajoViewModel.class);
        // TODO: Use the ViewModel
    }

}