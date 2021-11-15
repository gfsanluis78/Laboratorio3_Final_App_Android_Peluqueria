package com.farias.laboratorio3_final_app_android_peluqueria.ui.b_turno;

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
import android.widget.CalendarView;

import com.farias.laboratorio3_final_app_android_peluqueria.R;
import com.farias.laboratorio3_final_app_android_peluqueria.databinding.FragmentTurnoByFechaBinding;
import com.farias.laboratorio3_final_app_android_peluqueria.modelo.Fecha;
import com.farias.laboratorio3_final_app_android_peluqueria.ui.a_home.ElegirFechaViewModel;
import com.farias.laboratorio3_final_app_android_peluqueria.ui.a_home.adapter.RecyclerAdapterHomeEmpleados;
import com.farias.laboratorio3_final_app_android_peluqueria.ui.b_turno.adapter.RecyclerAdapterTurnoFechas;

import java.util.Calendar;

public class TurnoByFechaFragment extends Fragment {

    private TurnoByFechaViewModel mViewModel;
    private FragmentTurnoByFechaBinding binding;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private CalendarView calendarView;
    private Fecha fecha;

    public static TurnoByFechaFragment newInstance() {
        return new TurnoByFechaFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentTurnoByFechaBinding.inflate(inflater, container, false);
        mViewModel = new ViewModelProvider(this).get(TurnoByFechaViewModel.class);
        fecha = new Fecha();
        View view = binding.getRoot();
        recyclerView = binding.RVTurnosFecha;
        linearLayoutManager = new LinearLayoutManager(getContext(),       // cambie el getContext por el getActivity y lo volvi atras
                LinearLayoutManager.VERTICAL,
                false);


        calendarView = binding.calendarViewElejirFecha;

        long now = System.currentTimeMillis() - 1000;
        calendarView.setMinDate(now);
        calendarView.setMaxDate(now+(1000*60*60*24)*21); //After 7 Days from Now

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int año, int mes, int dia) {
                String date = (dia) + "/" + (mes + 1 ) + "/" + (año);
                Log.d("mensaje", "onSelectedDayChange: "+ date);
                //binding.TVFechaElegida.setVisibility(view.VISIBLE);
                //binding.TVFechaElegida.setText(date);
                fecha.setDate(date);
                Log.d("mensaje", "Elegir fecha Fragament: fecha "+ fecha);
                mViewModel.setFechaValida(fecha);
            }
        });

        mViewModel.getTurnosMutablelivedata().observe(getViewLifecycleOwner(), turnos -> {

            RecyclerAdapterTurnoFechas mAdapter = new RecyclerAdapterTurnoFechas(getContext(),turnos);
            recyclerView.setLayoutManager((linearLayoutManager));
            recyclerView.setAdapter(mAdapter);
        });


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(TurnoByFechaViewModel.class);
        // TODO: Use the ViewModel
    }

}