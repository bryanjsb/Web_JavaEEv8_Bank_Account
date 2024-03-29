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
import modelo.usuario.DaoUsuario;
import modelo.usuario.Usuario;

public class DaoCliente implements java.io.Serializable {

    public boolean agregarCliente(Cliente cliente) {
        boolean insertado = false;
        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CRUD_Cliente.INSERTAR.obtenerComando())) {

            stm.clearParameters();
            stm.setString(1, cliente.getIdCliente());
            stm.setString(2, cliente.getUsuarioIdUsuario());
            stm.setString(3, cliente.getApellidos());
            stm.setString(4, cliente.getNombre());
            stm.setString(5, cliente.getTelefono());

            if (stm.executeUpdate() != 1) {
                throw new SQLException();
            } else {
                insertado = true;
            }

        } catch (IOException | ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException ex) {
            Logger.getLogger(DaoCliente.class.getName()).log(Level.SEVERE, null, ex);
        }

        return insertado;
    }

    public boolean agregarCliente(Cliente cliente, Usuario usuario) {
        boolean insertado = false;
        DaoUsuario daousuario = DaoUsuario.obtenerInstancia();
        if (!daousuario.agregarUsuario(usuario)) {
            return false;
        }

        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CRUD_Cliente.INSERTAR.obtenerComando())) {

            stm.clearParameters();
            stm.setString(1, cliente.getIdCliente());
            stm.setString(2, cliente.getUsuarioIdUsuario());
            stm.setString(3, cliente.getApellidos());
            stm.setString(4, cliente.getNombre());
            stm.setString(5, cliente.getTelefono());

            if (stm.executeUpdate() != 1) {
                throw new SQLException();
            } else {
                insertado = true;
            }

        } catch (IOException | ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException ex) {
            Logger.getLogger(DaoCliente.class.getName()).log(Level.SEVERE, null, ex);
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
            stm.setString(2, idCliente);
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

    public void eliminarCliente(String cliente) {

    }

    public void modificarCliente(Cliente n) {

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

    public static void main(String[] args) {
        DaoCliente se = DaoCliente.obtenerInstancia();

        System.out.println("Obtener cliente");
        Optional<Cliente> us = se.obtenerCliente("304760577");

        System.out.println(us);

        System.out.println("verificar cliente");

        if (se.verificarCliente("304760577")) {
            System.out.println("encontrado");
        } else {
            System.out.println("no encontrado");
        }

        List<Cliente> clientes = se.obtenerListaClientes();
        System.out.println("Obtener lista cliente");

        clientes.forEach((e) -> {
            System.out.println(e);
        });
        System.out.println("INSERTAR");
//         DaoUsuario daousuario=DaoUsuario.obtenerInstancia();
//         daousuario.agregarUsuario(new Usuario("99999999","99999999",0,1));
//        se.agregarCliente(new Cliente("99999999", "99999999", "99999999", "99999999", "99999999"));

    }
}
