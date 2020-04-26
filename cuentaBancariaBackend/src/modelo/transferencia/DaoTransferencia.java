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
import util.conversion.DateConversion;

public class DaoTransferencia implements java.io.Serializable {

    public boolean agregarCuenta(transferencia tra) {
        boolean insertado = false;
        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CRUD_Transferencia.INSERTAR.obtenerComando())) {

            stm.clearParameters();
            stm.setInt(1, tra.getId_transferencia());

            stm.setString(2, tra.getCuenta_origen());
            stm.setString(3, tra.getCuenta_destino());
            stm.setString(4, tra.getMonto());
            stm.setTimestamp(5, DateConversion.util2Timestamp((java.util.Date) tra.getFecha()));

            stm.setInt(6, tra.getAplicado());

            if (stm.executeUpdate() != 1) {
                throw new SQLException();
            } else {
                insertado = true;
            }

        } catch (IOException | ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException ex) {
            Logger.getLogger(DaoTransferencia.class.getName()).log(Level.SEVERE, null, ex);
        }

        return insertado;
    }

    public boolean verificarTransferencia(int idTransferencia) {
        boolean encontrado = false;
        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CRUD_Transferencia.VERIFICAR.obtenerComando())) {
            stm.clearParameters();
            stm.setInt(1, idTransferencia);
            ResultSet rs = stm.executeQuery();
            encontrado = rs.next();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | IOException | SQLException ex) {
            Logger.getLogger(DaoTransferencia.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            bd.cerrarConexion();
        }
        return encontrado;
    }

    public Optional<transferencia> obtenerTransferencia(int idTransferencia) {
        Optional<transferencia> r = Optional.empty();
        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CRUD_Transferencia.CONSULTAR.obtenerComando());) {
            stm.clearParameters();
            stm.setInt(1, idTransferencia);
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

    public static void main(String[] args) {

        DaoTransferencia dt = DaoTransferencia.obtenerInstancia();
//        System.err.println("agregar tranferencia");
//        dt.agregarCuenta(new transferencia(1,"2020-CRC-545-554477-2","2020-USD-485-422547-4"
//        ,"100",servicios.ServicioFecha.fechaActualCuenta(),1));

        System.err.println("verificar tranferencia");
        if (dt.verificarTransferencia(1)) {
            System.out.println("encontrado");
        } else {
            System.out.println("no encontrado");
        }
        System.err.println("lista tranferencia");
        if (dt.verificarTransferencia(12)) {
            System.out.println("encontrado");
        } else {
            System.out.println("no encontrado");
        }

        System.err.println("lista tranferencia");

        dt.obtenerListaTransferencia().forEach((t) -> {
            System.out.println(t);
        });

        System.err.println("obtener transferencia");
        System.out.println(dt.obtenerTransferencia(1));

        System.err.println("obtener transferencia");
        System.out.println(dt.obtenerTransferencia(2));

    }

}
