package modelo.moneda;

public enum CRUD_Moneda {
    CONSULTAR("SELECT nombre, descripcion, simbolo,tipo_cambio_compra,tipo_cambio_venta"
            + " FROM moneda WHERE nombre=? "),
    LISTAR("SELECT nombre, descripcion, simbolo,tipo_cambio_compra,tipo_cambio_venta"
            + " FROM moneda ORDER BY nombre; ");

    CRUD_Moneda(String comando) {
        this.comando = comando;
    }

    public String obtenerComando() {
        return comando;
    }

    private final String comando;
}
