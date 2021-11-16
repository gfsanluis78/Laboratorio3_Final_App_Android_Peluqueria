package com.farias.laboratorio3_final_app_android_peluqueria.modelo;

import com.google.type.DateTime;

import java.io.Serializable;

/**
 * Created by Genaro Farias el 12/11/2021.
 * Estudiante de la ULP
 * gfsanluis78@gmail.com
 */

public class Turno implements Serializable {

    private int idTurno;
    private int idBloque;
    private int idEstado;
    private int idTrabajo;
    private int idCliente;
    private String costo;
    private String notas;
    private String fecha;
    private DateTime fechaCreacion;
    private Bloque bloque;
    private Estado estado;
    private Trabajo trabajo;
    private Cliente cliente;

    public Turno() {
    }

    public Turno(int idBloque, int idEstado, int idTrabajo, int idCliente, String costo, String fecha) {
        this.idBloque = idBloque;
        this.idEstado = idEstado;
        this.idTrabajo = idTrabajo;
        this.idCliente = idCliente;
        this.costo = costo;
        this.fecha = fecha;

    }

    public Turno(int idTurno, int idBloque, int idEstado, int idTrabajo, int idCliente, String costo, String notas, String fecha, DateTime fechaCreacion, Bloque bloque, Estado estado, Trabajo trabajo, Cliente cliente) {
        this.idTurno = idTurno;
        this.idBloque = idBloque;
        this.idEstado = idEstado;
        this.idTrabajo = idTrabajo;
        this.idCliente = idCliente;
        this.costo = costo;
        this.notas = notas;
        this.fecha = fecha;
        this.fechaCreacion = fechaCreacion;
        this.bloque = bloque;
        this.estado = estado;
        this.trabajo = trabajo;
        this.cliente = cliente;
    }

    public int getIdTurno() {
        return idTurno;
    }

    public void setIdTurno(int idTurno) {
        this.idTurno = idTurno;
    }

    public int getIdBloque() {
        return idBloque;
    }

    public void setIdBloque(int idBloque) {
        this.idBloque = idBloque;
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public DateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(DateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Bloque getBloque() {
        return bloque;
    }

    public void setBloque(Bloque bloque) {
        this.bloque = bloque;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Trabajo getTrabajo() {
        return trabajo;
    }

    public void setTrabajo(Trabajo trabajo) {
        this.trabajo = trabajo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }


    @Override
    public String toString() {
        return "Turno{" +
                "idTurno=" + idTurno +
                ", idBloque=" + idBloque +
                ", idEstado=" + idEstado +
                ", idTrabajo=" + idTrabajo +
                ", idCliente=" + idCliente +
                ", costo='" + costo + '\'' +
                ", notas='" + notas + '\'' +
                ", fecha='" + fecha + '\'' +
                ", fechaCreacion=" + fechaCreacion +
                ", bloque=" + bloque +
                ", estado=" + estado +
                ", trabajo=" + trabajo +
                ", cliente=" + cliente +
                '}';
    }
}
