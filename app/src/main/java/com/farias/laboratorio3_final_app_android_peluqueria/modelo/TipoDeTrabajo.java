package com.farias.laboratorio3_final_app_android_peluqueria.modelo;

import android.util.Log;

/**
 * Created by Genaro Farias el 12/11/2021.
 * Estudiante de la ULP
 * gfsanluis78@gmail.com
 */

public class TipoDeTrabajo {

    private int idTipoDeTrabajo;
    private String nombre;
    private String descripcion;
    private String duracion;
    private String costo;
    private String genero;
    private String imagen;

    public TipoDeTrabajo() {
    }

    public int getIdTipodeTrabajo() {
        return idTipoDeTrabajo;
    }

    public void setIdTipoDeTrabajo(int idTipodeTrabajo) {
        this.idTipoDeTrabajo = idTipodeTrabajo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getCosto() {
        return costo;
    }

    public void setCosto(String costo) {
        this.costo = costo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getUrlFoto() {
        String urlBase = "http://192.168.1.111:45455/";
        String url = urlBase + imagen;
        Log.d("mensaje: ", "La url de la foto del tipo de trabajo es " + url);

        return url;
    }

    @Override
    public String toString() {
        return "TipoDeTrabajo{" +
                "idTipodeTrabajo=" + idTipoDeTrabajo +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", duracion='" + duracion + '\'' +
                ", costo='" + costo + '\'' +
                ", genero='" + genero + '\'' +
                ", imagen='" + imagen + '\'' +
                '}';
    }
}
