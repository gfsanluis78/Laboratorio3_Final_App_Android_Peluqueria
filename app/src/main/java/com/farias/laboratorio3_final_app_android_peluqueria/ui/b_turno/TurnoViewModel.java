package com.farias.laboratorio3_final_app_android_peluqueria.ui.b_turno;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TurnoViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public TurnoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is turno fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}