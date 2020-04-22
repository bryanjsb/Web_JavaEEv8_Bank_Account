package modelo.movimiento;

public enum CRUD_Movimiento {
//    INSERTAR("INSERT INTO movimiento (id_movimiento, num_cuenta, monto, fecha, aplicado, movimientocol) VALUES (?, ?, ?, ?, ?, ?); "),
//    MODIFICAR("UPDATE movimiento SET id_movimiento=?, num_cuenta=?,monto=?,fecha=?,aplicado=?,movimientocol=? WHERE id_movimiento=?; "),
//    EXCLUIR("DELETE FROM movimiento WHERE id_movimiento=?; "),
    CONSULTAR("SELECT id_movimiento, num_cuenta, monto, fecha, aplicado, movimientocol FROM movimiento"
            + " WHERE id_movimiento=?; "),
    LISTAR("SELECT id_movimiento, num_cuenta, monto, fecha, aplicado, movimientocol FROM movimiento"
            + " ORDER BY id_movimiento; "),
    VERIFICAR("SELECT id_movimiento FROM movimiento WHERE id_movimiento=?; ");

    CRUD_Movimiento(String comando) {
        this.comando = comando;
    }

    public String obtenerComando() {
        return comando;
    }

    private final String comando;
}
