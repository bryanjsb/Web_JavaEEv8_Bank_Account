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
import servicios.ServicioFecha;
import util.conversion.DateConversion;

public class DaoCuenta implements java.io.Serializable {

    public boolean agregarCuenta(cuenta cuenta) {
        boolean insertado = false;
        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CRUD_Cuenta.INSERTAR.obtenerComando())) {

            stm.clearParameters();
            stm.setString(1, cuenta.getNum_cuenta());
            stm.setInt(2, cuenta.getTipo_cuenta_id_tipo_cuenta());
            stm.setString(3, cuenta.getCliente_id_cliente());
            stm.setString(4, cuenta.getMoneda_nombre());
            stm.setTimestamp(5, DateConversion.util2Timestamp((java.util.Date) cuenta.getFecha_creacion()));
            stm.setDouble(6, cuenta.getLimite_transferencia_diaria());
            stm.setInt(7, cuenta.getActiva());
            stm.setDouble(8, cuenta.getSaldo_inicial());
            stm.setTimestamp(9, DateConversion.util2Timestamp((java.util.Date) cuenta.getFecha_ultima_aplicacion()));
            stm.setDouble(10, cuenta.getSaldo_final());

            if (stm.executeUpdate() != 1) {
                throw new SQLException();
            } else {
                insertado = true;
            }

        } catch (IOException | ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException ex) {
            Logger.getLogger(DaoCuenta.class.getName()).log(Level.SEVERE, null, ex);
        }

        return insertado;
    }

    public boolean modificarCuenta(cuenta cuenta) {
        boolean insertado = false;
        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CRUD_Cuenta.MODIFICAR.obtenerComando())) {

            stm.setString(1, cuenta.getNum_cuenta());
            stm.setInt(2, cuenta.getTipo_cuenta_id_tipo_cuenta());
            stm.setString(3, cuenta.getCliente_id_cliente());
            stm.setString(4, cuenta.getMoneda_nombre());
            stm.setTimestamp(5, DateConversion.util2Timestamp((java.util.Date) cuenta.getFecha_creacion()));
            stm.setDouble(6, cuenta.getLimite_transferencia_diaria());
            stm.setInt(7, cuenta.getActiva());
            stm.setDouble(8, cuenta.getSaldo_inicial());
            stm.setTimestamp(9, DateConversion.util2Timestamp((java.util.Date) cuenta.getFecha_ultima_aplicacion()));
            stm.setDouble(10, cuenta.getSaldo_final());

            stm.setString(11, cuenta.getNum_cuenta());

            if (stm.executeUpdate() != 1) {
                throw new SQLException();
            } else {
                insertado = true;
            }

        } catch (IOException | ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException ex) {
            Logger.getLogger(DaoCuenta.class.getName()).log(Level.SEVERE, null, ex);
        }

        return insertado;
    }

    public boolean verificarCuenta(String numCuenta) {
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

    public Optional<cuenta> obtenerNumeroCuenta(String numCuenta) {
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

    public Optional<cuenta> obtenerNumeroCuentaId(String idUsuario) {
        Optional<cuenta> r = Optional.empty();
        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CRUD_Cuenta.CONSULTARCLIENTE.obtenerComando());) {
            stm.clearParameters();
            stm.setString(1, idUsuario);
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

    public List<cuenta> obtenerListaCuenta() {
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

    public List<cuenta> obtenerListaCuentaCliente(String id) {
        List<cuenta> r = new ArrayList<>();
        try (Connection cnx = obtenerConexion();
                Statement stm = cnx.createStatement();
                ResultSet rs = stm.executeQuery(CRUD_Cuenta.LISTAR.obtenerComando())) {
            while (rs.next()) {
                String idCliente = rs.getString("cliente_id_cliente");
                if (idCliente.equals(id)) {

                    cuenta e = new cuenta(
                            rs.getString("num_cuenta"),
                            rs.getInt("tipo_cuenta_id_tipo_cuenta"),
                            idCliente,
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

    public static void main(String[] args) {
        DaoCuenta daoCuenta = obtenerInstancia();

//        cuenta cuenta = new cuenta("aa", 1, "114450585", "CRC", ServicioFecha.fechaActualCuenta(),
//                0.0, 1, 0.0, ServicioFecha.fechaActualCuenta(), 450.055);
//        daoCuenta.agregarCuenta(cuenta);
//        List<cuenta> listCuenta=daoCuenta.obtenerListaCuenta();
//      System.err.println("lista Cuenta");
//        listCuenta.forEach((t) -> {System.out.println(t);
//        });
//                
//                
//        System.err.println("Obtener cuenta por numero de cuenta");
//         System.out.println(daoCuenta.obtenerNumeroCuenta("2020-EUR-522-791486-0").get());
//        
        System.err.println("verificar num cuenta");

        if (daoCuenta.verificarCuenta("2020-EUR-522-791486-0")) {
            System.out.println("encontrado");
        } else {
            System.out.println("no encontrado");
        }

        System.err.println("Obtener cuentas por id usuario");
        List<cuenta> listCuentaID = daoCuenta.obtenerListaCuentaCliente("304760577");
        System.err.println("lista Cuenta");
        listCuentaID.forEach((t) -> {
            System.out.println(t);
        });

        System.err.println("modificar cuenta");
        cuenta cuenta = new cuenta("aa", 1, "114450585", "CRC", ServicioFecha.fechaActualCuenta(),
                111110.0, 1, 114.0, ServicioFecha.fechaActualCuenta(), 112111);
        daoCuenta.modificarCuenta(cuenta);
    }
}
