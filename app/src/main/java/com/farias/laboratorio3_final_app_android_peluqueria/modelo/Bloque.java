package com.farias.laboratorio3_final_app_android_peluqueria.modelo;

import java.io.Serializable;

/**
 * Created by Genaro Farias el 13/11/2021.
 * Estudiante de la ULP
 * gfsanluis78@gmail.com
 */

public class Bloque implements Serializable {

    private int idBloque;
    private int orden;
    private String desde;
    private String hasta;
    private String descripcion;

    public Bloque() {
    }

    public Bloque(int idBloque, String desde, String hasta){
        this.idBloque = idBloque;
        this.desde = desde;
        this.hasta = hasta;

    }

    public void setIdBloque(int idBloque) {
        this.idBloque = idBloque;
    }

    public int getGetIdBloque() {
        return idBloque;
    }

    public void setGetIdBloque(int getIdBloque) {
        this.idBloque = getIdBloque;
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
                "getIdBloque=" + idBloque +
                ", orden=" + orden +
                ", desde='" + desde + '\'' +
                ", hasta='" + hasta + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
