package modelo.cuenta;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

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

    public String getNumero_cuenta() {
        return num_cuenta;
    }

    public int getTipo_cuenta_id_tipo_cuenta() {
        return tipo_cuenta_id_tipo_cuenta;
    }

    public String getId_cliente() {
        return cliente_id_cliente;
    }

    public String getMoneda_nombre() {
        return moneda_nombre;
    }

    public Date getFecha_creacion() {
        return fecha_creacion;
    }

    public double getLimite_transferencia() {
        return limite_transferencia_diaria;
    }

    public int getActiva() {
        return activa;
    }

    public double getSaldo_inicial() {
        return saldo_inicial;
    }

    public Date getFecha_ultima() {
        return fecha_ultima_aplicacion;
    }

    public double getSaldo_final() {
        return saldo_final;
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

    public Date getFecha_ultima_aplicacion() {
        return fecha_ultima_aplicacion;
    }

    public void setFecha_ultima_aplicacion(Date fecha_ultima_aplicacion) {
        this.fecha_ultima_aplicacion = fecha_ultima_aplicacion;
    }

    private final String num_cuenta;
    private final int tipo_cuenta_id_tipo_cuenta;
    private final String cliente_id_cliente;
    private final String moneda_nombre;
    private final Date fecha_creacion;
    private final double limite_transferencia_diaria;
    private final int activa;
    private final double saldo_inicial;
    private Date fecha_ultima_aplicacion;
    private final double saldo_final;

}
