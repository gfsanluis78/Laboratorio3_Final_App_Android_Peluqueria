package com.farias.laboratorio3_final_app_android_peluqueria.modelo;

import java.io.Serializable;

/**
 * Created by Genaro Farias el 12/11/2021.
 * Estudiante de la ULP
 * gfsanluis78@gmail.com
 */

public class Trabajo implements Serializable {
    private int idTrabajo;
    private int idTipoDeTrabajo;
    private int idEmpleado;
    private String comentarios;

    public Trabajo() {
    }

    public Trabajo(int idTrabajo, int idTipoDeTrabajo, int idEmpleado, String comentarios) {
        this.idTrabajo = idTrabajo;
        this.idTipoDeTrabajo = idTipoDeTrabajo;
        this.idEmpleado = idEmpleado;
        this.comentarios = comentarios;
    }

    public int getIdTrabajo() {
        return idTrabajo;
    }

    public void setIdTrabajo(int idTrabajo) {
        this.idTrabajo = idTrabajo;
    }

    public int getIdTipoDeTrabajo() {
        return idTipoDeTrabajo;
    }

    public void setIdTipoDeTrabajo(int idTipoDeTrabajo) {
        this.idTipoDeTrabajo = idTipoDeTrabajo;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    @Override
    public String toString() {
        return "Trabajo{" +
                "idTrabajo=" + idTrabajo +
                ", idTipoDeTrabajo=" + idTipoDeTrabajo +
                ", idEmpleado=" + idEmpleado +
                ", comentarios='" + comentarios + '\'' +
                '}';
    }
}
