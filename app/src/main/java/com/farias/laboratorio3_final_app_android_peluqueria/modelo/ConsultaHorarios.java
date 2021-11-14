package com.farias.laboratorio3_final_app_android_peluqueria.modelo;

/**
 * Created by Genaro Farias el 13/11/2021.
 * Estudiante de la ULP
 * gfsanluis78@gmail.com
 */

public class ConsultaHorarios {

    private int idEmpleado;
    private String fecha;

    public ConsultaHorarios() {
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "ConsultaHorarios{" +
                "idEmpleado=" + idEmpleado +
                ", fecha='" + fecha + '\'' +
                '}';
    }
}
