package com.farias.laboratorio3_final_app_android_peluqueria.ui.c_cliente;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ClienteViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ClienteViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is cliente fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}