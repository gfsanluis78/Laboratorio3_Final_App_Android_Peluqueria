package com.farias.laboratorio3_final_app_android_peluqueria.modelo;

import android.util.Log;

import java.io.Serializable;

/**
 * Created by Genaro Farias el 10/11/2021.
 * Estudiante de la ULP
 * gfsanluis78@gmail.com
 */

public class Administrador implements Serializable {
    private int idAdministrador;
    private String nombre;
    private String apellido;
    private String dni;
    private String telefono;
    private String email;
    private String password;
    private String avatar;

    public Administrador() {
    }

    public Administrador(int idAdministrador, String nombre, String apellido, String dni, String telefono, String email, String password, String avatar) {
        this.idAdministrador = idAdministrador;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.telefono = telefono;
        this.email = email;
        this.password = password;
        this.avatar = avatar;
    }

    public int getIdAdministrador() {
        return idAdministrador;
    }

    public void setIdAdministrador(int idAdministrador) {
        this.idAdministrador = idAdministrador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContraseña() {
        return password;
    }

    public void setContraseña(String contraseña) {
        this.password = contraseña;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNombreCompleto(){
        return nombre + " " + apellido;
    }

    @Override
    public String toString() {
        return "Administrador{" +
                "idAdministrador=" + idAdministrador +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", dni='" + dni + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }

    public String getUrlFoto() {
        String urlBase = "http://192.168.1.111:45455/";
        String url = urlBase + avatar;
        Log.d("mensaje: ", "La url de la foto del administrador " + url);

        return url;
    }

}
