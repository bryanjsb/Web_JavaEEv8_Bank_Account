
package modelo.movimiento;

import java.util.Date;

public class movimiento{

    public movimiento(int id_movimiento, String num_cuenta, double monto, Date fecha, int aplicado, String movimientocol) {
        this.id_movimiento = id_movimiento;
        this.num_cuenta = num_cuenta;
        this.monto = monto;
        this.fecha = fecha;
        this.aplicado = aplicado;
        this.movimientocol = movimientocol;
    }

    public int getId_movimiento() {
        return id_movimiento;
    }

    public String getNum_cuenta() {
        return num_cuenta;
    }

    public double getMonto() {
        return monto;
    }

    public Date getFecha() {
        return fecha;
    }

    public int getAplicado() {
        return aplicado;
    }

    public String getMovimientocol() {
        return movimientocol;
    }

    @Override
    public String toString() {
        return "Movimiento{" + "id_movimiento=" + id_movimiento + ", num_cuenta=" + num_cuenta + ", monto=" + monto + ", fecha=" + fecha + ", aplicado=" + aplicado + ", movimientocol=" + movimientocol + '}';
    }
    
    
    
    private final int id_movimiento;
    private final String num_cuenta;
    private final double monto;
    private final Date fecha;
    private final int aplicado;
    private final String movimientocol;
    
    
}
