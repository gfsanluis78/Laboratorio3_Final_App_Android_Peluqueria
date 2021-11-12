package com.farias.laboratorio3_final_app_android_peluqueria.ui.a_home;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.farias.laboratorio3_final_app_android_peluqueria.modelo.Cliente;
import com.farias.laboratorio3_final_app_android_peluqueria.request.ApiClient;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends AndroidViewModel {

    private MutableLiveData<ArrayList<Cliente>> clientes;
    private Context context;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        this.clientes = new MutableLiveData<>();
        this.context = application.getApplicationContext();
    }

    public MutableLiveData<ArrayList<Cliente>> getClientes() { return clientes; }

    public void setClientes(){

        String token = ApiClient.leer(context, getClass().toString());

        Call<List<Cliente>> callClientes = ApiClient.getMyApiClient("Home VM").obtenerClientes(token);
        callClientes.enqueue(new Callback<List<Cliente>>() {
            @Override
            public void onResponse(Call<List<Cliente>> call, Response<List<Cliente>> response) {
                if (response.isSuccessful()){
                    clientes.setValue((ArrayList<Cliente>) response.body());
                    Log.d("mensaje view model", clientes.toString());
                }
                Log.d("mensaje HomeVM", response.message());

            }

            @Override
            public void onFailure(Call<List<Cliente>> call, Throwable t) {

            }
        });


    }

}