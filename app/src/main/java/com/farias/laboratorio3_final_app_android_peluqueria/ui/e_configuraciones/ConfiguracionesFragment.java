package com.farias.laboratorio3_final_app_android_peluqueria.ui.e_configuraciones;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.farias.laboratorio3_final_app_android_peluqueria.R;

public class ConfiguracionesFragment extends Fragment {

    private ConfiguracionesViewModel mViewModel;

    public static ConfiguracionesFragment newInstance() {
        return new ConfiguracionesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_configuraciones, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ConfiguracionesViewModel.class);
        // TODO: Use the ViewModel
    }

}