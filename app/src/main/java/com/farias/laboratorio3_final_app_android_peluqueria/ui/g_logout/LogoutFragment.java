package com.farias.laboratorio3_final_app_android_peluqueria.ui.g_logout;

import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.farias.laboratorio3_final_app_android_peluqueria.R;
import com.farias.laboratorio3_final_app_android_peluqueria.databinding.FragmentLogoutBinding;
import com.farias.laboratorio3_final_app_android_peluqueria.ui.login.LoginActivity;

public class LogoutFragment extends Fragment {

    private LogoutViewModel mViewModel;
    private Button logout;
    private FragmentLogoutBinding binding;
    private Context context = getContext();

    public static LogoutFragment newInstance() {
        return new LogoutFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentLogoutBinding.inflate(inflater,container,false);
        View view = binding.getRoot();

        this.iniciarControl(view);


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Log out");
                builder.setMessage("Seguro desea desloguarse");
                builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mViewModel.borrarLogin();
                        startActivity(new Intent(getActivity(), LoginActivity.class));
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
        });

        return view;
    }

    private void iniciarControl(View view) {
        logout = view.findViewById(R.id.button_logout);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(LogoutViewModel.class);
        //
    }

}