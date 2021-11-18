package com.farias.laboratorio3_final_app_android_peluqueria.ui.d_informes;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.farias.laboratorio3_final_app_android_peluqueria.modelo.DatosGrafico;
import com.farias.laboratorio3_final_app_android_peluqueria.request.ApiClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CantidadTurnosxEmpleadoViewModel extends AndroidViewModel {

    private Context context;
    private MutableLiveData<ArrayList<DatosGrafico>> datosGraficoMutableLiveData;
    DatosGrafico datosGrafico;


    public CantidadTurnosxEmpleadoViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
        this.datosGraficoMutableLiveData = new MutableLiveData<>();
        this.datosGrafico = new DatosGrafico();
    }

    public MutableLiveData<ArrayList<DatosGrafico>> getDatosGraficoMutableLiveData() {
        return datosGraficoMutableLiveData;
    }

    public void setDatosGraficoMutableLiveData() {

        String token = ApiClient.leer(context, getClass().toString().toUpperCase(Locale.ROOT));

        Call<List<DatosGrafico>> datosGraficoCall= ApiClient.getMyApiClient("CantidadTurnosEmpleado VM").obtenerDatosGrafico1(token);
        datosGraficoCall.enqueue(new Callback<List<DatosGrafico>>() {
            @Override
            public void onResponse(Call<List<DatosGrafico>> call, Response<List<DatosGrafico>> response) {
                if(response.isSuccessful()){

                    datosGraficoMutableLiveData.setValue((ArrayList<DatosGrafico>) response.body());
                }
                else{
                    Log.d("mensaje", "CantidadTurnosEmpleado VM: " + response.message());
                    //Toast.makeText(context, "No hay respuesta para mostrar", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<DatosGrafico>> call, Throwable t) {
                Toast.makeText(context, "Fallo en CantidadTurnosEmpleado VM/setDatosGrafico1 " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}