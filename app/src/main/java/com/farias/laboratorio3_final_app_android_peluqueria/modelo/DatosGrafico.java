package com.farias.laboratorio3_final_app_android_peluqueria.modelo;

import java.io.Serializable;

/**
 * Created by Genaro Farias el 18/11/2021.
 * Estudiante de la ULP
 * gfsanluis78@gmail.com
 */

public class DatosGrafico implements Serializable {
    private Empleado empleado;
    private int cantidad;

    public DatosGrafico() {
    }

    public DatosGrafico(Empleado empleado, int cantidad) {
        this.empleado = empleado;
        this.cantidad = cantidad;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "DatosGrafico{" +
                "empleado=" + empleado +
                ", cantidad=" + cantidad +
                '}';
    }
}
