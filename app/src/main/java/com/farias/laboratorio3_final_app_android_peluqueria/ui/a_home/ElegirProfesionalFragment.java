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

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.farias.laboratorio3_final_app_android_peluqueria.R;
import com.farias.laboratorio3_final_app_android_peluqueria.databinding.FragmentElegirProfesionalBinding;
import com.farias.laboratorio3_final_app_android_peluqueria.modelo.Preparacion;
import com.farias.laboratorio3_final_app_android_peluqueria.ui.a_home.adapter.RecyclerAdapterHomeEmpleados;

public class ElegirProfesionalFragment extends Fragment {

    private ElegirProfesionalViewModel mViewModel;
    private RecyclerView recyclerView;
    private FragmentElegirProfesionalBinding binding;
    private Preparacion p;

    public static ElegirProfesionalFragment newInstance() {
        return new ElegirProfesionalFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentElegirProfesionalBinding.inflate(inflater, container,false);
        mViewModel = new ViewModelProvider(this).get(ElegirProfesionalViewModel.class);
        View view = binding.getRoot();
        recyclerView = view.findViewById(R.id.RV_profesionales);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),       // cambie el getContext por el getActivity y lo volvi atras
                LinearLayoutManager.VERTICAL,
                false);
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
                mViewModel.setTrabajoMutableLiveData(p.getTipoDeTrabajo());
                Glide.with(getContext())
                        .load(preparacion.getCliente().getUrlFoto())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)               // Llama la imagen remota y la carga en el cache,
                        .into(binding.IVFotoDelCliente);                          // despues la busca de ahi y es mas rapido
                //mViewModel.setEmpleadoMutableLiveData(p.getTipoDeTrabajo());
            }
        });

        mViewModel.getTrabajoMutableLiveData().observe( getViewLifecycleOwner(), trabajos -> {               // cambie el this.getActivity por un getViewLifeycleOwner porqu es fragment
            {

                RecyclerAdapterHomeEmpleados mAdapter = new RecyclerAdapterHomeEmpleados(getContext(),trabajos, p);
                recyclerView.setLayoutManager((linearLayoutManager));
                recyclerView.setAdapter(mAdapter);
            }
        });

        mViewModel.setPreparacion(getArguments());
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ElegirProfesionalViewModel.class);
        // TODO: Use the ViewModel
    }

}