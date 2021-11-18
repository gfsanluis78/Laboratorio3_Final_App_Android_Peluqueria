package com.farias.laboratorio3_final_app_android_peluqueria.ui.d_informes;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.farias.laboratorio3_final_app_android_peluqueria.R;

public class Dia_TurnoByProfesionalFragment extends Fragment {

    private DiaTurnoByProfesionalViewModel mViewModel;

    public static Dia_TurnoByProfesionalFragment newInstance() {
        return new Dia_TurnoByProfesionalFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dia_turno_by_profesional, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(DiaTurnoByProfesionalViewModel.class);
        // TODO: Use the ViewModel
    }

}