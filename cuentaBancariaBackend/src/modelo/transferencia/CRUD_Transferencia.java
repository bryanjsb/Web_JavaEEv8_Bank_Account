package modelo.transferencia;

public enum CRUD_Transferencia {
    INSERTAR("INSERT INTO transferencia (id_transferencia, cuenta_origen, cuenta_destino, monto, fecha, aplicado)"
            + " VALUES (?, ?, ?, ?, ?, ?); "),
    //    MODIFICAR("UPDATE transferencia SET id_transferencia=?, cuenta_origen=?,cuenta_destino=?, monto=?, fecha=?, aplicado=? WHERE id_transferencia=?; "),
    //    EXCLUIR("DELETE FROM transferencia WHERE id_transferencia=?; "),
    CONSULTAR("SELECT id_transferencia, cuenta_origen, cuenta_destino, monto, fecha, aplicado FROM transferencia"
            + " WHERE id_transferencia=?; "),
    LISTAR("SELECT id_transferencia, cuenta_origen, cuenta_destino, monto, fecha, aplicado FROM transferencia"
            + " ORDER BY id_transferencia; "),
    VERIFICAR("SELECT id_transferencia FROM transferencia WHERE id_transferencia=?; ");

    CRUD_Transferencia(String comando) {
        this.comando = comando;
    }

    public String obtenerComando() {
        return comando;
    }

    private final String comando;
}
