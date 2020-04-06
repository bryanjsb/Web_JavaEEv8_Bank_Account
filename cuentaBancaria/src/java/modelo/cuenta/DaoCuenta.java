/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.cuenta;

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

public class DaoCuenta {

    public boolean verificarCliente(String numCuenta) {
        boolean encontrado = false;
        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CRUD_Cuenta.VERIFICAR.obtenerComando())) {
            stm.clearParameters();
            stm.setString(1, numCuenta);
            ResultSet rs = stm.executeQuery();
            encontrado = rs.next();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | IOException | SQLException ex) {
            Logger.getLogger(DaoCuenta.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            bd.cerrarConexion();
        }
        return encontrado;
    }

    public Optional<cuenta> obtenerCliente(String numCuenta) {
        Optional<cuenta> r = Optional.empty();
        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CRUD_Cuenta.CONSULTAR.obtenerComando());) {
            stm.clearParameters();
            stm.setString(1, numCuenta);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    r = Optional.of(new cuenta(
                            rs.getString("num_cuenta"),
                            rs.getInt("tipo_cuenta_id_tipo_cuenta"),
                            rs.getString("cliente_id_cliente"),
                            rs.getString("moneda_nombre"),
                            rs.getDate("fecha_creacion"),
                            rs.getDouble("limite_transferencia_diaria"),
                            rs.getInt("activa"),
                            rs.getDouble("saldo_inicial"),
                            rs.getDate("fecha_ultima_aplicacion"),
                            rs.getDouble("saldo_final")
                    ));
                }
            }
        } catch (IOException
                | ClassNotFoundException
                | IllegalAccessException
                | InstantiationException
                | SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        } finally {
            bd.cerrarConexion();
        }
        return r;
    }

    public List<cuenta> obtenerListaClientes() {
        List<cuenta> r = new ArrayList<>();
        try (Connection cnx = obtenerConexion();
                Statement stm = cnx.createStatement();
                ResultSet rs = stm.executeQuery(CRUD_Cuenta.LISTAR.obtenerComando())) {
            while (rs.next()) {
                cuenta e = new cuenta(
                        rs.getString("num_cuenta"),
                        rs.getInt("tipo_cuenta_id_tipo_cuenta"),
                        rs.getString("cliente_id_cliente"),
                        rs.getString("moneda_nombre"),
                        rs.getDate("fecha_creacion"),
                        rs.getDouble("limite_transferencia_diaria"),
                        rs.getInt("activa"),
                        rs.getDouble("saldo_inicial"),
                        rs.getDate("fecha_ultima_aplicacion"),
                        rs.getDouble("saldo_final")
                );
                r.add(e);
            }
        } catch (IOException
                | ClassNotFoundException
                | IllegalAccessException
                | InstantiationException
                | SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        } finally {
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