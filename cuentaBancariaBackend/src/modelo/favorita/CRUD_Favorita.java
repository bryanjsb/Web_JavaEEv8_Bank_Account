package modelo.favorita;

public enum CRUD_Favorita {
    INSERTAR("INSERT INTO favorita (cliente_id_cliente, cuenta_num_cuenta) VALUES (?, ?); "),
    //    MODIFICAR("UPDATE cuenta SET num_cuenta=?,tipo_cuenta_id_tipo_cuenta=?,"
    //            + " cliente_id_cliente=?, moneda_nombre=?, fecha_creacion=?,"
    //            + " limite_transferencia_diaria=? ,activa=?, saldo_inicial=?,"
    //            + " fecha_ultima_aplicacion=?, saldo_final=? WHERE num_cuenta=?; "),
    //    EXCLUIR("DELETE FROM cuenta WHERE num_cuenta=?; "),
    //    CONSULTAR("SELECT num_cuenta, tipo_cuenta_id_tipo_cuenta, cliente_id_cliente, moneda_nombre, fecha_creacion, limite_transferencia_diaria, activa, saldo_inicial, fecha_ultima_aplicacion, saldo_final FROM cuenta"
    //            + " WHERE num_cuenta=?; "),
    //    CONSULTARCLIENTE("SELECT num_cuenta, tipo_cuenta_id_tipo_cuenta, cliente_id_cliente, moneda_nombre, fecha_creacion, limite_transferencia_diaria, activa, saldo_inicial, fecha_ultima_aplicacion, saldo_final FROM cuenta"
    //            + " WHERE cliente_id_cliente=?; "),
    //    LISTARSALDOPOSITIVO("SELECT num_cuenta, tipo_cuenta_id_tipo_cuenta, "
    //            + "cliente_id_cliente, moneda_nombre, fecha_creacion, "
    //            + "limite_transferencia_diaria, activa, saldo_inicial,"
    //            + " fecha_ultima_aplicacion, saldo_final FROM cuenta"
    //            + " WHERE saldo_final>0 ORDER BY num_cuenta; "),
    LISTAR("SELECT cliente_id_cliente, cuenta_num_cuenta FROM favorita"
            + " ORDER BY cliente_id_cliente; "),
    VERIFICAR("SELECT cliente_id_cliente, cuenta_num_cuenta "
            + "FROM favorita WHERE cliente_id_cliente=? "
            + "AND cuenta_num_cuenta=?");

    CRUD_Favorita(String comando) {
        this.comando = comando;
    }

    public String obtenerComando() {
        return comando;
    }

    private final String comando;
}
