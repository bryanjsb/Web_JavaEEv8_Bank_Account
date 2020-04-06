/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.cuenta;

import modelo.cliente.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.datos.BaseDatosBanco;



/**
 *
 * @author Bryan
 */
public class DaoCuenta {
    
    public boolean verificarCliente(String idCliente) {
        boolean encontrado = false;
        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CRUD_Cuenta.VERIFICAR.obtenerComando())) {
            stm.clearParameters();
            stm.setString(1, idCliente);
            ResultSet rs = stm.executeQuery();
            encontrado = rs.next();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | IOException | SQLException ex) {
            Logger.getLogger(DaoCuenta.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            bd.cerrarConexion();
        }
        return encontrado;
    }

    public Optional<Cliente> obtenerCliente(String idCliente) {
        Optional<Cliente> r = Optional.empty();
        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CRUD_Cuenta.CONSULTAR.obtenerComando());) {
            stm.clearParameters();
            stm.setString(1, idCliente);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    r = Optional.of(new Cliente(
                           rs.getString("id_cliente"),
                        rs.getString("usuario_id_usuario"),
                        rs.getString("apellidos"),
                        rs.getString("nombre"),
                        rs.getString("telefono")
                    ));
                }
            }
        } catch (IOException
                | ClassNotFoundException
                | IllegalAccessException
                | InstantiationException
                | SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }finally {
            bd.cerrarConexion();
        }
        return r;
    }

    public List<Cliente> obtenerListaClientes() {
        List<Cliente> r = new ArrayList<>();
        try (Connection cnx = obtenerConexion();
                Statement stm = cnx.createStatement();
                ResultSet rs = stm.executeQuery(CRUD_Cuenta.LISTAR.obtenerComando())) {
            while (rs.next()) {
                Cliente e = new Cliente(
                        rs.getString("id_cliente"),
                        rs.getString("usuario_id_usuario"),
                        rs.getString("apellidos"),
                        rs.getString("nombre"),
                        rs.getString("telefono")
                );
                r.add(e);
            }
        } catch (IOException
                | ClassNotFoundException
                | IllegalAccessException
                | InstantiationException
                | SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }finally {
            bd.cerrarConexion();
        }
        return r;
    }

    public Connection obtenerConexion() throws
            ClassNotFoundException,
            IllegalAccessException,
            InstantiationException,
            IOException,
            SQLException {
        bd = BaseDatosBanco.obtenerInstancia();
        Connection cnx = bd.obtenerConexion();
        return cnx;
    }

    

    private DaoCuenta() {
        try {
            this.bd = BaseDatosBanco.obtenerInstancia();
            bd.obtenerConexion();
        } catch (SQLException ex) {
            Logger.getLogger(DaoCuenta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static DaoCuenta obtenerInstancia() {
        if (instancia == null) {
            instancia = new DaoCuenta();
        }
        return instancia;
    }

    private static DaoCuenta instancia = null;
    private BaseDatosBanco bd = null;
}
