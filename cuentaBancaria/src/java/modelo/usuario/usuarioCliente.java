/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.usuario;

import java.util.logging.Logger;

/**
 *
 * @author Bryan
 */
public class usuarioCliente {
    private String numeroCuenta, usuarioIdUsuario,apellidos,nombre,telefono;
    private Usuario user;
    public usuarioCliente(String numeroCuenta, String usuarioIdUsuario,
            String apellidos, String nombre, String telefono,Usuario user) {
        this.numeroCuenta = numeroCuenta;
        this.usuarioIdUsuario = usuarioIdUsuario;
        this.apellidos = apellidos;
        this.nombre = nombre;
        this.telefono = telefono;
        this.user=user;
    }
    private static final Logger LOG = Logger.getLogger(usuarioCliente.class.getName());

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getUsuarioIdUsuario() {
        return usuarioIdUsuario;
    }

    public void setUsuarioIdUsuario(String usuarioIdUsuario) {
        this.usuarioIdUsuario = usuarioIdUsuario;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }
    
 
}
