package com.farias.laboratorio3_final_app_android_peluqueria;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.farias.laboratorio3_final_app_android_peluqueria.modelo.Administrador;

/**
 * Created by Genaro Farias el 10/11/2021.
 * Estudiante de la ULP
 * gfsanluis78@gmail.com
 */

public class MainActivityViewModel extends AndroidViewModel {

    private MutableLiveData<Administrador> administradorMutableLiveData;
    private Context context;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        administradorMutableLiveData =  new MutableLiveData<>();
    }

//        public MainActivityViewModel@Nullable Application aplication){
//            super(aplication);
//            propietario =  new MutableLiveData<>();
//        }

    public MutableLiveData<Administrador> getAdministradorMutableLiveData() {
        if(administradorMutableLiveData == null){
            administradorMutableLiveData =  new MutableLiveData<>();
        }
        return administradorMutableLiveData;
    }

    public void actualizarPerfil(Administrador a){
        administradorMutableLiveData.setValue(a);
    }


}