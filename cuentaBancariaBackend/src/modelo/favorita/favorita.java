package modelo.favorita;

public class favorita implements java.io.Serializable {

    public favorita(String cliente_id_cliente, String cuenta_num_cuenta) {
        this.cliente_id_cliente = cliente_id_cliente;
        this.cuenta_num_cuenta = cuenta_num_cuenta;
    }

    public favorita() {
        this("", "");
    }

    public String getCliente_id_cliente() {
        return cliente_id_cliente;
    }

    public String getCuenta_num_cuenta() {
        return cuenta_num_cuenta;
    }

    public void setCliente_id_cliente(String cliente_id_cliente) {
        this.cliente_id_cliente = cliente_id_cliente;
    }

    public void setCuenta_num_cuenta(String cuenta_num_cuenta) {
        this.cuenta_num_cuenta = cuenta_num_cuenta;
    }

    @Override
    public String toString() {
        return "favorita{" + "cliente_id_cliente=" + cliente_id_cliente + ", cuenta_num_cuenta=" + cuenta_num_cuenta + '}';
    }

    private String cliente_id_cliente;
    private String cuenta_num_cuenta;
    private final DaoFavorita daoFavorita = DaoFavorita.obtenerInstancia();

    public void agregarCuentaFavorita() {
//        if(verificarFavorita(cliente_id_cliente, cuenta_num_cuenta)){
        daoFavorita.agregarFavorita(this);
//        }
//        
    }

    public void agregarCuentaFavorita(favorita f) {
        daoFavorita.agregarFavorita(f);
    }

    public boolean verificarFavorita(String n, String m) {
        return daoFavorita.verificarFavorita(n, m);
    }

}
