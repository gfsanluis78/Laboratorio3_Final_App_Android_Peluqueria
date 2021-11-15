package com.farias.laboratorio3_final_app_android_peluqueria.modelo;

/**
 * Created by Genaro Farias el 14/11/2021.
 * Estudiante de la ULP
 * gfsanluis78@gmail.com
 */

public class ConsultaByTipoTrabajo {
    private int idTipoTrabajo;

    public ConsultaByTipoTrabajo() {

    }

    public ConsultaByTipoTrabajo(int idTipoTrabajo) {
        this.idTipoTrabajo = idTipoTrabajo;
    }

    public int getIdTipoTrabajo() {
        return idTipoTrabajo;
    }

    public void setIdTipoTrabajo(int idTipoTrabajo) {
        this.idTipoTrabajo = idTipoTrabajo;
    }

    @Override
    public String toString() {
        return "ConsultaByTipoTrabajo{" +
                "idTipoTrabajo=" + idTipoTrabajo +
                '}';
    }
}
