package com.farias.laboratorio3_final_app_android_peluqueria.modelo;

import android.util.Log;

import java.io.Serializable;

import kotlin.text.UStringsKt;

/**
 * Created by Genaro Farias el 10/11/2021.
 * Estudiante de la ULP
 * gfsanluis78@gmail.com
 */

public class Cliente implements Serializable {
    private int idCliente;
    private String nombre;
    private String apellido;
    private String dni;
    private String telefono;
    private String email;
    private String password;
    private String especialidad;
    private String avatar;

    public Cliente() {
    }

    public Cliente(int idCliente, String nombre, String apellido, String dni, String telefono, String email, String password, String especialidad, String avatar) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.telefono = telefono;
        this.email = email;
        this.password = password;
        this.especialidad = especialidad;
        this.avatar = avatar;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
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
        return "Cliente{" +
                "idCliente=" + idCliente +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", dni='" + dni + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                ", especialidad='" + especialidad + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }

    public String getUrlFoto() {
        String urlBase = "http://192.168.1.111:45455/";
        String url = urlBase + avatar.replace("\\","/");
        Log.d("mensaje: ", "La url de la foto del cliente " + url);

        return url;
    }
}
