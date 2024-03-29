package modelo.moneda;

import java.util.List;

public class moneda implements java.io.Serializable {

    private final String nombreMoneda;
    private final String descripcion;
    private final String simboloMoneda;
    private final double tipoCambioCompra;
    private final double tipoCambioVenta;

    public moneda(String nombreMoneda, String descripcion, String simboloMoneda, double tipoCambioCompra, double tipoCambioVenta) {
        this.nombreMoneda = nombreMoneda;
        this.descripcion = descripcion;
        this.simboloMoneda = simboloMoneda;
        this.tipoCambioCompra = tipoCambioCompra;
        this.tipoCambioVenta = tipoCambioVenta;
    }

    public moneda() {
        this("CRC", "Colón", "₡", 1.0, 1.0);
    }

    public String getNombreMoneda() {
        return nombreMoneda;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getSimboloMoneda() {
        return simboloMoneda;
    }

    public double getTipoCambioCompra() {
        return tipoCambioCompra;
    }

    public double getTipoCambioVenta() {
        return tipoCambioVenta;
    }

    @Override
    public String toString() {
        return "moneda{" + "nombreMoneda=" + nombreMoneda + ", descripcion=" + descripcion + ", simboloMoneda=" + simboloMoneda + ", tipoCambioCompra=" + tipoCambioCompra + ", tipoCambioVenta=" + tipoCambioVenta + '}';
    }

    public String mostrarMoneda() {
        StringBuilder s = new StringBuilder();
        s.append(String.format("%s %s:Compra:%f%n", nombreMoneda, simboloMoneda,
                tipoCambioCompra));
        s.append(String.format("%s %s:Venta:%f%n", nombreMoneda, simboloMoneda,
                tipoCambioVenta));

        return s.toString();
    }

    public List<moneda> obtenerListaMoneda() {
        DaoMoneda daomoneda = DaoMoneda.obtenerInstancia();
        return daomoneda.obtenerListaMoneda();
    }

    public String mostrarListaMonedaJSP() {
        StringBuilder s = new StringBuilder();
        List<moneda> listaMoneda = this.obtenerListaMoneda();
        if (listaMoneda.isEmpty()) {

            s.append("<select name=\"moneda\" >");
            s.append("<option selected value=\"0\"> no existe tipo moneda </option>");
            s.append("  </select>");
        } else {
            s.append("<select name=\"moneda\" >");
            s.append("<option selected value=\"0\"> Elige tipo de moneda </option>");

            listaMoneda.forEach((m) -> {
                s.append(String.format(" <option value=\"" + m.getNombreMoneda() + "\">"
                        + m.getDescripcion() + " - " + m.getNombreMoneda() + "  "
                        + m.getSimboloMoneda() + "</option> "));
            });

            s.append("  </select>");

        }
        return s.toString();
    }

    public static moneda obtenerMoneda(String n) {
        DaoMoneda d = DaoMoneda.obtenerInstancia();
        return d.obtenerMoneda(n).get();

    }

    public static String mostrarListaMonedaStatic() {
        DaoMoneda daomoneda = DaoMoneda.obtenerInstancia();
        StringBuilder s = new StringBuilder();
        List<moneda> listaMoneda = daomoneda.obtenerListaMoneda();
        if (listaMoneda.isEmpty()) {

            s.append("<select name=\"moneda\" >");
            s.append("<option selected value=\"0\"> no existe tipo moneda </option>");
            s.append("  </select>");
        } else {
            s.append("<select name=\"moneda\" >");
            s.append("<option selected value=\"0\"> Elige tipo de moneda </option>");

            listaMoneda.forEach((m) -> {
                s.append(String.format(" <option value=\"" + m.getNombreMoneda() + "\">"
                        + m.getDescripcion() + " - " + m.getNombreMoneda() + "  "
                        + "  " + " Compra: " + m.getSimboloMoneda()
                        + m.getTipoCambioCompra() + " Venta: " + m.getSimboloMoneda()
                        + m.getTipoCambioVenta()
                        + "</option> "));
            });

            s.append("  </select>");

        }
        return s.toString();
    }

}
