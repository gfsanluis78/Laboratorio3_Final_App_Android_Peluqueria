package com.farias.laboratorio3_final_app_android_peluqueria.modelo;

/**
 * Created by Genaro Farias el 23/10/2021.
 * Estudiante de la ULP
 * gfsanluis78@gmail.com
 */

public class LoginRetrofit {
    private String token;

    public LoginRetrofit(String token) {
        this.token = token;

    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        token = token;
    }



}
