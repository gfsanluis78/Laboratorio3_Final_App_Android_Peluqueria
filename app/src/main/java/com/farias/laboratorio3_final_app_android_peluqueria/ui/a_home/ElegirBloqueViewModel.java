package com.farias.laboratorio3_final_app_android_peluqueria.ui.a_home;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.farias.laboratorio3_final_app_android_peluqueria.modelo.Bloque;
import com.farias.laboratorio3_final_app_android_peluqueria.modelo.ConsultaHorarios;
import com.farias.laboratorio3_final_app_android_peluqueria.modelo.Empleado;
import com.farias.laboratorio3_final_app_android_peluqueria.modelo.Preparacion;
import com.farias.laboratorio3_final_app_android_peluqueria.modelo.TipoDeTrabajo;
import com.farias.laboratorio3_final_app_android_peluqueria.request.ApiClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ElegirBloqueViewModel extends AndroidViewModel {

    private Context context;
    private MutableLiveData<Preparacion> preparacionMutableLiveData;
    private MutableLiveData<ArrayList<Bloque>> bloqueMutableLiveData;
    private Preparacion preparacion;
    private ConsultaHorarios consultaHorarios;
    private MutableLiveData<Button> a,b,c,d,e,f,g,h;



    public ElegirBloqueViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        this.preparacionMutableLiveData = new MutableLiveData<>();
        this.bloqueMutableLiveData = new MutableLiveData<>();
        this.preparacion = new Preparacion();
        this.consultaHorarios = new ConsultaHorarios();
        this.a = new MutableLiveData<>();
        this.b = new MutableLiveData<>();
        this.c = new MutableLiveData<>();
        this.d = new MutableLiveData<>();
        this.e = new MutableLiveData<>();
        this.f = new MutableLiveData<>();
        this.g = new MutableLiveData<>();
        this.h = new MutableLiveData<>();
    }

    // metodos

    public MutableLiveData<Preparacion> getPreparacionMutableLiveData() {
        return preparacionMutableLiveData;
    }

    public MutableLiveData<ArrayList<Bloque>> getBloqueMutableLiveData() {
        return bloqueMutableLiveData;
    }

    public MutableLiveData<Button> getA() {
        return a;
    }

    public MutableLiveData<Button> getB() {
        return b;
    }

    public MutableLiveData<Button> getC() {
        return c;
    }

    public MutableLiveData<Button> getD() {
        return d;
    }

    public MutableLiveData<Button> getE() {
        return e;
    }

    public MutableLiveData<Button> getF() {
        return f;
    }

    public MutableLiveData<Button> getG() {
        return g;
    }

    public MutableLiveData<Button> getH() {
        return h;
    }

    public void setPreparacion(Bundle arguments) {
        preparacion = (Preparacion) arguments.getSerializable("preparacion");
        Log.d("mensaje", "View Model Elegir Bloque. El bundle trae " + preparacion.getCliente().toString() + ". Tipo de trabajo: " + preparacion.getTipoDeTrabajo().getNombre() + ". Empleado: " + preparacion.getEmpleado().getApellido());
        preparacionMutableLiveData.setValue(preparacion);
    }

    public void setBloquesMutableLiveData(Preparacion preparacion){

        consultaHorarios.setIdEmpleado(preparacion.getEmpleado().getIdEmpleado());
        consultaHorarios.setFecha(preparacion.getFecha().getDate());



        String token = ApiClient.leer(context, getClass().toString().toUpperCase(Locale.ROOT));

        Call<List<Bloque>> callBloque = ApiClient.getMyApiClient("Inmueble VM").obtenerBloquesLibresDelDiaDelProfesional(token, consultaHorarios);
        callBloque.enqueue(new Callback<List<Bloque>>() {
            @Override
            public void onResponse(Call<List<Bloque>> call, Response<List<Bloque>> response) {
                if (response.isSuccessful()){
                    bloqueMutableLiveData.setValue((ArrayList<Bloque>) response.body());
                    if(response.body().isEmpty()){
                        Toast.makeText(context, "Todos los turnos estan disponibles", Toast.LENGTH_SHORT).show();
                    }
                    Log.d("mensaje viewmodel", "Response isSuccessful: "+ response.body().toString());
                }
                else{
                    Log.d("mensaje", "Fallo en VM elegir trabajo: " + response.message());
                }


            }

            @Override
            public void onFailure(Call<List<Bloque>> call, Throwable t) {
                Log.d("mensaje", "Fallo en view model elegir tipo de trabajo: " + t.getMessage());
            }
        });
    }




}