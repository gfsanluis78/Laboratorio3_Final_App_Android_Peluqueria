package com.farias.laboratorio3_final_app_android_peluqueria.modelo;

import java.io.Serializable;

/**
 * Created by Genaro Farias el 12/11/2021.
 * Estudiante de la ULP
 * gfsanluis78@gmail.com
 */

public class Turno implements Serializable {

    private int idTurno;
    private int idHorario;
    private int idEstado;
    private int idTrabajo;
    private int idCliente;
    private String costo;
    private String notas;
    private String fechaCreacion;

    public Turno() {
    }

    public Turno(int idTurno, int idHorario, int idEstado, int idTrabajo, int idCliente, String costo, String notas, String fechaCreacion) {
        this.idTurno = idTurno;
        this.idHorario = idHorario;
        this.idEstado = idEstado;
        this.idTrabajo = idTrabajo;
        this.idCliente = idCliente;
        this.costo = costo;
        this.notas = notas;
        this.fechaCreacion = fechaCreacion;
    }

    public int getIdTurno() {
        return idTurno;
    }

    public void setIdTurno(int idTurno) {
        this.idTurno = idTurno;
    }

    public int getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(int idHorario) {
        this.idHorario = idHorario;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public int getIdTrabajo() {
        return idTrabajo;
    }

    public void setIdTrabajo(int idTrabajo) {
        this.idTrabajo = idTrabajo;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getCosto() {
        return costo;
    }

    public void setCosto(String costo) {
        this.costo = costo;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Override
    public String toString() {
        return "Turno{" +
                "idTurno=" + idTurno +
                ", idHorario=" + idHorario +
                ", idEstado=" + idEstado +
                ", idTrabajo=" + idTrabajo +
                ", idCliente=" + idCliente +
                ", costo='" + costo + '\'' +
                ", notas='" + notas + '\'' +
                ", fechaCreacion='" + fechaCreacion + '\'' +
                '}';
    }
}
