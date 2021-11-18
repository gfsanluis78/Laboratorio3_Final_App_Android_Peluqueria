package com.farias.laboratorio3_final_app_android_peluqueria.ui.a_home;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.farias.laboratorio3_final_app_android_peluqueria.R;
import com.farias.laboratorio3_final_app_android_peluqueria.databinding.FragmentElegirTrabajoBinding;
import com.farias.laboratorio3_final_app_android_peluqueria.modelo.Preparacion;
import com.farias.laboratorio3_final_app_android_peluqueria.ui.a_home.adapter.RecyclerAdapterHomeTrabajos;

public class ElegirTrabajoFragment extends Fragment {

    private RecyclerView recyclerView;
    private ElegirTrabajoViewModel mViewModel;
    private FragmentElegirTrabajoBinding binding;
    private Preparacion p;
    private View view;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerAdapterHomeTrabajos mAdapter;
    private RecyclerView.LayoutManager layoutManager;


    public static ElegirTrabajoFragment newInstance() {
        return new ElegirTrabajoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentElegirTrabajoBinding.inflate(inflater, container,false);
        mViewModel = new ViewModelProvider(this).get(ElegirTrabajoViewModel.class);
        view = binding.getRoot();

        recyclerView = view.findViewById(R.id.RV_trabajos);

        linearLayoutManager = new LinearLayoutManager(getContext(),       // cambie el getContext por el getActivity y lo volvi atras
                LinearLayoutManager.VERTICAL,
                false);
        layoutManager = new GridLayoutManager(getContext(), 2);


        mViewModel.getPreparacionMutableLiveData().observe( getViewLifecycleOwner(), preparacion -> {
            if(preparacion != null){
                p = preparacion;
                binding.TVNombre.setText("Nombre: " + preparacion.getCliente().getNombreCompleto());
                binding.TVDni.setText("Dni: " + preparacion.getCliente().getDni());
                binding.TVTelefono.setText("Telefono: " + preparacion.getCliente().getTelefono());
                Glide.with(getContext())
                        .load(preparacion.getCliente().getUrlFoto())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)               // Llama la imagen remota y la carga en el cache,
                        .into(binding.IVFotoDelCliente);                          // despues la busca de ahi y es mas rapido
            }
        });

        mViewModel.getTipoDeTrabajoMutableLiveData().observe( getViewLifecycleOwner(), trabajos -> {               // cambie el this.getActivity por un getViewLifeycleOwner porqu es fragment
            {
                mAdapter = new RecyclerAdapterHomeTrabajos(getContext(),trabajos, p);
                recyclerView.setLayoutManager(layoutManager);
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