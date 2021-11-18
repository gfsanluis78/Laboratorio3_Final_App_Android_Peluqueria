package com.farias.laboratorio3_final_app_android_peluqueria.ui.i_trabajo;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.farias.laboratorio3_final_app_android_peluqueria.modelo.TipoDeTrabajo;
import com.farias.laboratorio3_final_app_android_peluqueria.request.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrabajoViewModel extends AndroidViewModel {

    private MutableLiveData<ArrayList<TipoDeTrabajo>> trabajos;
    private Context context;

    public TrabajoViewModel(@NonNull Application application) {
        super(application);
        this.trabajos = new MutableLiveData<>();
        this.context = application.getApplicationContext();

    }

    public MutableLiveData<ArrayList<TipoDeTrabajo>> getTrabajos() {
        return trabajos;
    }

    public void setTipoTrabajos() {

        String token = ApiClient.leer(context, getClass().toString());

        Call<List<TipoDeTrabajo>> callTipoTrabajos = ApiClient.getMyApiClient("Trabajos VM").obtenerTipoDeTrabajos(token);
        callTipoTrabajos.enqueue(new Callback<List<TipoDeTrabajo>>() {
            @Override
            public void onResponse(Call<List<TipoDeTrabajo>> call, Response<List<TipoDeTrabajo>> response) {
                if (response.isSuccessful()){
                    trabajos.setValue((ArrayList<TipoDeTrabajo>) response.body());
                    Log.d("mensaje view model", trabajos.toString());
                } else {
                    Log.d("mensaje", "Trabajos VM: " + response.message());
                    Toast.makeText(context, "Respuesta No isSuccessful " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<TipoDeTrabajo>> call, Throwable t) {
                Log.d("mensaje", "Trabajos VM: " + t.getMessage());
                Toast.makeText(context, "Fallo comunicacion " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}