/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.moneda;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import modelo.datos.BaseDatosBanco;

/**
 *
 * @author Bryan
 */
public class DaoMoneda {

    public List<moneda> obtenerListaMoneda() {
        List<moneda> r = new ArrayList<>();
        try (Connection cnx = obtenerConexion();
                Statement stm = cnx.createStatement();
                ResultSet rs = stm.executeQuery(CRUD_Moneda.LISTAR.obtenerComando())) {
            while (rs.next()) {
                moneda e = new moneda(
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getString("simbolo"),
                        rs.getDouble("tipo_cambio_compra"),
                        rs.getDouble("tipo_cambio_venta")
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

    private DaoMoneda() {
        try {
            this.bd = BaseDatosBanco.obtenerInstancia();
            bd.obtenerConexion();
        } catch (SQLException ex) {
            Logger.getLogger(DaoMoneda.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static DaoMoneda obtenerInstancia() {
        if (instancia == null) {
            instancia = new DaoMoneda();
        }
        return instancia;
    }

    private static DaoMoneda instancia = null;
    private BaseDatosBanco bd = null;

}
