package modelo.principal;

import java.util.List;
import modelo.cliente.DaoCliente;
import modelo.cuenta.DaoCuenta;
import modelo.moneda.DaoMoneda;
import modelo.movimiento.DaoMovimiento;
import modelo.tipo_cuenta.DaoTipo_cuenta;
import modelo.transferencia.DaoTransferencia;
import modelo.transferencia.transferencia;
import modelo.usuario.DaoUsuario;
import servicios.ServicioFecha;
import servicios.generadorClave;

public class model {

    private static model instancia = null;
    private final DaoCuenta daoCuenta;

    private final DaoMoneda daoMoneda;
    private final DaoCliente daoCliente;

    private final DaoTipo_cuenta daoTipo_cuenta;

    private final DaoMovimiento daoMovimiento;
    private final DaoTransferencia dt;

    private final DaoUsuario daoUsuario;

    private static int numMovConsecutivoTransferencia = 1;

    //constructor
    private model() {
        daoCuenta = DaoCuenta.obtenerInstancia();
        daoMoneda = DaoMoneda.obtenerInstancia();
        daoCliente = DaoCliente.obtenerInstancia();
        daoTipo_cuenta = DaoTipo_cuenta.obtenerInstancia();
        daoMovimiento = DaoMovimiento.obtenerInstancia();
        dt = DaoTransferencia.obtenerInstancia();
        daoUsuario = DaoUsuario.obtenerInstancia();
    }

    public static model obtenerInstancia() {
        if (instancia == null) {
            instancia = new model();
        }
        return instancia;
    }

    //DaoTransferencia
    public boolean agregarTransferencia(transferencia t) {
        return dt.agregarCuenta(t);
    }

    public boolean verificarTransferencia(int n) {
        return dt.verificarTransferencia(n);
    }

    public boolean verificarTransferencia(transferencia n) {
        return dt.verificarTransferencia(n.getId_transferencia());
    }

    public List<transferencia> obtenerListaTransferencia() {
        return dt.obtenerListaTransferencia();
    }

    public transferencia obtenerTransferencia(int n) {
        return dt.obtenerTransferencia(n).get();
    }

    private String generarNumerosTransfe() {
        return String.format("%d%s%s", ++numMovConsecutivoTransferencia, "0000", generarNumeros(3));
    }

    public int generarIdTransferenciaUnico() {

        int id = Integer.parseInt(generarNumerosTransfe());

        if (!dt.verificarTransferencia(id)) {
            return (id);

        }
        return generarIdTransferenciaUnico();
    }

    public static String generarNumeroCuentaAuto(String moneda) {
        StringBuilder s = new StringBuilder();

        s.append(String.format("%s-%s-%s-%s-%s", ServicioFecha.AñoActual(),
                moneda, generarNumeros(3), generarNumeros(6), generarNumeros(1)));
        return s.toString();
    }

    public static String generarNumeros(int len) {
        return generadorClave.getPinNumber(len);
    }

}
