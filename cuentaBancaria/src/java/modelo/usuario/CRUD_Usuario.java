package modelo.usuario;

public enum CRUD_Usuario {
    INSERTAR("INSERT INTO usuario (id_usuario,clave_acceso,clave_vencida,rol) VALUES (?,?,?,?); "),
    MODIFICAR("UPDATE usuario SET id_usuario=?, clave_acceso=?,clave_vencida=?,rol=? WHERE id_usuario=?; "),
    EXCLUIR("DELETE FROM usuario WHERE id_usuario=?; "),
    CONSULTAR("SELECT id_usuario, clave_acceso,clave_vencida, rol FROM usuario WHERE id_usuario=? "),
    LISTAR("SELECT id_usuario, clave_acceso, clave_vencida,rol FROM usuario ORDER BY id_usuario; "),
    VERIFICAR("SELECT id_usuario FROM usuario WHERE id_usuario=? AND clave_acceso=?; ");

    CRUD_Usuario(String comando) {
        this.comando = comando;
    }

    public String obtenerComando() {
        return comando;
    }

    private final String comando;
}
