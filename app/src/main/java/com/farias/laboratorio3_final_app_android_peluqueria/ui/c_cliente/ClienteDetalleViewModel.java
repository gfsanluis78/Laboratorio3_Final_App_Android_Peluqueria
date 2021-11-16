package com.farias.laboratorio3_final_app_android_peluqueria.ui.c_cliente;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.farias.laboratorio3_final_app_android_peluqueria.modelo.Cliente;
import com.farias.laboratorio3_final_app_android_peluqueria.modelo.DetallesCliente;
import com.farias.laboratorio3_final_app_android_peluqueria.modelo.Turno;
import com.farias.laboratorio3_final_app_android_peluqueria.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClienteDetalleViewModel extends AndroidViewModel {

    private MutableLiveData<Cliente> clienteMutableLiveData;
    private MutableLiveData<Turno> turnoMutableLiveData;
    private MutableLiveData<DetallesCliente> detallesClienteMutableLiveData;
    private Context context;
    private DetallesCliente detallesCliente;
    private Cliente cliente;
    private Turno turno;

    public ClienteDetalleViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        this.clienteMutableLiveData = new MutableLiveData<>();
        this.turnoMutableLiveData = new MutableLiveData<>();
        this.detallesClienteMutableLiveData = new MutableLiveData<>();
        this.detallesCliente = new DetallesCliente();
        this.cliente = new Cliente();
        this.turno = new Turno();
    }

    public MutableLiveData<Cliente> getClienteMutableLiveData() {
        if (clienteMutableLiveData == null) {
            clienteMutableLiveData = new MutableLiveData<>();
        }
        return clienteMutableLiveData;
    }

    public MutableLiveData<Turno> getTurnoMutableLiveData() {
        if (turnoMutableLiveData == null){
            turnoMutableLiveData = new MutableLiveData<>();
        }
        return turnoMutableLiveData;
    }

    public MutableLiveData<DetallesCliente> getDetallesClienteMutableLiveData() {
        if( detallesClienteMutableLiveData == null){
            detallesClienteMutableLiveData = new MutableLiveData<>();
        }
        return  detallesClienteMutableLiveData;
    }


    public void setClienteMutableLiveData(Bundle bundle) {
        cliente = (Cliente) bundle.getSerializable("cliente");
        Log.d("mensaje", "ClienteDetalle VM: llega el bundle con " + cliente.toString());
        clienteMutableLiveData.setValue(cliente);
        setUltimoTurno(cliente);

    }

    public void setUltimoTurno(Cliente c) {

        detallesCliente.setCliente(c);


        Log.d("mensaje", "Cliente Detalle VM/setUltimoTurno: " + c.toString());
        String token = ApiClient.leer(context, getClass().toString());

        Call<Turno> turnoCall = ApiClient.getMyApiClient("Detalle Cliente VM").obtenerUltimoTurnoByCliente(token, c);
        turnoCall.enqueue(new Callback<Turno>() {
            @Override
            public void onResponse(Call<Turno> call, Response<Turno> response) {
                if(response.isSuccessful()){
                    turno = (Turno) response.body();
                    detallesCliente.setTurno(turno);
                    turnoMutableLiveData.setValue(turno);
                    detallesClienteMutableLiveData.setValue(detallesCliente);
                }
            }

            @Override
            public void onFailure(Call<Turno> call, Throwable t) {

            }
        });


    }
}