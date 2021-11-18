package com.farias.laboratorio3_final_app_android_peluqueria.ui.d_informes;

import androidx.lifecycle.ViewModelProvider;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.farias.laboratorio3_final_app_android_peluqueria.R;
import com.farias.laboratorio3_final_app_android_peluqueria.databinding.FragmentCantidadTurnosxEmpleadoBinding;
import com.farias.laboratorio3_final_app_android_peluqueria.modelo.DatosGrafico;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

public class CantidadTurnosxEmpleadoFragment extends Fragment {

    private CantidadTurnosxEmpleadoViewModel mViewModel;
    private FragmentCantidadTurnosxEmpleadoBinding binding;
    private View view;
    private GraphView graphViewBarra;
    private BarGraphSeries<DataPoint> seriesBarra;

    private int contador;

    public static CantidadTurnosxEmpleadoFragment newInstance() {
        return new CantidadTurnosxEmpleadoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mViewModel = new ViewModelProvider(this).get(CantidadTurnosxEmpleadoViewModel.class);
        binding = FragmentCantidadTurnosxEmpleadoBinding.inflate(inflater,container,false);
        view = binding.getRoot();
        graphViewBarra = binding.graphBarra1;

        mViewModel.getDatosGraficoMutableLiveData().observe(getViewLifecycleOwner(), datosGrafico -> {

            contador = 0;
            DataPoint[] dataSet = new DataPoint[datosGrafico.size()];
            for (DatosGrafico dato : datosGrafico
            ) {
                dataSet[contador] = new DataPoint(contador+1,dato.getCantidad());
                Log.d("mensaje", "Los datos del Grafico " + contador + " " + dato.toString());
                contador++;
            }
            seriesBarra = new BarGraphSeries<>(dataSet);
            graphViewBarra.addSeries(seriesBarra);

            graphViewBarra.setTitle("Turnos x empleado");
            seriesBarra.setValueDependentColor(new ValueDependentColor<DataPoint>() {
                @Override
                public int get(DataPoint data) {
                    return Color.rgb((int)data.getX()*255/4,(int) data.getY()*255/6,100);
                }
            });

            seriesBarra.setSpacing(20);
            //seriesBarra.setDrawValuesOnTop(true);
            seriesBarra.setValuesOnTopColor(Color.BLUE);
            seriesBarra.setAnimated(true);

        });

        mViewModel.setDatosGraficoMutableLiveData();
        return view;
    }

}