package com.farias.laboratorio3_final_app_android_peluqueria.modelo;

import java.io.Serializable;

/**
 * Created by Genaro Farias el 16/11/2021.
 * Estudiante de la ULP
 * gfsanluis78@gmail.com
 */

public class DetallesCliente implements Serializable {
    private Cliente cliente;
    private Turno turno;


    public DetallesCliente() {
    }

    public DetallesCliente(Cliente cliente, Turno turno) {
        this.cliente = cliente;
        this.turno = turno;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }

    @Override
    public String toString() {
        return "DetallesCliente{" +
                "cliente=" + cliente +
                ", turno=" + turno +
                '}';
    }
}
