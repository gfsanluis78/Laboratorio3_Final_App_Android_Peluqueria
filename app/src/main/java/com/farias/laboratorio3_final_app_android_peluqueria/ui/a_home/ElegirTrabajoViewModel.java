package com.farias.laboratorio3_final_app_android_peluqueria.ui.a_home;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.farias.laboratorio3_final_app_android_peluqueria.modelo.Preparacion;
import com.farias.laboratorio3_final_app_android_peluqueria.modelo.TipoDeTrabajo;
import com.farias.laboratorio3_final_app_android_peluqueria.modelo.Trabajo;
import com.farias.laboratorio3_final_app_android_peluqueria.request.ApiClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ElegirTrabajoViewModel extends AndroidViewModel {

    private Context context;
    private MutableLiveData<Preparacion> preparacionMutableLiveData;
    private MutableLiveData<ArrayList<TipoDeTrabajo>> tipoDeTrabajoMutableLiveData;
    private Preparacion preparacion;

    public ElegirTrabajoViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        this.tipoDeTrabajoMutableLiveData = new MutableLiveData<>();
        this.preparacionMutableLiveData = new MutableLiveData<>();
        this.preparacion = new Preparacion();
    }


    public MutableLiveData<Preparacion> getPreparacionMutableLiveData() {
        return preparacionMutableLiveData;
    }

    public MutableLiveData<ArrayList<TipoDeTrabajo>> getTipoDeTrabajoMutableLiveData() {
        return tipoDeTrabajoMutableLiveData;
    }

    public void setPreparacion(Bundle arguments) {
        preparacion = (Preparacion) arguments.getSerializable("preparacion");
        Log.d("mensaje ElegirTrabajovm", "El bundle trae " + preparacion.getCliente().toString());
        preparacionMutableLiveData.setValue(preparacion);
    }

    public void setTipoDeTrabajoMutableLiveData(){


        String token = ApiClient.leer(context, getClass().toString().toUpperCase(Locale.ROOT));

        Call<List<TipoDeTrabajo>> callInmuebles = ApiClient.getMyApiClient("Inmueble VM").obtenerTipoDeTrabajos(token);
        callInmuebles.enqueue(new Callback<List<TipoDeTrabajo>>() {
            @Override
            public void onResponse(Call<List<TipoDeTrabajo>> call, Response<List<TipoDeTrabajo>> response) {
                if (response.isSuccessful()){
                    tipoDeTrabajoMutableLiveData.setValue((ArrayList<TipoDeTrabajo>) response.body());
                    if(response.body().isEmpty()){
                        Toast.makeText(context, "No hay trabajos para mostrar", Toast.LENGTH_SHORT).show();
                    }
                    Log.d("mensaje viewmodel", "Response isSuccessful: "+ response.body().toString());
                }
                else{
                    Log.d("mensaje", "Fallo en VM elegir trabajo: " + response.message());
                }


            }

            @Override
            public void onFailure(Call<List<TipoDeTrabajo>> call, Throwable t) {
                Log.d("mensaje", "Fallo en view model elegir tipo de trabajo: " + t.getMessage());
            }
        });
    }





}