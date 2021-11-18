package com.farias.laboratorio3_final_app_android_peluqueria.ui.c_cliente;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.farias.laboratorio3_final_app_android_peluqueria.modelo.Cliente;
import com.farias.laboratorio3_final_app_android_peluqueria.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClienteCrearViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Cliente> clienteMutableLiveData;
    private MutableLiveData<Button> buttonMutableLiveData;

    public ClienteCrearViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }


    public MutableLiveData<Cliente> getClienteMutableLiveData() {
        return clienteMutableLiveData;
    }

    public MutableLiveData<Button> getButtonMutableLiveData() {
        return buttonMutableLiveData;
    }

    public void setButtonMutableLiveData(MutableLiveData<Button> buttonMutableLiveData) {

    }

    public void guardarCliente(Cliente cliente){
        String token = ApiClient.leer(context,"ClienteCrearViewModel.guardarCliente" );

        Call<Cliente> clienteCall = ApiClient.getMyApiClient("ClienteCrearViewModel.guardarCliente").crearCliente(token, cliente);
        clienteCall.enqueue(new Callback<Cliente>() {
            @Override
            public void onResponse(Call<Cliente> call, Response<Cliente> response) {
                if (response.isSuccessful()){
                    Cliente c = (Cliente) response.body();
                    Toast.makeText(context, "Nuevo cliente creado con exito con el id "+ c.getIdCliente() , Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(context, "Fallo en ClienteCrearViewModel.guardarCliente: " + response.message(), Toast.LENGTH_LONG).show();
                    Log.d("mensaje", "Fallo en ClienteCrearViewModel.guardarCliente: " +response.message());
                }

            }

            @Override
            public void onFailure(Call<Cliente> call, Throwable t) {
                Toast.makeText(context, "Fallo en comunicacion con BD en ClienteCrearViewModel.guardarCLiente: " + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });




    }
}