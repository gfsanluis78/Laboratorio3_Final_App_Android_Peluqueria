package com.farias.laboratorio3_final_app_android_peluqueria.ui.c_cliente;

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
import com.farias.laboratorio3_final_app_android_peluqueria.modelo.Turno;
import com.farias.laboratorio3_final_app_android_peluqueria.request.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClienteTurnosViewModel extends AndroidViewModel {

    private MutableLiveData<ArrayList<Turno>> turnosMutableLiveData;
    private MutableLiveData<Cliente> clienteMutableLiveData;
    private Context context;
    private Cliente cliente;


    public ClienteTurnosViewModel(@NonNull Application application) {
        super(application);
        this.turnosMutableLiveData = new MutableLiveData<>();
        this.clienteMutableLiveData = new MutableLiveData<>();
        this.context = application.getApplicationContext();
        this.cliente = new Cliente();
    }

    public MutableLiveData<ArrayList<Turno>> getTurnosMutableLiveData() {
        if(turnosMutableLiveData==null){
            turnosMutableLiveData = new MutableLiveData<>();
        }
        return turnosMutableLiveData;
    }

    public MutableLiveData<Cliente> getClienteMutableLiveData() {
        return clienteMutableLiveData;
    }

    public void setClienteMutableLiveData(Bundle bundle) {
        cliente = (Cliente) bundle.getSerializable("cliente");
        Log.d("mensaje", "Cliente Turnos VM: llega el bundle con " + cliente.toString());
        clienteMutableLiveData.setValue(cliente);
        setTurnos(cliente);

    }


    public void setTurnos(Cliente c) {

        String token = ApiClient.leer(context, getClass().toString());

        Call<List<Turno>> callTurnos = ApiClient.getMyApiClient("Home VM").obtenerTurnosByCliente(token, c);
        callTurnos.enqueue(new Callback<List<Turno>>() {
            @Override
            public void onResponse(Call<List<Turno>> call, Response<List<Turno>> response) {
                if (response.isSuccessful()) {
                    turnosMutableLiveData.setValue((ArrayList<Turno>) response.body());
                    Log.d("mensaje", "Cliente Turnos VM/setTurnos" + turnosMutableLiveData.toString());
                }
                Log.d("mensaje", "OK Cliente Turnos VM/setTurnos: " + response.message());
                if(response.body().isEmpty()){
                    Toast.makeText(context, "No hay turnos para mostar", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Turno>> call, Throwable t) {
                Log.d("mensaje", "Fallo Cliente Turnos VM/setTurnos: " + t.getMessage());
            }
        });

    }
    }