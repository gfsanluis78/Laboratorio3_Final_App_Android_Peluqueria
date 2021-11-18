package com.farias.laboratorio3_final_app_android_peluqueria.ui.g_logout;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.farias.laboratorio3_final_app_android_peluqueria.modelo.LoginRetrofit;
import com.farias.laboratorio3_final_app_android_peluqueria.request.ApiClient;

public class LogoutViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<LoginRetrofit> loginRetrofitMutableLiveData;


    public LogoutViewModel(@NonNull Application application) {
        super(application);
        context = getApplication().getApplicationContext();
    }

    public MutableLiveData<LoginRetrofit> getLoginRetrofitMutableLiveData(){
        if (loginRetrofitMutableLiveData == null){
            loginRetrofitMutableLiveData = new MutableLiveData<>();
        }
        return loginRetrofitMutableLiveData;
    }

    public void borrarLogin(){
        ApiClient.guardar(context,"");
        Log.d("mensaje Logout: " ,"nuevo token: " + ApiClient.leer(context, getClass().toString()));

    }


}