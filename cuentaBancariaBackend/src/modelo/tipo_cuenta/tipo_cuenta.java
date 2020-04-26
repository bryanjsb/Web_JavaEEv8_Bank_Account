package modelo.tipo_cuenta;

import java.util.List;

public class tipo_cuenta implements java.io.Serializable {

    public tipo_cuenta(int id_tipo_cuenta, String descripcion, double tasa_interes) {
        this.id_tipo_cuenta = id_tipo_cuenta;
        this.descripcion = descripcion;
        this.tasa_interes = tasa_interes;
    }

    public tipo_cuenta() {
        this(0, "", 0.0);
    }

    public int getId_tipo_cuenta() {
        return id_tipo_cuenta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getTasa_interes() {
        return tasa_interes;
    }

    @Override
    public String toString() {
        return "(" + id_tipo_cuenta + ") " + descripcion + " " + tasa_interes + '%';
    }

    public List<tipo_cuenta> obtenerListaTipoCuenta() {
        DaoTipo_cuenta dao = DaoTipo_cuenta.obtenerInstancia();
        return dao.obtenerListaTipo_cuenta();
    }

    public String mostrarListaTipoCuentaJSP() {
        StringBuilder s = new StringBuilder();
        List<tipo_cuenta> listaTipoCuenta = this.obtenerListaTipoCuenta();
        if (listaTipoCuenta.isEmpty()) {

            s.append("<select name=\"tipoCuenta\" >");
            s.append("<option selected value=\"0\"> no existe tipo cuenta </option>");
            s.append("  </select>");
        } else {
            s.append("<select name=\"tipoCuenta\" >");
            s.append("<option selected value=\"0\"> Elige tipo de cuenta </option>");

            listaTipoCuenta.forEach((m) -> {
                s.append(String.format(" <option value=\"" + m.getId_tipo_cuenta() + "\">"
                        + m.getId_tipo_cuenta() + " - " + m.getDescripcion() + " - " + m.getTasa_interes() + "</option> "));
            });

            s.append("  </select>");

        }
        return s.toString();
    }
    private final int id_tipo_cuenta;
    private final String descripcion;
    private final double tasa_interes;

    static public tipo_cuenta obtenerTipoCuenta(int n) {
        DaoTipo_cuenta dao = DaoTipo_cuenta.obtenerInstancia();
        return dao.obtenerTipo_cuenta(n).get();
    }

    public static List<tipo_cuenta> obtenerListaTipoCuentaStatic() {
        DaoTipo_cuenta dao = DaoTipo_cuenta.obtenerInstancia();
        return dao.obtenerListaTipo_cuenta();
    }

    public static boolean verificarTipoCuenta(int n) {

        DaoTipo_cuenta dao = DaoTipo_cuenta.obtenerInstancia();
        return dao.verificarTipo_cuenta(n);

    }
}
