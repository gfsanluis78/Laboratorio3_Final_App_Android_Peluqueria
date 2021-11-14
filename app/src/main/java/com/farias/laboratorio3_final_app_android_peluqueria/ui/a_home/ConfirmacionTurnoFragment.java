package com.farias.laboratorio3_final_app_android_peluqueria.ui.a_home;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.farias.laboratorio3_final_app_android_peluqueria.R;

public class ConfirmacionTurnoFragment extends Fragment {

    private ConfirmacionTurnoViewModel mViewModel;

    public static ConfirmacionTurnoFragment newInstance() {
        return new ConfirmacionTurnoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_confirmacion_turno, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ConfirmacionTurnoViewModel.class);
        // TODO: Use the ViewModel
    }

}