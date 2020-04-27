package modelo.movimiento;

import java.util.Date;
import java.util.List;
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

    public static void setNumMovConsecutivo(int numMovConsecutivo) {
        movimiento.numMovConsecutivo = numMovConsecutivo;
    }

    public void setId_movimiento(int id_movimiento) {
        this.id_movimiento = id_movimiento;
    }

    public void setNum_cuenta(String num_cuenta) {
        this.num_cuenta = num_cuenta;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setAplicado(int aplicado) {
        this.aplicado = aplicado;
    }

    public void setMovimientocol(String movimientocol) {
        this.movimientocol = movimientocol;
    }
    


    @Override
    public String toString() {
        return "Movimiento{" + "id_movimiento=" + id_movimiento + ", num_cuenta=" + num_cuenta + ", monto=" + monto + ", fecha=" + fecha + ", aplicado=" + aplicado + ", movimientocol=" + movimientocol + '}';
    }

    public static int obtenerNumMovimConsecutivo() {

        return ++numMovConsecutivo;
    }

    private static int numMovConsecutivo = 1;
    private  int id_movimiento;
    private  String num_cuenta;
    private  double monto;
    private  Date fecha;
    private  int aplicado;
    private  String movimientocol;

    //daos
    public static boolean verificarMovimiento(String idMovimiento) {
        DaoMovimiento d = DaoMovimiento.obtenerInstancia();
        return d.verificarMovimiento(idMovimiento);
    }

    private static String generarNumeros() {
        return String.format("%d%s%s", numMovConsecutivo, "0000", generadorClave.getPinNumber(3));
    }

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
    
    public static void obtenerMovimiento(String m) {
        DaoMovimiento daoMov = DaoMovimiento.obtenerInstancia();
        daoMov.obtenerMovimiento(m);

    }
    
    public List<movimiento> obtenerListaMovimiento(String m) {
        DaoMovimiento daoMov = DaoMovimiento.obtenerInstancia();
        return daoMov.obtenerListaMovimientos(m);
    }

     public String listarCuentaVerMovimientos() {
         return this.listarCuentaVerMovimientos(getNum_cuenta());
     }
     public String listarCuentaVerMovimientos(String m) {
        StringBuilder r = new StringBuilder();
        r.append("<table class=\"tabla\">");
        r.append(String.format("<h2>Lista de movimientos de la cuenta %s</h2>", m));
        r.append("<thead>");

        r.append("<th width=\"150px\">ID Movimiento</th>");
        r.append("<th width=\"180px\">numero de cuenta</th>");
        r.append("<th width=\"100px\">monto</th>");
        r.append("<th width=\"100px\">fecha</th>");
        r.append("<th width=\"100px\">aplicado</th>");
        r.append("<th width=\"200px\">motivo</th>");
        r.append("</thead>");

        r.append("<tbody>");
        List<movimiento> lista = obtenerListaMovimiento(m);
        for (movimiento e : lista) {
            r.append("<tr>");
//            r.append(String.format("<td width=\"180px\"><input type=\"button\" name=\"ClienteMov\" value=\"%s\"</td>","ver Movimientos"));
            
            r.append(String.format("<td width=\"150px\"><label>%s</label></td>", e.getId_movimiento()));
            r.append(String.format("<td width=\"180px\"><label>%s</label></td>", e.getNum_cuenta()));

            r.append(String.format("<td width=\"100px\"><label>%s</label></td>", e.getMonto()));
            r.append(String.format("<td width=\"100px\"><label>%s</label></td>", e.getFecha()));
            if(e.getAplicado()==1){
            r.append(String.format("<td width=\"100px\"><label>%s</label></td>", "APLICADO"));
            
            }else{
            r.append(String.format("<td width=\"100px\"><label>%s</label></td>", "NO APLICADO"));
            }
            r.append(String.format("<td width=\"200px\" height=\"100px\"><label>%s</label></td>", e.getMovimientocol()));
            r.append("</tr>");
           
        }
        
        r.append("</tbody>");

        r.append("</table>");
        return r.toString();

    }
}
