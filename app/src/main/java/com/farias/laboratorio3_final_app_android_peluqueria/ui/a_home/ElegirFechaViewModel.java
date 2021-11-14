package com.farias.laboratorio3_final_app_android_peluqueria.ui.a_home;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.farias.laboratorio3_final_app_android_peluqueria.modelo.Empleado;
import com.farias.laboratorio3_final_app_android_peluqueria.modelo.Fecha;
import com.farias.laboratorio3_final_app_android_peluqueria.modelo.Preparacion;

import java.util.ArrayList;

public class ElegirFechaViewModel extends AndroidViewModel {

    private Context context;
    private MutableLiveData<Preparacion> preparacionMutableLiveData;
    private MutableLiveData<Fecha> fechaMutableLiveData;
    private MutableLiveData<Boolean> esFechaValida;
    private Preparacion preparacion;



    public ElegirFechaViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        this.preparacionMutableLiveData = new MutableLiveData<>();
        this.esFechaValida = new MutableLiveData<>();
        this.preparacion = new Preparacion();
    }

    // metodos

    public MutableLiveData<Preparacion> getPreparacionMutableLiveData() {
        return preparacionMutableLiveData;
    }

    public MutableLiveData<Boolean> getEsFechaValida() {
        return esFechaValida;
    }

    public void setFechaValida() {
        esFechaValida.setValue(true);
    }



    public void setPreparacion(Bundle arguments) {
        preparacion = (Preparacion) arguments.getSerializable("preparacion");
        Log.d("mensaje", "View Model Elegir fecha. El bundle trae " + preparacion.getCliente().toString() + ". Tipo de trabajo: " + preparacion.getTipoDeTrabajo().getNombre() + ". Empleado: " + preparacion.getEmpleado().getApellido());
        preparacionMutableLiveData.setValue(preparacion);
    }




}