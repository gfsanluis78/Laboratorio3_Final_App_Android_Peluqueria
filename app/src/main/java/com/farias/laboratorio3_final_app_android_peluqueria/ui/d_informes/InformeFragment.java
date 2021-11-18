package com.farias.laboratorio3_final_app_android_peluqueria.ui.d_informes;

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

import com.farias.laboratorio3_final_app_android_peluqueria.R;
import com.farias.laboratorio3_final_app_android_peluqueria.databinding.FragmentInformeBinding;

public class InformeFragment extends Fragment {

    private InformeViewModel mViewModel;
    private FragmentInformeBinding binding;
    private View view;


    public static InformeFragment newInstance() {
        return new InformeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(InformeViewModel.class);
        binding = FragmentInformeBinding.inflate(inflater, container, false);
        view = binding.getRoot();


        binding.BTInfTurnoXProfesional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("mensaje", "click en boton");
                Navigation.findNavController(view).navigate(R.id.cantidadTurnosxEmpleadoFragment);
            }
        });


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // TODO: Use the ViewModel
    }

}