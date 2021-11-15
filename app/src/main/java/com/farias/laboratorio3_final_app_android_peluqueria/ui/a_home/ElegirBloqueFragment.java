package com.farias.laboratorio3_final_app_android_peluqueria.ui.a_home;

import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.farias.laboratorio3_final_app_android_peluqueria.R;
import com.farias.laboratorio3_final_app_android_peluqueria.databinding.FragmentElegirBloqueBinding;
import com.farias.laboratorio3_final_app_android_peluqueria.modelo.Bloque;
import com.farias.laboratorio3_final_app_android_peluqueria.modelo.Preparacion;

public class ElegirBloqueFragment extends Fragment {

    private ElegirBloqueViewModel mViewModel;
    private FragmentElegirBloqueBinding binding;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private Preparacion p;
    private Bloque bl1,bl2,bl3,bl4,bl5,bl6,bl7,bl8, bloque;
    private Button a,b,c,d,e,f,g,h;

    public static ElegirBloqueFragment newInstance() {
        return new ElegirBloqueFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentElegirBloqueBinding.inflate(inflater, container, false);
        mViewModel = new ViewModelProvider(this).get(ElegirBloqueViewModel.class);
        bloque = new Bloque();
        View view = binding.getRoot();
        recyclerView = binding.RVBloques;
        linearLayoutManager = new LinearLayoutManager(getContext(),       // cambie el getContext por el getActivity y lo volvi atras
                LinearLayoutManager.VERTICAL,
                false);
        a = binding.BTDe9a10;
        b = binding.BTDe10a11;
        c = binding.BTDe11a12;
        d = binding.BTDe12a13;
        e = binding.BT14a15;
        f = binding.BT15a16;
        g = binding.BT16a17;
        h = binding.BT17a18;



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
                Glide.with(getContext())
                        .load(preparacion.getCliente().getUrlFoto())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)               // Llama la imagen remota y la carga en el cache,
                        .into(binding.IVFotoDelCliente);                          // despues la busca de ahi y es mas rapido
                binding.TVProfesional.setText(preparacion.getEmpleado().getNombreCompleto());
                Glide.with(getContext())
                        .load(preparacion.getEmpleado().getUrlFoto())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)               // Llama la imagen remota y la carga en el cache,
                        .into(binding.IVFotoProfesional);
                binding.TVFecha.setText(p.getFecha().getDate());
                mViewModel.setBloquesMutableLiveData(p);

            }
        });

        mViewModel.getBloqueMutableLiveData().observe( getViewLifecycleOwner(), bloques -> {               // cambie el this.getActivity por un getViewLifeycleOwner porqu es fragment

                Log.d("mensaje", "ElegirBloqueFragment: "+ bloques.toString());
                for (Bloque bloque:bloques) {
                    if(bloque.getOrden() == 1) {
                        a.setEnabled(false);
                    }
                    if(bloque.getOrden() == 2) {
                        b.setEnabled(false);
                    }
                    if(bloque.getOrden() == 3) {
                        c.setEnabled(false);
                    }
                    if(bloque.getOrden() == 4) {
                        d.setEnabled(false);
                    }
                    if(bloque.getOrden() == 5) {
                        e.setEnabled(false);
                    }
                    if(bloque.getOrden() == 6) {
                        f.setEnabled(false);
                    }
                    if(bloque.getOrden() == 7) {
                        g.setEnabled(false);

                    }
                    if(bloque.getOrden() == 8) {
                        h.setEnabled(false);
                    }
                }

                // se retira recycler view
                /*
                RecyclerAdapterHomeBloques mAdapter = new RecyclerAdapterHomeBloques(getContext(),bloques, p);
                recyclerView.setLayoutManager((linearLayoutManager));
                recyclerView.setAdapter(mAdapter);
                */
            });

        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                p.setBloque(new Bloque(4, "09:00", "10:00"));
                hacerIntent(view);
            }
        });

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                p.setBloque(new Bloque(5, "10:00", "11:00"));
                hacerIntent(view);
            }
        });

        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                p.setBloque(new Bloque(6, "11:00", "12:00"));
                hacerIntent(view);
            }
        });

        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                p.setBloque(new Bloque(7, "12:00", "13:00"));
                hacerIntent(view);
            }
        });

        e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                p.setBloque(new Bloque(8, "14:00", "15:00"));
                hacerIntent(view);
            }
        });

        f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                p.setBloque(new Bloque(9, "15:00", "16:00"));
                hacerIntent(view);
            }
        });

        g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                p.setBloque(new Bloque(1002, "16:00", "17:00"));
                hacerIntent(view);
            }
        });
        h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                p.setBloque(new Bloque(2002, "17:00", "18:00"));
                hacerIntent(view);
            }
        });

        mViewModel.setPreparacion(getArguments());
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ElegirBloqueViewModel.class);
        // TODO: Use the ViewModel
    }

    public void hacerIntent(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setTitle("Confirmacion de turno");
        builder.setMessage("Seguro desea el crear turno?");
        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Bundle bundle = new Bundle();                                           // Instancio el bundle a enviar
                //Preparacion preparacion = new Preparacion();                            // Comienzo a preparar el Bloque
                bundle.putSerializable("preparacion", p);                           // Meto la preparacion en el bundle
                Log.d("mensaje ","hacer intent Preparacion: " + p.toString());
                Navigation.findNavController(view).navigate(R.id.confirmacionTurnoFragment,bundle);       // Meto el bundle en el navigation
            }
        });
        // y si no quiero
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        //Inicializar el alert dialog
        AlertDialog alertDialog = builder.create();
        // Mostrar el alert
        alertDialog.show();
    }

}