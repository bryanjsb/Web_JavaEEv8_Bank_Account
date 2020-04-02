package modelo.dao;

public enum IMEC_Usuario {
    INSERTAR("INSERT INTO usuario (id_usuario, clave_acceso, clave_vencida,rol) VALUES (?, ?, ?,?); "),
    MODIFICAR("UPDATE usuario SET id_usuario=?, clave_acceso=?,clave_vencida=?,rol=? WHERE id_usuario=?; "),
  
    EXCLUIR("DELETE FROM usuario WHERE id_usuario=?; "),
    CONSULTAR("SELECT id_usuario, clave_acceso,clave_vencida, rol FROM usuario WHERE id_usuario=? "),
    LISTAR("SELECT id_usuario, clave_acceso, clave_vencida,rol FROM usuario ORDER BY id_usuario; ");

    IMEC_Usuario(String comando) {
        this.comando = comando;
    }

    public String obtenerComando() {
        return comando;
    }

    private final String comando;
}
