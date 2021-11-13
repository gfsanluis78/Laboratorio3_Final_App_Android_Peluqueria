package com.farias.laboratorio3_final_app_android_peluqueria.modelo;

import java.io.Serializable;

/**
 * Created by Genaro Farias el 12/11/2021.
 * Estudiante de la ULP
 * gfsanluis78@gmail.com
 */

public class Preparacion implements Serializable {

    private int idTurno;
    private Horario horario;
    private Estado estado;
    private TipoDeTrabajo tipoDeTrabajo;
    private Trabajo trabajo;
    private Cliente cliente;
    private String costo;
    private String notas;
    private String fechaCreacion;
    private Empleado empleado;

    public Preparacion() {
    }


    public int getIdTurno() {
        return idTurno;
    }

    public void setIdTurno(int idTurno) {
        this.idTurno = idTurno;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
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


    public TipoDeTrabajo getTipoDeTrabajo() {
        return tipoDeTrabajo;
    }

    public void setTipoDeTrabajo(TipoDeTrabajo tipoDeTrabajo) {
        this.tipoDeTrabajo = tipoDeTrabajo;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    @Override
    public String toString() {
        return "Preparacion{" +
                "idTurno=" + idTurno +
                ", horario=" + horario +
                ", estado=" + estado +
                ", trabajo=" + trabajo +
                ", cliente=" + cliente +
                ", costo='" + costo + '\'' +
                ", notas='" + notas + '\'' +
                ", fechaCreacion='" + fechaCreacion + '\'' +
                '}';
    }
}
