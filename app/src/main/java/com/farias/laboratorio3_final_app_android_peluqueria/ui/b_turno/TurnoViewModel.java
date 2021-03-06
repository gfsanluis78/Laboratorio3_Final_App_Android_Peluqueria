package com.farias.laboratorio3_final_app_android_peluqueria.ui.b_turno;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.farias.laboratorio3_final_app_android_peluqueria.modelo.Cliente;
import com.farias.laboratorio3_final_app_android_peluqueria.modelo.Turno;
import com.farias.laboratorio3_final_app_android_peluqueria.request.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TurnoViewModel extends AndroidViewModel {

    private MutableLiveData<ArrayList<Turno>> turnosMutablelivedata;
    private Context context;

    public TurnoViewModel(@NonNull Application application) {
        super(application);
        this.turnosMutablelivedata = new MutableLiveData<>();
        this.context = application.getApplicationContext();
    }

    public MutableLiveData<ArrayList<Turno>> getTurnosMutablelivedata() {
        return turnosMutablelivedata;
    }

    public void setTurnosMutablelivedata(){

        String token = ApiClient.leer(context, getClass().toString());

        Call<List<Turno>> callTurnos = ApiClient.getMyApiClient("Home VM").obtenerTurnosFull(token);
        callTurnos.enqueue(new Callback<List<Turno>>() {
            @Override
            public void onResponse(Call<List<Turno>> call, Response<List<Turno>> response) {
                if (response.isSuccessful()){
                    turnosMutablelivedata.setValue((ArrayList<Turno>) response.body());
                    Log.d("mensaje view model", turnosMutablelivedata.toString());
                }
                else{
                    Toast.makeText(context, "Turnos no successful "+ response.message(), Toast.LENGTH_LONG).show();
                    Log.d("mensaje HomeVM", response.message());
                }


            }

            @Override
            public void onFailure(Call<List<Turno>> call, Throwable t) {
                Toast.makeText(context, "Fallo en busqueda" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });






    }
}

