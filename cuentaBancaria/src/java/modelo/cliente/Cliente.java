package modelo.cliente;

import java.util.List;
import modelo.cuenta.DaoCuenta;
import modelo.cuenta.cuenta;

public class Cliente implements java.io.Serializable {

    private String idCliente, usuarioIdUsuario, apellidos, nombre, telefono;

    public Cliente(String idCliente, String usuarioIdUsuario,
            String apellidos, String nombre, String telefono) {
        this.idCliente = idCliente;
        this.usuarioIdUsuario = usuarioIdUsuario;
        this.apellidos = apellidos;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public Cliente() {
        this("", "", "", "", "");
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getUsuarioIdUsuario() {
        return usuarioIdUsuario;
    }

    public void setUsuarioIdUsuario(String usuarioIdUsuario) {
        this.usuarioIdUsuario = usuarioIdUsuario;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String nombreCompleto() {
        return getNombre() + " " + getApellidos();
    }

    //metodos static 
    public static boolean verificarCliente(String codigoIngresado) {
        DaoCliente daoCliente = DaoCliente.obtenerInstancia();
        return daoCliente.verificarCliente(codigoIngresado);
    }

    public static Cliente obtenerCliente(String n) {

        DaoCliente d = DaoCliente.obtenerInstancia();
        return d.obtenerCliente(n).get();
    }

    public String listarCuentahtml() {
        StringBuilder r = new StringBuilder();
//        r.append("<table class=\"listaCuentaCliente\">");

        r.append("<thead>");
        r.append(String.format("<h2>Lista de la cuentas del cliente %s</h2>", nombreCompleto()));
        r.append("<th width=\"200px\">Seleccione cuenta</th>");
        r.append("<th width=\"200px\">numero de cuenta</th>");
        r.append("<th width=\"180px\">Tipo de Cuenta</th>");
        r.append("<th width=\"180px\">estado</th>");
        r.append("</thead>");

        r.append("<tbody>");
        List<cuenta> listaCuenta = daoCuenta.obtenerListaCuentaCliente(getIdCliente());
        for (cuenta e : listaCuenta) {
            r.append("<tr>");
            r.append(String.format("<td><input type=\"radio\" name=\"numCuentaCliente\" value=\"%s\"</td>", e.getNum_cuenta()));
            r.append(String.format("<td width=\"200px\"><label>%s</label></td>", e.getNum_cuenta()));
            r.append(String.format("<td width=\"180px\"><label>%s</label></td>", e.mostrarInfoCuentaDeposito().getDescripcion()));

            if (e.getActiva() == 1) {
                r.append(String.format("<td width=\"80px\"><label>%s</label></td>", "activa"));
            } else {
                r.append(String.format("<td width=\"80px\"><label>%s</label></td>", "inactiva"));
            }

            r.append("</tr>");
        }
        r.append("</tbody>");
//
//        r.append("</table>");
        return r.toString();

    }

    private static DaoCuenta daoCuenta = DaoCuenta.obtenerInstancia();
    private static DaoCliente daoCliente = DaoCliente.obtenerInstancia();
}
