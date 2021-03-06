package com.farias.laboratorio3_final_app_android_peluqueria.ui.a_home;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.farias.laboratorio3_final_app_android_peluqueria.modelo.ConsultaByTipoTrabajo;
import com.farias.laboratorio3_final_app_android_peluqueria.modelo.Empleado;
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

public class ElegirProfesionalViewModel extends AndroidViewModel {

    private Context context;
    private MutableLiveData<Preparacion> preparacionMutableLiveData;
    private MutableLiveData<ArrayList<Trabajo>> trabajoMutableLiveData;
    private Preparacion preparacion;
    private ConsultaByTipoTrabajo consultaByTipoTrabajo;

    // constructor
    public ElegirProfesionalViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        this.preparacionMutableLiveData = new MutableLiveData<>();
        this.trabajoMutableLiveData = new MutableLiveData<>();
        this.preparacion = new Preparacion();
        this.consultaByTipoTrabajo = new ConsultaByTipoTrabajo();
    }

    // metodos
    public MutableLiveData<Preparacion> getPreparacionMutableLiveData() {
        return preparacionMutableLiveData;
    }

    public MutableLiveData<ArrayList<Trabajo>> getTrabajoMutableLiveData() {
        return trabajoMutableLiveData;
    }

    public void setPreparacion(Bundle arguments) {
        preparacion = (Preparacion) arguments.getSerializable("preparacion");
        Log.d("mensaje", "View Model Elegir proefciona. El bundle trae " + preparacion.getCliente().toString() + ". Tipo de trabajo: " + preparacion.getTipoDeTrabajo().getNombre());
        preparacionMutableLiveData.setValue(preparacion);
    }

    public void setTrabajoMutableLiveData(TipoDeTrabajo tipoDeTrabajo){

        consultaByTipoTrabajo.setIdTipoTrabajo(tipoDeTrabajo.getIdTipodeTrabajo());

        String token = ApiClient.leer(context, getClass().toString().toUpperCase(Locale.ROOT));

        Call<List<Trabajo>> callTrabajo = ApiClient.getMyApiClient("Inmueble VM").obtenerTrabajosByTipoTrabajo(token, consultaByTipoTrabajo);
        callTrabajo.enqueue(new Callback<List<Trabajo>>() {
            @Override
            public void onResponse(Call<List<Trabajo>> call, Response<List<Trabajo>> response) {
                if (response.isSuccessful()){
                    trabajoMutableLiveData.setValue((ArrayList<Trabajo>) response.body());
                    if(response.body().isEmpty()){
                        Toast.makeText(context, "No hay profesionales para mostrar", Toast.LENGTH_SHORT).show();
                    }
                    Log.d("mensaje viewmodel", "Response isSuccessful: "+ response.body().toString());
                }
                else{
                    Log.d("mensaje", "Fallo en VM elegir trabajo: " + response.message());
                }


            }

            @Override
            public void onFailure(Call<List<Trabajo>> call, Throwable t) {
                Log.d("mensaje", "Fallo en view model elegir tipo de trabajo: " + t.getMessage());
            }
        });
    }

}