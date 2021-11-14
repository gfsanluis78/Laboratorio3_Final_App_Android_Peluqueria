package com.farias.laboratorio3_final_app_android_peluqueria.modelo;

import android.hardware.SensorEventListener;

import com.google.type.DateTime;

import java.io.Serializable;

/**
 * Created by Genaro Farias el 13/11/2021.
 * Estudiante de la ULP
 * gfsanluis78@gmail.com
 */

public class Fecha implements Serializable {
    private int dia;
    private int mes;
    private int año;
    private String date;
    private DateTime dateTime;

    public Fecha() {
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(DateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Fecha{" +
                "dia=" + dia +
                ", mes=" + mes +
                ", año=" + año +
                ", date='" + date + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }
}
