/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.usuario;

import java.io.Serializable;

/**
 *
 * @author Bryan
 */
public class Usuario implements Serializable{
    
    private String idUsuario;
    private String claveAcceso;
    private int claveVencida;
    private int rol;

    public Usuario(String idUsuario, String claveAcceso, int claveVencida, int rol) {
        this.idUsuario = idUsuario;
        this.claveAcceso = claveAcceso;
        this.claveVencida = claveVencida;
        this.rol = rol;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getClaveAcceso() {
        return claveAcceso;
    }

    public void setClaveAcceso(String claveAcceso) {
        this.claveAcceso = claveAcceso;
    }

    public int getClaveVencida() {
        return claveVencida;
    }

    public void setClaveVencida(int claveVencida) {
        this.claveVencida = claveVencida;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return String.format("{ %s, %s, %s, %s }",idUsuario,claveAcceso,claveVencida,rol);
    }
    
    
}
