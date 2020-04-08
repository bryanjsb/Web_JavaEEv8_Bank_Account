package modelo.tipo_cuenta;

public enum CRUD_Tipo_cuenta {
//    INSERTAR("INSERT INTO tipo_cuenta (id_tipo_cuenta, descripcion, tasa_interes) VALUES (?, ?, ?); "),
//    MODIFICAR("UPDATE tipo_cuenta SET id_tipo_cuenta=?, descripcion=?,tasa_interes=? WHERE id_tipo_cuenta=?; "),
//    EXCLUIR("DELETE FROM tipo_cuenta WHERE id_tipo_cuenta=?; "),
    CONSULTAR("SELECT id_tipo_cuenta, descripción, tasa_interés FROM tipo_cuenta"
            + " WHERE id_tipo_cuenta=?; "),
    LISTAR("SELECT id_tipo_cuenta, descripción, tasa_interés FROM tipo_cuenta"
            + " ORDER BY id_tipo_cuenta; "),
    VERIFICAR("SELECT id_tipo_cuenta FROM tipo_cuenta WHERE id_tipo_cuenta=?; ");

    CRUD_Tipo_cuenta(String comando) {
        this.comando = comando;
    }

    public String obtenerComando() {
        return comando;
    }

    private final String comando;
}
