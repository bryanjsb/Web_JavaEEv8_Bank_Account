
package modelo.favorita;


public class favorita {

    public favorita(String cliente_id_cliente, String cuenta_num_cuenta) {
        this.cliente_id_cliente = cliente_id_cliente;
        this.cuenta_num_cuenta = cuenta_num_cuenta;
    }

    public String getCliente_id_cliente() {
        return cliente_id_cliente;
    }

    public String getCuenta_num_cuenta() {
        return cuenta_num_cuenta;
    }

    @Override
    public String toString() {
        return "favorita{" + "cliente_id_cliente=" + cliente_id_cliente + ", cuenta_num_cuenta=" + cuenta_num_cuenta + '}';
    }
       
    
    private final String cliente_id_cliente;
    private final String cuenta_num_cuenta;
}
