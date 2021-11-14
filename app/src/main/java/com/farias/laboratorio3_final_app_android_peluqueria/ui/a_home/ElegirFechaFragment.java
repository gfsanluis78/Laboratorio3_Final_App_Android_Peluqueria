package com.farias.laboratorio3_final_app_android_peluqueria.ui.a_home;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.farias.laboratorio3_final_app_android_peluqueria.R;
import com.farias.laboratorio3_final_app_android_peluqueria.databinding.FragmentElegirFechaBinding;
import com.farias.laboratorio3_final_app_android_peluqueria.modelo.Fecha;
import com.farias.laboratorio3_final_app_android_peluqueria.modelo.Preparacion;

import java.util.Calendar;
import java.util.Date;

public class ElegirFechaFragment extends Fragment {

    private ElegirFechaViewModel mViewModel;
    private FragmentElegirFechaBinding binding;
    private Preparacion p;
    private CalendarView calendarView;
    private Calendar hoy = null;
    private Fecha fecha;

    public static ElegirFechaFragment newInstance() {
        return new ElegirFechaFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentElegirFechaBinding.inflate(inflater, container, false);
        mViewModel = new ViewModelProvider(this).get(ElegirFechaViewModel.class);
        fecha = new Fecha();
        View view = binding.getRoot();

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


            }
        });

        calendarView = binding.calendarViewElejirFecha;
        hoy = Calendar.getInstance();

        long now = System.currentTimeMillis() - 1000;
        calendarView.setMinDate(now);
        calendarView.setMaxDate(now+(1000*60*60*24)*21); //After 7 Days from Now

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int año, int mes, int dia) {
                String date = (dia) + "/" + (mes + 1 ) + "/" + (año);
                Log.d("mensaje", "onSelectedDayChange: "+ date);
                binding.TVFechaElegida.setVisibility(view.VISIBLE);
                binding.TVFechaElegida.setText(date);
                fecha.setDate(date);
                p.setFecha(fecha);
                Log.d("mensaje", "Elegir fecha Fragament: nuevo preparacion "+ p);
                mViewModel.setFechaValida();
            }
        });
        //calendarView.setDate(now);


        mViewModel.getEsFechaValida().observe( getViewLifecycleOwner(), esValida -> {
            if(esValida){
                binding.BTConsultarDisponibles.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Bundle bundle = new Bundle();                                           // Instancio el bundle a enviar
                        //Preparacion preparacion = new Preparacion();                            // Comienzo a preparar el turno
                        bundle.putSerializable("preparacion", p);                           // Meto la preparacion en el bundle
                        Log.d("mensaje ","OnBindViewHolder Preparacion: " + p.toString());
                        Navigation.findNavController(view).navigate(R.id.elegirTurnoFragment,bundle);       // Meto el bundle en el navigation
                    }
                });

                binding.BTConsultarDisponibles.setEnabled(true);
            }
        } );

        long dateLong = calendarView.getDate();
        Log.d("mensaje", "La fecha inicial es "+ dateLong + " y " + hoy.getTime());


        mViewModel.setPreparacion(getArguments());
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ElegirFechaViewModel.class);
        // TODO: Use the ViewModel
    }

}