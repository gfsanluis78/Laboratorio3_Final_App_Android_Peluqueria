package com.farias.laboratorio3_final_app_android_peluqueria.ui.h_empleado;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.farias.laboratorio3_final_app_android_peluqueria.modelo.Empleado;
import com.farias.laboratorio3_final_app_android_peluqueria.request.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmpleadoViewModel extends AndroidViewModel {
    private MutableLiveData<ArrayList<Empleado>> empleadosMutableLiveData;
    private Context context;


    public EmpleadoViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
        this.empleadosMutableLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<ArrayList<Empleado>> getEmpleadosMutableLiveData() {
        return empleadosMutableLiveData;
    }

    public void setEmpleados() {
        String token = ApiClient.leer(context, getClass().toString());

        Call<List<Empleado>> callEmpleados = ApiClient.getMyApiClient("Empleados VM").obtenerEmpleados(token);
        callEmpleados.enqueue(new Callback<List<Empleado>>() {
            @Override
            public void onResponse(Call<List<Empleado>> call, Response<List<Empleado>> response) {
                if (response.isSuccessful()){
                    empleadosMutableLiveData.setValue((ArrayList<Empleado>) response.body());
                    Log.d("mensaje", "Empleado VM/Respuesta isSuccessful "+ empleadosMutableLiveData.toString());
                    //Toast.makeText(context, "Respuesta " + response.message(), Toast.LENGTH_SHORT).show();

                }
                else{
                    Log.d("mensaje", "Empleado VM: " + response.message());
                    Toast.makeText(context, "Respuesta no isSuccessful " + response.message(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<Empleado>> call, Throwable t) {
                Log.d("mensaje", "Cliente VM: " + t.getMessage());
                Toast.makeText(context, "Fallo comunicacion " + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}