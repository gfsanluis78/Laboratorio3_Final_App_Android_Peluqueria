package com.farias.laboratorio3_final_app_android_peluqueria.ui.b_turno;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.farias.laboratorio3_final_app_android_peluqueria.databinding.FragmentTurnoBinding;

public class TurnoFragment extends Fragment {

    private TurnoViewModel turnoViewModel;
private FragmentTurnoBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        turnoViewModel =
                new ViewModelProvider(this).get(TurnoViewModel.class);

    binding = FragmentTurnoBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

        final TextView textView = binding.textTurno;
        turnoViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}