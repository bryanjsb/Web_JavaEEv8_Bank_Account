/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.tipo_cuenta;

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

public class DaoTipo_cuenta {

    public boolean verificarTipo_cuenta(String idTipo_cuenta) {
        boolean encontrado = false;
        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CRUD_Tipo_cuenta.VERIFICAR.obtenerComando())) {
            stm.clearParameters();
            stm.setString(1, idTipo_cuenta);
            ResultSet rs = stm.executeQuery();
            encontrado = rs.next();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | IOException | SQLException ex) {
            Logger.getLogger(DaoTipo_cuenta.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            bd.cerrarConexion();
        }
        return encontrado;
    }

    public Optional<tipo_cuenta> obtenerMovimiento(String idTipo_cuenta) {
        Optional<tipo_cuenta> r = Optional.empty();
        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CRUD_Tipo_cuenta.CONSULTAR.obtenerComando());) {
            stm.clearParameters();
            stm.setString(1, idTipo_cuenta);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    r = Optional.of(new tipo_cuenta(
                            rs.getInt("id_tipo_cuenta"),
                            rs.getString("descripcion"),
                            rs.getDouble("tasa_interes")
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

    public List<tipo_cuenta> obtenerListaTipo_cuenta() {
        List<tipo_cuenta> r = new ArrayList<>();
        try (Connection cnx = obtenerConexion();
                Statement stm = cnx.createStatement();
                ResultSet rs = stm.executeQuery(CRUD_Tipo_cuenta.LISTAR.obtenerComando())) {
            while (rs.next()) {
                tipo_cuenta e = new tipo_cuenta(
                        rs.getInt("id_tipo_cuenta"),
                        rs.getString("descripcion"),
                        rs.getDouble("tasa_interes")
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

    private DaoTipo_cuenta() {
        try {
            this.bd = BaseDatosBanco.obtenerInstancia();
            bd.obtenerConexion();
        } catch (SQLException ex) {
            Logger.getLogger(DaoTipo_cuenta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static DaoTipo_cuenta obtenerInstancia() {
        if (instancia == null) {
            instancia = new DaoTipo_cuenta();
        }
        return instancia;
    }

    private static DaoTipo_cuenta instancia = null;
    private BaseDatosBanco bd = null;
}
