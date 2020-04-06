package modelo.usuario;

import modelo.datos.BaseDatosBanco;
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

public class DaoUsuario {

    public boolean verificarUsuario(String usuario, String clave) {
        boolean encontrado = false;
        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CRUD_Usuario.VERIFICAR.obtenerComando())) {
            stm.clearParameters();
            stm.setString(1, usuario);
            stm.setString(2, clave);
            ResultSet rs = stm.executeQuery();
            encontrado = rs.next();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | IOException | SQLException ex) {
            Logger.getLogger(DaoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            bd.cerrarConexion();
        }
        return encontrado;
    }

    public Optional<Usuario> obtenerUsuario(String id) {
        Optional<Usuario> r = Optional.empty();
        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CRUD_Usuario.CONSULTAR.obtenerComando());) {
            stm.clearParameters();
            stm.setString(1, id);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    r = Optional.of(new Usuario(
                            rs.getString("id_usuario"),
                            rs.getString("clave_acceso"),
                            rs.getInt("clave_vencida"),
                            rs.getInt("rol")
                    ));
                }
            }
        } catch (IOException
                | ClassNotFoundException
                | IllegalAccessException
                | InstantiationException
                | SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }finally {
            bd.cerrarConexion();
        }
        return r;
    }

    public List<Usuario> obtenerListaUsuarios() {
        List<Usuario> r = new ArrayList<>();
        try (Connection cnx = obtenerConexion();
                Statement stm = cnx.createStatement();
                ResultSet rs = stm.executeQuery(CRUD_Usuario.LISTAR.obtenerComando())) {
            while (rs.next()) {
                Usuario e = new Usuario(
                        rs.getString("id_usuario"),
                        rs.getString("clave_acceso"),
                        rs.getInt("clave_vencida"),
                        rs.getInt("rol")
                );
                r.add(e);
            }
        } catch (IOException
                | ClassNotFoundException
                | IllegalAccessException
                | InstantiationException
                | SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }finally {
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

    

    private DaoUsuario() {
        try {
            this.bd = BaseDatosBanco.obtenerInstancia();
            bd.obtenerConexion();
        } catch (SQLException ex) {
            Logger.getLogger(DaoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static DaoUsuario obtenerInstancia() {
        if (instancia == null) {
            instancia = new DaoUsuario();
        }
        return instancia;
    }

    private static DaoUsuario instancia = null;
    private BaseDatosBanco bd = null;

    
    
    
    public static void main(String[] args) {
        DaoUsuario se = new DaoUsuario();
        Optional<Usuario> us = se.obtenerUsuario("3");

        System.out.println(us);
        System.out.println(us.get().getRol());
        List<Usuario> estudiantes = se.obtenerListaUsuarios();

        System.out.println("verificar");

        if (se.verificarUsuario("3", "3")) {
            System.out.println("si verifica");
        } else {
            System.out.println("no verifica");
        }

        estudiantes.forEach((e) -> {
            System.out.println(e);
        });

    }
}