package com.farias.laboratorio3_final_app_android_peluqueria.ui.b_turno;

import android.os.Bundle;
import android.util.Log;
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

import com.farias.laboratorio3_final_app_android_peluqueria.databinding.FragmentHomeBinding;
import com.farias.laboratorio3_final_app_android_peluqueria.databinding.FragmentTurnoBinding;
import com.farias.laboratorio3_final_app_android_peluqueria.ui.a_home.adapter.RecyclerAdapterHomeClientes;
import com.farias.laboratorio3_final_app_android_peluqueria.ui.b_turno.adapter.RecyclerAdapterTurnoTodos;

public class TurnoFragment extends Fragment {

    private TurnoViewModel turnoViewModel;
    private FragmentTurnoBinding binding;
    private RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        turnoViewModel =
                new ViewModelProvider(this).get(TurnoViewModel.class);

        binding = FragmentTurnoBinding.inflate(inflater, container, false);
        recyclerView = binding.RVTodosLosTurnos;

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);


        View root = binding.getRoot();

        turnoViewModel.getTurnosMutablelivedata().observe(getViewLifecycleOwner(), turnos ->{
            Log.d("mensaje", "llegan los tunos " + turnos );
            RecyclerAdapterTurnoTodos mAdapter = new RecyclerAdapterTurnoTodos(getContext(), turnos);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(mAdapter);
        });


        turnoViewModel.setTurnosMutablelivedata();

        //final TextView textView = binding.textTurno;
//        turnoViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                //textView.setText(s);
//            }
//        });
        return root;
    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}