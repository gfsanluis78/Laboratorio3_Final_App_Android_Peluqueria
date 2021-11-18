package com.farias.laboratorio3_final_app_android_peluqueria.ui.h_empleado;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.farias.laboratorio3_final_app_android_peluqueria.modelo.Cliente;
import com.farias.laboratorio3_final_app_android_peluqueria.modelo.Empleado;
import com.farias.laboratorio3_final_app_android_peluqueria.modelo.TipoDeTrabajo;
import com.farias.laboratorio3_final_app_android_peluqueria.request.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmpleadoDetalleViewModel extends AndroidViewModel {

    private MutableLiveData<Empleado> empleadoMutableLiveData;
    private MutableLiveData<ArrayList<TipoDeTrabajo>> tipoTrabajosMutableLiveData;
    private MutableLiveData<String> cantidadTurnosRealizados;
    private Context context;
    private Empleado empleado;


    public EmpleadoDetalleViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
        this.empleadoMutableLiveData = new MutableLiveData<>();
        this.tipoTrabajosMutableLiveData = new MutableLiveData<>();
        this.cantidadTurnosRealizados = new MutableLiveData<>();
        this.empleado = new Empleado();
    }

    public MutableLiveData<Empleado> getEmpleadoMutableLiveData() {
        return empleadoMutableLiveData;
    }

    public MutableLiveData<ArrayList<TipoDeTrabajo>> getTipoTrabajosMutableLiveData() {
        return tipoTrabajosMutableLiveData;
    }

    public MutableLiveData<String> getCantidadTurnosRealizados() {
        return cantidadTurnosRealizados;
    }

    public void setEmpleadoMutableLiveData(Bundle bundle) {
        empleado = (Empleado) bundle.getSerializable("empleado");
        Log.d("mensaje", "Empleado Detalle VM: llega el bundle con " + empleado.toString());
        empleadoMutableLiveData.setValue(empleado);
    }

    public void setCantidadTurnosRealizados(Empleado empleado){

        String token = ApiClient.leer(context, getClass().toString());

        Call<Integer> integerCall = ApiClient.getMyApiClient("Empleados detalle VM").obtenerCantidadTurnosBYEmpleado(token, empleado);
        integerCall.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (response.isSuccessful()) {
                    cantidadTurnosRealizados.setValue( response.body() + "");
                }else {
                    Log.d("mensaje", "Empleado Detalle VM: " + response.message());
                    //Toast.makeText(context, "No hay respuesta para mostrar", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Toast.makeText(context, "Fallo en Detallle VM/setCantidad " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }


    public void setTiposDeTrabajos(Empleado empleado) {

        String token = ApiClient.leer(context, getClass().toString());

        Call<List<TipoDeTrabajo>> callTipoTrabajos = ApiClient.getMyApiClient("Empleados Detalle VM").obtenerTipoTrabajoByEmpleado(token, empleado);
        callTipoTrabajos.enqueue(new Callback<List<TipoDeTrabajo>>() {
            @Override
            public void onResponse(Call<List<TipoDeTrabajo>> call, Response<List<TipoDeTrabajo>> response) {

                if (response.isSuccessful()) {
                        tipoTrabajosMutableLiveData.setValue((ArrayList<TipoDeTrabajo>) response.body());
                }else {
                        Log.d("mensaje", "Empleado Detalle VM: " + response.message());
                    Toast.makeText(context, "No hay trabajos para mostrar", Toast.LENGTH_SHORT).show();
                    }
                }

            @Override
            public void onFailure(Call<List<TipoDeTrabajo>> call, Throwable t) {
                Log.d("mensaje", "Cliente Detalle VM: " + t.getMessage());
                Toast.makeText(context, "Fallo comunicacion " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}