package com.farias.laboratorio3_final_app_android_peluqueria.ui.b_turno;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.farias.laboratorio3_final_app_android_peluqueria.modelo.Cliente;
import com.farias.laboratorio3_final_app_android_peluqueria.modelo.Turno;

public class TurnoDetalleViewModel extends AndroidViewModel {

    private MutableLiveData<Turno> turnoMutableLiveData;
    private Context context;
    private Turno turno;


    public TurnoDetalleViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
        this.turnoMutableLiveData = new MutableLiveData<>();
        this.turno = new Turno();
    }

    public MutableLiveData<Turno> getTurnoMutableLiveData() {
        return turnoMutableLiveData;
    }

    public void setTurnoMutableLiveData(Bundle bundle) {
        turno = (Turno) bundle.getSerializable("turno");
        Log.d("mensaje", "Cliente Turno Detalle VM: llegó el bundle con " + turno.toString());
        turnoMutableLiveData.setValue(turno);
    }

}