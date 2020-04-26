package modelo.movimiento;

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

public class DaoMovimiento implements java.io.Serializable {

    public boolean agregarMovimiento(movimiento v) {
        boolean insertado = false;
        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CRUD_Movimiento.INSERTAR.obtenerComando())) {

            stm.clearParameters();

            stm.setInt(1, v.getId_movimiento());
            stm.setString(2, v.getNum_cuenta());
            stm.setDouble(3, v.getMonto());
            stm.setTimestamp(4, DateConversion.util2Timestamp((java.util.Date) v.getFecha()));
            stm.setInt(5, v.getAplicado());
            stm.setString(6, v.getMovimientocol());

            if (stm.executeUpdate() != 1) {
                throw new SQLException();
            } else {
                insertado = true;
            }

        } catch (IOException | ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException ex) {
            Logger.getLogger(DaoMovimiento.class.getName()).log(Level.SEVERE, null, ex);
        }

        return insertado;
    }

    public boolean verificarMovimiento(String s) {
        boolean encontrado = false;
        int idMovimiento = Integer.parseInt(s);
        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CRUD_Movimiento.VERIFICAR.obtenerComando())) {
            stm.clearParameters();
            stm.setInt(1, idMovimiento);
            ResultSet rs = stm.executeQuery();
            encontrado = rs.next();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | IOException | SQLException ex) {
            Logger.getLogger(DaoMovimiento.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            bd.cerrarConexion();
        }
        return encontrado;
    }

    public Optional<movimiento> obtenerMovimiento(String idMovimiento) {
        Optional<movimiento> r = Optional.empty();
        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CRUD_Movimiento.CONSULTAR.obtenerComando());) {
            stm.clearParameters();
            stm.setString(1, idMovimiento);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    r = Optional.of(new movimiento(
                            rs.getInt("id_movimiento"),
                            rs.getString("cuenta_num_cuenta"),
                            rs.getDouble("monto"),
                            rs.getDate("fecha"),
                            rs.getInt("aplicado"),
                            rs.getString("movimientocol")
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

    public List<movimiento> obtenerListaMovimientos() {
        List<movimiento> r = new ArrayList<>();
        try (Connection cnx = obtenerConexion();
                Statement stm = cnx.createStatement();
                ResultSet rs = stm.executeQuery(CRUD_Movimiento.LISTAR.obtenerComando())) {
            while (rs.next()) {
                movimiento e = new movimiento(
                        rs.getInt("id_movimiento"),
                        rs.getString("cuenta_num_cuenta"),
                        rs.getDouble("monto"),
                        rs.getDate("fecha"),
                        rs.getInt("aplicado"),
                        rs.getString("movimientocol")
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

    private DaoMovimiento() {
        try {
            this.bd = BaseDatosBanco.obtenerInstancia();
            bd.obtenerConexion();
        } catch (SQLException ex) {
            Logger.getLogger(DaoMovimiento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static DaoMovimiento obtenerInstancia() {
        if (instancia == null) {
            instancia = new DaoMovimiento();
        }
        return instancia;
    }

    private static DaoMovimiento instancia = null;
    private BaseDatosBanco bd = null;

    public static void main(String[] args) {
        DaoMovimiento d = DaoMovimiento.obtenerInstancia();

        if (d.agregarMovimiento(new movimiento(344555, "2020-CRC-197-546767-9",
                6566767, servicios.ServicioFecha.fechaActualCuenta(), 1, "gfggghghg"))) {

            System.out.println("insertado");
        }
        if (d.verificarMovimiento("0")) {
            System.out.println("encontrado");
        }
        if (!d.verificarMovimiento("0")) {
            System.out.println(" no encontrado");
        }

        d.obtenerListaMovimientos().forEach((t) -> {
            System.out.println(t);
        });
    }
}
