package modelo.movimiento;

import java.util.Date;
import servicios.generadorClave;

public class movimiento implements java.io.Serializable {

    public movimiento(int id_movimiento, String num_cuenta, double monto,
            Date fecha, int aplicado, String movimientocol) {
        this.id_movimiento = id_movimiento;
        this.num_cuenta = num_cuenta;
        this.monto = monto;
        this.fecha = fecha;
        this.aplicado = aplicado;
        this.movimientocol = movimientocol;
        obtenerNumMovimConsecutivo();
    }

    public movimiento() {
        this(0, "", 0.0, null, 0, "");
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

    public static int obtenerNumMovimConsecutivo() {

        return ++numMovConsecutivo;
    }

    private static int numMovConsecutivo = 1;
    private final int id_movimiento;
    private final String num_cuenta;
    private final double monto;
    private final Date fecha;
    private final int aplicado;
    private final String movimientocol;

    //daos
    public static boolean verificarMovimiento(String idMovimiento) {
        DaoMovimiento d = DaoMovimiento.obtenerInstancia();
        return d.verificarMovimiento(idMovimiento);
    }

    private static String generarNumeros() {
        return String.format("%d%s%s", numMovConsecutivo, "0000", generadorClave.getPinNumber(3));
    }

    /**
     *
     * @return
     */
    public static int generarIdMovimientoUnico() {

        DaoMovimiento daoMov = DaoMovimiento.obtenerInstancia();
        String id = generarNumeros();

        if (!daoMov.verificarMovimiento(id)) {
            return Integer.parseInt(id);

        }
        return generarIdMovimientoUnico();
    }

    public static void agregarMovimiento(movimiento m) {
        DaoMovimiento daoMov = DaoMovimiento.obtenerInstancia();
        daoMov.agregarMovimiento(m);

    }

}
