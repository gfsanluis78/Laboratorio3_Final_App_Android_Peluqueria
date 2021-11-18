package com.farias.laboratorio3_final_app_android_peluqueria.ui.i_trabajo;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.farias.laboratorio3_final_app_android_peluqueria.modelo.Empleado;
import com.farias.laboratorio3_final_app_android_peluqueria.modelo.TipoDeTrabajo;
import com.farias.laboratorio3_final_app_android_peluqueria.request.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrabajoDetalleViewModel extends AndroidViewModel {

    private MutableLiveData<TipoDeTrabajo> tipoDeTrabajoMutableLiveData;
    private MutableLiveData<ArrayList<Empleado>> listMutableLiveData;
    private Context context;
    private TipoDeTrabajo tipoDeTrabajo;


    public TrabajoDetalleViewModel(@NonNull Application application) {
        super(application);
        this.tipoDeTrabajoMutableLiveData = new MutableLiveData<>();
        this.listMutableLiveData = new MutableLiveData<>();
        this.tipoDeTrabajo = new TipoDeTrabajo();
     }

    public MutableLiveData<TipoDeTrabajo> getTipoDeTrabajoMutableLiveData() {
        return tipoDeTrabajoMutableLiveData;
    }

    public MutableLiveData<ArrayList<Empleado>> getListMutableLiveData() {
        return listMutableLiveData;
    }

    public void setTipoDeTrabajoMutableLiveData(Bundle bundle) {
        tipoDeTrabajo = (TipoDeTrabajo) bundle.getSerializable("trabajo");
        Log.d("mensaje", "Trabajo Detalle VM: llega el bundle con " + tipoDeTrabajo.toString());
        tipoDeTrabajoMutableLiveData.setValue(tipoDeTrabajo);


    }
    public void setEmpleadosByTipoTrabajo(TipoDeTrabajo tipoDeTrabajo) {

        String token = ApiClient.leer(context, getClass().toString());

        Call<List<Empleado>> callEmpleados = ApiClient.getMyApiClient("Trabajo Detalle VM").obtenerEmpleadosByTipoTrabajo(token, tipoDeTrabajo);
        callEmpleados.enqueue(new Callback<List<Empleado>>() {
            @Override
            public void onResponse(Call<List<Empleado>> call, Response<List<Empleado>> response) {
                if (response.isSuccessful()){
                    listMutableLiveData.setValue((ArrayList<Empleado>) response.body());
                    Log.d("mensaje", "Empleado Detalle VM/Respuesta isSuccessful "+ listMutableLiveData.toString());

                } else
                {
                    Toast.makeText(context, "No hay profesionales para mostrar" , Toast.LENGTH_SHORT).show();
                    Log.d("mensaje", "Empleado Detalle VM: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Empleado>> call, Throwable t) {
                Log.d("mensaje", "Cliente Detalle VM: " + t.getMessage());
            }
        });
    }

}