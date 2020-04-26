package modelo.transferencia;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import servicios.generadorClave;

public class transferencia implements Serializable {

    public transferencia(int id_transferencia, String cuenta_origen,
            String cuenta_destino, String monto, Date fecha, int aplicado) {
        this.id_transferencia = id_transferencia;
        this.cuenta_origen = cuenta_origen;
        this.cuenta_destino = cuenta_destino;
        this.monto = monto;
        this.fecha = fecha;
        this.aplicado = aplicado;
    }

    public transferencia() {
        this(0, "", "", "", null, 0);
    }

    public int getId_transferencia() {
        return id_transferencia;
    }

    public String getCuenta_origen() {
        return cuenta_origen;
    }

    public String getCuenta_destino() {
        return cuenta_destino;
    }

    public String getMonto() {
        return monto;
    }

    public Date getFecha() {
        return fecha;
    }

    public int getAplicado() {
        return aplicado;
    }

    @Override
    public String toString() {
        return "Transferencia{" + "id_transferencia=" + id_transferencia + ", cuenta_origen=" + cuenta_origen + ", cuenta_destino=" + cuenta_destino + ", monto=" + monto + ", fecha=" + fecha + ", aplicado=" + aplicado + '}';
    }

    private final int id_transferencia;
    private final String cuenta_origen;
    private final String cuenta_destino;
    private final String monto;
    private final Date fecha;
    private final int aplicado;
}
