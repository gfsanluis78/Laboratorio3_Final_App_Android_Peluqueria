package com.farias.laboratorio3_final_app_android_peluqueria.ui.c_cliente;

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
import com.farias.laboratorio3_final_app_android_peluqueria.request.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClienteViewModel extends AndroidViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<ArrayList<Cliente>> clientes;
    private Context context;

    public ClienteViewModel(@NonNull Application application) {
        super(application);
        this.clientes = new MutableLiveData<>();
        this.context = application.getApplicationContext();

        mText = new MutableLiveData<>();
        mText.setValue("This is cliente fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public MutableLiveData<ArrayList<Cliente>> getClientes() {
        return clientes;
    }

    public void setClientes() {
        String token = ApiClient.leer(context, getClass().toString());

        Call<List<Cliente>> callClientes = ApiClient.getMyApiClient("Home VM").obtenerClientes(token);
        callClientes.enqueue(new Callback<List<Cliente>>() {
            @Override
            public void onResponse(Call<List<Cliente>> call, Response<List<Cliente>> response) {
                if (response.isSuccessful()){
                    clientes.setValue((ArrayList<Cliente>) response.body());
                    Log.d("mensaje view model", clientes.toString());
                }
                Log.d("mensaje", "Cliente VM: " + response.message());
            }

            @Override
            public void onFailure(Call<List<Cliente>> call, Throwable t) {
                Log.d("mensaje", "Cliente VM: " + t.getMessage());
            }
        });
    }
}