package com.farias.laboratorio3_final_app_android_peluqueria.ui.a_home;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.farias.laboratorio3_final_app_android_peluqueria.modelo.Confirmacion;
import com.farias.laboratorio3_final_app_android_peluqueria.modelo.Fecha;
import com.farias.laboratorio3_final_app_android_peluqueria.modelo.Preparacion;
import com.farias.laboratorio3_final_app_android_peluqueria.modelo.Trabajo;
import com.farias.laboratorio3_final_app_android_peluqueria.modelo.Turno;
import com.farias.laboratorio3_final_app_android_peluqueria.request.ApiClient;
import com.google.type.DateTime;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConfirmacionTurnoViewModel extends AndroidViewModel {

    private Context context;
    private MutableLiveData<Preparacion> preparacionMutableLiveData;
    private MutableLiveData<Confirmacion> confirmacionMutableLiveData;
    private MutableLiveData<Turno> turnoMutableLiveData;
    private Preparacion preparacion;
    private Turno turno;
    private Calendar hoy;
    private Fecha fecha;



    public ConfirmacionTurnoViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        this.preparacionMutableLiveData = new MutableLiveData<>();
        this.confirmacionMutableLiveData = new MutableLiveData<>();
        this.turnoMutableLiveData = new MutableLiveData<>();
        this.preparacion = new Preparacion();
    }

    public MutableLiveData<Preparacion> getPreparacionMutableLiveData() {
        return preparacionMutableLiveData;
    }

    public MutableLiveData<Confirmacion> getConfirmacionMutableLiveData() {
        return confirmacionMutableLiveData;
    }

    public MutableLiveData<Turno> getTurnoMutableLiveData() {
        return turnoMutableLiveData;
    }


    public void setPreparacion(Bundle arguments) {
        preparacion = (Preparacion) arguments.getSerializable("preparacion");
        Log.d("mensaje", "View Model Elegir Bloque. El bundle trae " + preparacion.getCliente().toString() + ". Tipo de trabajo: " + preparacion.getTipoDeTrabajo().getNombre() + ". Empleado: " + preparacion.getEmpleado().getApellido());
        preparacionMutableLiveData.setValue(preparacion);
    }

    public void setTurno(Preparacion p) {

        // idEstado 2 default pendiente
        turno = new Turno(
                p.getBloque().getGetIdBloque(),
                2,
                p.getTrabajo().getIdTrabajo(),
                p.getCliente().getIdCliente(),
                p.getTipoDeTrabajo().getCosto(),
                p.getFecha().getDate());

        String token = ApiClient.leer(context, getClass().toString().toUpperCase(Locale.ROOT));

        Call<Turno> callTurno = ApiClient.getMyApiClient("Confirmacion VM").crearTurno(token, turno);
        callTurno.enqueue(new Callback<Turno>() {
            @Override
            public void onResponse(Call<Turno> call, Response<Turno> response) {
                if (response.isSuccessful()){
                    turnoMutableLiveData.setValue(response.body());
                    if(response.body() == null){
                        Toast.makeText(context, "El post no devolvio el turno creado", Toast.LENGTH_SHORT).show();
                    }
                    Log.d("mensaje viewmodel", "Response isSuccessful: "+ response.body().toString());
                    Toast.makeText(context, "El turno se creo con exito.", Toast.LENGTH_LONG).show();
                }
                else{
                    Log.d("mensaje", "Fallo en VM confirmacion TURNO: " + response.message());
                }


            }

            @Override
            public void onFailure(Call<Turno> call, Throwable t) {
                Log.d("mensaje", "Fallo en view model confirmar TURNO : " + t.getMessage());
            }
        });


    }


}