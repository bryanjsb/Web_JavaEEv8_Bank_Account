/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.cliente;

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
public class DaoCliente {

     public boolean registrarCliente(Cliente nuevoCliente) {
        boolean exito = false;
        int registrosActualizados = 0;
        try {
            Connection cnx = bd.obtenerConexion();

            try (PreparedStatement stm = cnx.prepareStatement(CRUD_Cliente.INSERTAR.obtenerComando())) {
                stm.clearParameters();

                stm.setString(1,nuevoCliente.getIdCliente());
            stm.setString(2,nuevoCliente.getUsuarioIdUsuario());
            stm.setString(3,nuevoCliente.getApellidos());
            stm.setString(4,nuevoCliente.getNombre());
            stm.setString(5,nuevoCliente.getTelefono());

//                stm.setTimestamp(4, DateConversion.util2Timestamp((java.util.Date) nuevoUsuario.ultimoAcceso()));
//
//                stm.setInt(5, nuevoUsuario.prioridad());

                registrosActualizados = stm.executeUpdate();
                exito = registrosActualizados == 1;
            }

        } catch (SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        } finally {
            bd.cerrarConexion();
        }
        return exito;
    }
    public boolean insertarCliente(Cliente cliente) {
        boolean insertado = false;

        try (Connection cnx = obtenerConexion();
                PreparedStatement s = cnx.prepareStatement(CRUD_Cliente.INSERTAR.obtenerComando());) {

            s.setString(1,cliente.getIdCliente());
            s.setString(2,cliente.getUsuarioIdUsuario());
            s.setString(3,cliente.getApellidos());
            s.setString(4,cliente.getNombre());
            s.setString(5,cliente.getTelefono());
           s.executeUpdate();
           insertado= s.execute();
        } catch (IOException
                | ClassNotFoundException
                | IllegalAccessException
                | InstantiationException
                | SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        } finally {
            bd.cerrarConexion();
        }

        return insertado;

    }

    public boolean verificarCliente(String idCliente) {
        boolean encontrado = false;
        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CRUD_Cliente.VERIFICAR.obtenerComando())) {
            stm.clearParameters();
            stm.setString(1, idCliente);
            ResultSet rs = stm.executeQuery();
            encontrado = rs.next();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | IOException | SQLException ex) {
            Logger.getLogger(DaoCliente.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            bd.cerrarConexion();
        }
        return encontrado;
    }

    public Optional<Cliente> obtenerCliente(String idCliente) {
        Optional<Cliente> r = Optional.empty();
        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CRUD_Cliente.CONSULTAR.obtenerComando());) {
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
        } finally {
            bd.cerrarConexion();
        }
        return r;
    }

    public List<Cliente> obtenerListaClientes() {
        List<Cliente> r = new ArrayList<>();
        try (Connection cnx = obtenerConexion();
                Statement stm = cnx.createStatement();
                ResultSet rs = stm.executeQuery(CRUD_Cliente.LISTAR.obtenerComando())) {
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

    private DaoCliente() {
        try {
            this.bd = BaseDatosBanco.obtenerInstancia();
            bd.obtenerConexion();
        } catch (SQLException ex) {
            Logger.getLogger(DaoCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static DaoCliente obtenerInstancia() {
        if (instancia == null) {
            instancia = new DaoCliente();
        }
        return instancia;
    }

    private static DaoCliente instancia = null;
    private BaseDatosBanco bd = null;
}
