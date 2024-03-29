package modelo.cliente;

public enum CRUD_Cliente {
    INSERTAR("INSERT INTO cliente (id_cliente,usuario_id_usuario,apellidos,nombre,telefono) VALUES (?,?,?,?,?); "),
    //    MODIFICAR("UPDATE usuario SET id_usuario=?, clave_acceso=?,clave_vencida=?,rol=? WHERE id_usuario=?; "),
    //    EXCLUIR("DELETE FROM usuario WHERE id_usuario=?; "),
    CONSULTAR("SELECT id_cliente,usuario_id_usuario,apellidos,nombre,telefono FROM cliente"
            + " WHERE id_cliente=? AND usuario_id_usuario=?; "),
    LISTAR("SELECT id_cliente, usuario_id_usuario,apellidos, nombre,telefono FROM cliente"
            + " ORDER BY id_cliente; "),
    VERIFICAR("SELECT id_cliente FROM cliente WHERE id_cliente=? ; ");

    CRUD_Cliente(String comando) {
        this.comando = comando;
    }

    public String obtenerComando() {
        return comando;
    }

    private final String comando;
}
