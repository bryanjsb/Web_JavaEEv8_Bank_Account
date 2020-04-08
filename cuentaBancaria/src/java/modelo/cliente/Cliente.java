/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.cliente;

/**
 *
 * @author Bryan
 */
public class Cliente implements java.io.Serializable {

    private String idCliente, usuarioIdUsuario, apellidos, nombre, telefono;

    public Cliente(String idCliente, String usuarioIdUsuario,
            String apellidos, String nombre, String telefono) {
        this.idCliente = idCliente;
        this.usuarioIdUsuario = usuarioIdUsuario;
        this.apellidos = apellidos;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public Cliente() {
        this("", "", "", "", "");
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
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

}
