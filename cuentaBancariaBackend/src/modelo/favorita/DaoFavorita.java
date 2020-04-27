package modelo.favorita;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.datos.BaseDatosBanco;

public class DaoFavorita implements java.io.Serializable {

    public boolean agregarFavorita(favorita favorita) {
        boolean insertado = false;
        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CRUD_Favorita.INSERTAR.obtenerComando())) {

            stm.clearParameters();
            stm.setString(1, favorita.getCliente_id_cliente());
            stm.setString(2, favorita.getCuenta_num_cuenta());
            if (stm.executeUpdate() != 1) {
                throw new SQLException();
            } else {
                insertado = true;
            }

        } catch (IOException | ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException ex) {
            Logger.getLogger(DaoFavorita.class.getName()).log(Level.SEVERE, null, ex);
        }

        return insertado;
    }

    public boolean verificarFavorita(String idCliente, String numCuenta) {
        boolean encontrado = false;
        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CRUD_Favorita.VERIFICAR.obtenerComando())) {
            stm.clearParameters();
            stm.setString(1, idCliente);
            stm.setString(2, numCuenta);
            ResultSet rs = stm.executeQuery();
            encontrado = rs.next();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | IOException | SQLException ex) {
            Logger.getLogger(DaoFavorita.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            bd.cerrarConexion();
        }
        return encontrado;
    }

//    public Optional<cuenta> obtenerNumeroCuentaId(String idUsuario) {
//        Optional<cuenta> r = Optional.empty();
//        try (Connection cnx = obtenerConexion();
//                PreparedStatement stm = cnx.prepareStatement(CRUD_Favorita.CONSULTARCLIENTE.obtenerComando());) {
//            stm.clearParameters();
//            stm.setString(1, idUsuario);
//            try (ResultSet rs = stm.executeQuery()) {
//                if (rs.next()) {
//                    r = Optional.of(new cuenta(
//                            rs.getString("num_cuenta"),
//                            rs.getInt("tipo_cuenta_id_tipo_cuenta"),
//                            rs.getString("cliente_id_cliente"),
//                            rs.getString("moneda_nombre"),
//                            rs.getDate("fecha_creacion"),
//                            rs.getDouble("limite_transferencia_diaria"),
//                            rs.getInt("activa"),
//                            rs.getDouble("saldo_inicial"),
//                            rs.getDate("fecha_ultima_aplicacion"),
//                            rs.getDouble("saldo_final")
//                    ));
//                }
//            }
//        } catch (IOException
//                | ClassNotFoundException
//                | IllegalAccessException
//                | InstantiationException
//                | SQLException ex) {
//            System.err.printf("Excepción: '%s'%n", ex.getMessage());
//        } finally {
//            bd.cerrarConexion();
//        }
//        return r;
//    }
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

    private DaoFavorita() {
        try {
            this.bd = BaseDatosBanco.obtenerInstancia();
            bd.obtenerConexion();
        } catch (SQLException ex) {
            Logger.getLogger(DaoFavorita.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static DaoFavorita obtenerInstancia() {
        if (instancia == null) {
            instancia = new DaoFavorita();
        }
        return instancia;
    }

    private static DaoFavorita instancia = null;
    private BaseDatosBanco bd = null;

    public static void main(String[] args) {

        DaoFavorita d = obtenerInstancia();

//       d.agregarFavorita(new favorita("304760577", "2020-CRC-258-414499-4"));
        System.out.println("verifar");
        if (d.verificarFavorita("304760577", "2020-CRC-258-414499-4")) {
            System.out.println("encontrado");
        } else {
            System.out.println(" no encontrado");
        }
    }
}
