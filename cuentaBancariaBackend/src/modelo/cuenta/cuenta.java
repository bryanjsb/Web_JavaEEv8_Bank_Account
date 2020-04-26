package modelo.cuenta;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import modelo.cliente.Cliente;
import modelo.cliente.DaoCliente;
import modelo.moneda.moneda;
import modelo.tipo_cuenta.tipo_cuenta;

public class cuenta implements java.io.Serializable {

    public cuenta(String num_cuenta, int tipo_cuenta_id_tipo_cuenta,
            String cliente_id_cliente, String moneda_nombre,
            Date fecha_creacion, double limite_transferencia_diaria,
            int activa, double saldo_inicial, Date fecha_ultima_aplicacion, double saldo_final) {
        this.num_cuenta = num_cuenta;
        this.tipo_cuenta_id_tipo_cuenta = tipo_cuenta_id_tipo_cuenta;
        this.cliente_id_cliente = cliente_id_cliente;
        this.moneda_nombre = moneda_nombre;
        this.fecha_creacion = fecha_creacion;
        this.limite_transferencia_diaria = limite_transferencia_diaria;
        this.activa = activa;
        this.saldo_inicial = saldo_inicial;
        this.fecha_ultima_aplicacion = fecha_ultima_aplicacion;
        this.saldo_final = saldo_final;
    }

    public cuenta() {
        this("", 0, "", "", new Date(), 0.0, 0, 0.0, new Date(), 0.0);
    }

    public String getNum_cuenta() {
        return num_cuenta;
    }

    public void setNum_cuenta(String num_cuenta) {
        this.num_cuenta = num_cuenta;
    }

    public int getTipo_cuenta_id_tipo_cuenta() {
        return tipo_cuenta_id_tipo_cuenta;
    }

    public void setTipo_cuenta_id_tipo_cuenta(int tipo_cuenta_id_tipo_cuenta) {
        this.tipo_cuenta_id_tipo_cuenta = tipo_cuenta_id_tipo_cuenta;
    }

    public String getCliente_id_cliente() {
        return cliente_id_cliente;
    }

    public void setCliente_id_cliente(String cliente_id_cliente) {
        this.cliente_id_cliente = cliente_id_cliente;
    }

    public String getMoneda_nombre() {
        return moneda_nombre;
    }

    public void setMoneda_nombre(String moneda_nombre) {
        this.moneda_nombre = moneda_nombre;
    }

    public Date getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(Date fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public double getLimite_transferencia_diaria() {
        return limite_transferencia_diaria;
    }

    public void setLimite_transferencia_diaria(double limite_transferencia_diaria) {
        this.limite_transferencia_diaria = limite_transferencia_diaria;
    }

    public int getActiva() {
        return activa;
    }

    public void setActiva(int activa) {
        this.activa = activa;
    }

    public double getSaldo_inicial() {
        return saldo_inicial;
    }

    public void setSaldo_inicial(double saldo_inicial) {
        this.saldo_inicial = saldo_inicial;
    }

    public Date getFecha_ultima_aplicacion() {
        return fecha_ultima_aplicacion;
    }

    public void setFecha_ultima_aplicacion(Date fecha_ultima_aplicacion) {
        this.fecha_ultima_aplicacion = fecha_ultima_aplicacion;
    }

    public double getSaldo_final() {
        return saldo_final;
    }

    public void setSaldo_final(double saldo_final) {
        this.saldo_final = saldo_final;
    }

    @Override
    public String toString() {
        return "Cuenta{" + "numero_cuenta=" + num_cuenta + ", tipo_cuenta=" + tipo_cuenta_id_tipo_cuenta + ", id_cliente=" + cliente_id_cliente + ", moneda_nombre=" + moneda_nombre + ", fecha_creacion=" + fecha_creacion + ", limite_transferencia=" + limite_transferencia_diaria + ", activa=" + activa + ", saldo_inicial=" + saldo_inicial + ", fecha_ultima=" + fecha_ultima_aplicacion + ", saldo_final=" + saldo_final + '}';
    }

    public void actualizarfechaUltimaAplicacion() {
        DateFormat fmt = new SimpleDateFormat("dd 'de' MMMM, yyyy", new Locale("es", "CR"));
        java.util.Date fecha = Calendar.getInstance().getTime();
        fecha_ultima_aplicacion = fecha;
    }

    private String num_cuenta;
    private int tipo_cuenta_id_tipo_cuenta;
    private String cliente_id_cliente;
    private String moneda_nombre;
    private Date fecha_creacion;
    private double limite_transferencia_diaria;
    private int activa;
    private double saldo_inicial;
    private Date fecha_ultima_aplicacion;
    private double saldo_final;

    private static DaoCuenta daoCuenta = DaoCuenta.obtenerInstancia();
    private static DaoCliente daoCliente = DaoCliente.obtenerInstancia();

    //metodos static
    public static boolean verificarCuenta(String n) {
        daoCuenta = DaoCuenta.obtenerInstancia();

        return daoCuenta.verificarCuenta(n);
    }

    public static cuenta obtenerCuenta(String n) {
        daoCuenta = DaoCuenta.obtenerInstancia();
        return daoCuenta.obtenerNumeroCuenta(n).get();
    }

    public static List<cuenta> obtenerListaCuentaCliente(String n) {
        daoCuenta = DaoCuenta.obtenerInstancia();
        return daoCuenta.obtenerListaCuentaCliente(n);
    }

    public tipo_cuenta mostrarInfoCuentaDeposito() {
        return tipo_cuenta.obtenerTipoCuenta(getTipo_cuenta_id_tipo_cuenta());
    }

    public tipo_cuenta mostrarInfoCuentaRetiro() {
        return tipo_cuenta.obtenerTipoCuenta(getTipo_cuenta_id_tipo_cuenta());
    }

    public moneda obtenerMoneda() {
        return moneda.obtenerMoneda(getMoneda_nombre());

    }

    public Cliente obtenerCliente() {
        DaoCliente d = DaoCliente.obtenerInstancia();
        return d.obtenerCliente(getCliente_id_cliente()).get();
    }

    public String nombreCompletoCliente() {
        return obtenerCliente().nombreCompleto();
    }

    public static void actualizarMonto(cuenta n) {
        daoCuenta = DaoCuenta.obtenerInstancia();
        daoCuenta.modificarCuenta(n);
    }

    public void realizarDeposito(double n) {
        setSaldo_inicial((double) getSaldo_final());
        setSaldo_final((double) getSaldo_final() + (double) n);
        cambiarHoraUltimoMovimiento();
    }

    private void cambiarHoraUltimoMovimiento() {
        this.fecha_ultima_aplicacion = servicios.ServicioFecha.fechaActualCuenta();
    }

    public void realizarRetiro(Double n) {
        setSaldo_inicial((double) getSaldo_final());
        setSaldo_final((double) getSaldo_final() - (double) n);
        cambiarHoraUltimoMovimiento();
    }

}
