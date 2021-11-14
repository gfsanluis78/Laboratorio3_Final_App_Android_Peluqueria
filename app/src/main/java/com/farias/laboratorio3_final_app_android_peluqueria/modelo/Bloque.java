package com.farias.laboratorio3_final_app_android_peluqueria.modelo;

import java.io.Serializable;

/**
 * Created by Genaro Farias el 13/11/2021.
 * Estudiante de la ULP
 * gfsanluis78@gmail.com
 */

public class Bloque implements Serializable {

    private int getIdBloque;
    private int orden;
    private String desde;
    private String hasta;
    private String descripcion;

    public Bloque() {
    }

    public int getGetIdBloque() {
        return getIdBloque;
    }

    public void setGetIdBloque(int getIdBloque) {
        this.getIdBloque = getIdBloque;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public String getDesde() {
        return desde;
    }

    public void setDesde(String desde) {
        this.desde = desde;
    }

    public String getHasta() {
        return hasta;
    }

    public void setHasta(String hasta) {
        this.hasta = hasta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDesdeHasta(){
        return desde + " a " + hasta;
    }

    @Override
    public String toString() {
        return "Bloque{" +
                "getIdBloque=" + getIdBloque +
                ", orden=" + orden +
                ", desde='" + desde + '\'' +
                ", hasta='" + hasta + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
