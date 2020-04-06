package modelo.transferencia;

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

public class DaoTransferencia {

    public boolean verificarTransferencia(String idTransferencia) {
        boolean encontrado = false;
        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CRUD_Transferencia.VERIFICAR.obtenerComando())) {
            stm.clearParameters();
            stm.setString(1, idTransferencia);
            ResultSet rs = stm.executeQuery();
            encontrado = rs.next();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | IOException | SQLException ex) {
            Logger.getLogger(DaoTransferencia.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            bd.cerrarConexion();
        }
        return encontrado;
    }

    public Optional<transferencia> obtenerTraansferencia(String idTransferencia) {
        Optional<transferencia> r = Optional.empty();
        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CRUD_Transferencia.CONSULTAR.obtenerComando());) {
            stm.clearParameters();
            stm.setString(1, idTransferencia);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    r = Optional.of(new transferencia(
                            rs.getInt("id_transferencia"),
                            rs.getString("cuenta_origen"),
                            rs.getString("cuenta_destino"),
                            rs.getString("monto"),
                            rs.getDate("fecha"),
                            rs.getInt("aplicado")
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

    public List<transferencia> obtenerListaTransferencia() {
        List<transferencia> r = new ArrayList<>();
        try (Connection cnx = obtenerConexion();
                Statement stm = cnx.createStatement();
                ResultSet rs = stm.executeQuery(CRUD_Transferencia.LISTAR.obtenerComando())) {
            while (rs.next()) {
                transferencia e = new transferencia(
                        rs.getInt("id_transferencia"),
                        rs.getString("cuenta_origen"),
                        rs.getString("cuenta_destino"),
                        rs.getString("monto"),
                        rs.getDate("fecha"),
                        rs.getInt("aplicado")
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

    private DaoTransferencia() {
        try {
            this.bd = BaseDatosBanco.obtenerInstancia();
            bd.obtenerConexion();
        } catch (SQLException ex) {
            Logger.getLogger(DaoTransferencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static DaoTransferencia obtenerInstancia() {
        if (instancia == null) {
            instancia = new DaoTransferencia();
        }
        return instancia;
    }

    private static DaoTransferencia instancia = null;
    private BaseDatosBanco bd = null;
}
