package com.farias.laboratorio3_final_app_android_peluqueria.ui.c_cliente;

import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.farias.laboratorio3_final_app_android_peluqueria.R;
import com.farias.laboratorio3_final_app_android_peluqueria.databinding.FragmentClienteDetalleBinding;
import com.farias.laboratorio3_final_app_android_peluqueria.modelo.Cliente;
import com.farias.laboratorio3_final_app_android_peluqueria.modelo.Turno;

public class ClienteDetalleFragment extends Fragment {

    private ClienteDetalleViewModel mViewModel;
    private FragmentClienteDetalleBinding binding;
    private Cliente c;
    private Turno t;


    public static ClienteDetalleFragment newInstance() {
        return new ClienteDetalleFragment();
    }

    @SuppressLint("WrongConstant")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(ClienteDetalleViewModel.class);
        binding = FragmentClienteDetalleBinding.inflate(inflater,container,false);
        View view = binding.getRoot();

//        mViewModel.getClienteMutableLiveData().observe(getViewLifecycleOwner(), cliente -> {
//            mViewModel.setUltimoTurno(cliente);
//        });

        mViewModel.getDetallesClienteMutableLiveData().observe(getViewLifecycleOwner(), detallesCliente -> {

            t = detallesCliente.getTurno();
            c = detallesCliente.getCliente();

            binding.TVTitulo.setText(c.getNombreCompleto());
            binding.TVClienteDni.setText(c.getDni());
            binding.TVClienteNombre.setText(c.getNombre());
            binding.TVClienteApellido.setText(c.getApellido());
            binding.TVClienteEmail.setText(c.getEmail());
            binding.TVClienteTelefono.setText(c.getTelefono());
            Glide.with(getContext())
                    .load(detallesCliente.getCliente().getUrlFoto())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)               // Llama la imagen remota y la carga en el cache,
                    .into(binding.IVFoto);                          // despues la busca de ahi y es mas rapido

            if(t.getIdTurno()!= 0){
                binding.TVUltimaVisita.setText("Fecha: " + t.getFecha());
                binding.TVBloque.setText("Horario: " + t.getBloque().getDesdeHasta());
                binding.TVProfesional.setText("Profesional: " + t.getTrabajo().getEmpleado().getNombreCompleto());
                binding.TVCosto.setText("Costo: " + t.getCosto());
                binding.TVTrabajo.setText("Trabajo realizado: " + t.getTrabajo().getTipoDeTrabajo().getNombre());
                binding.BTVerTodosDelCliente.setVisibility(0);
                binding.BTVerTodosDelCliente.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("cliente", c);
                        Log.d("mensaje", "Cliente Fragment/BT ver todos: Envio el bunlde con " +c);
                        Navigation.findNavController(view).navigate(R.id.clienteTurnosFragment, bundle);
                    }
                });

            }else {
                binding.TVUltimaVisita.setText("No tiene registrado turnos");
            }
        });

        mViewModel.setClienteMutableLiveData(getArguments());
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}