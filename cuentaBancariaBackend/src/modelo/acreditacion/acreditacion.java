/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.acreditacion;

import java.util.List;
import modelo.cuenta.DaoCuenta;
import modelo.cuenta.cuenta;
import modelo.movimiento.movimiento;
import modelo.tipo_cuenta.DaoTipo_cuenta;

/**
 *
 * @author Bryan
 */
public class acreditacion implements java.io.Serializable {

    private final DaoCuenta daoCuenta = DaoCuenta.obtenerInstancia();

    public acreditacion() {
    }

    private String obtenerListaCuentaHTML(List<cuenta> cuenta) {
        StringBuilder r = new StringBuilder();

        r.append("<table class=\"tabla\">");

        r.append("<thead>");
        r.append("<th width=\"250px\">Numero de Cuenta</th>");
        r.append("<th width=\"150px\">Tipo de Cuenta</th>");
        r.append("<th width=\"150px\">Cliente</th>");
        r.append("<th width=\"150px\">Moneda</th>");
        r.append("<th width=\"150px\">Estado</th>");
        r.append("<th width=\"150px\">Saldo</th>");
        r.append("</thead>");

        r.append("<tbody>");

        for (cuenta e : cuenta) {
            r.append("<tr>");
            r.append(String.format("<td width=\"250px\"><label>%s</label></td>", e.getNum_cuenta()));
            r.append(String.format("<td width=\"150px\"><label>%s</label></td>", e.getTipo_cuenta_id_tipo_cuenta()));
            r.append(String.format("<td width=\"150px\"><label>%s</label></td>", e.getCliente_id_cliente()));
            r.append(String.format("<td width=\"150px\"><label>%s</label></td>", e.getMoneda_nombre()));

            if (e.getActiva() == 1) {
                r.append(String.format("<td width=\"150px\"><label>%s</label></td>", "activa"));
            } else {
                r.append(String.format("<td width=\"150px\"><label>%s</label></td>", "inactiva"));
            }
            r.append(String.format("<td width=\"150px\"><label>%s</label></td>", e.getSaldo_final()));
            r.append("</tr>");
        }
        r.append("</tbody>");

        r.append("</table>");
        return r.toString();
    }

    public String mostrarCuentasPositivas() {
        return this.obtenerListaCuentaHTML(daoCuenta.obtenerListaCuentaSaldoPositivo());
    }

    public String mostrarCuentasInteresAplicado() {
        return this.obtenerListaCuentaHTML(aplicarIntereses());
    }

    private List<cuenta> aplicarIntereses() {
        List<cuenta> c = daoCuenta.obtenerListaCuentaSaldoPositivo();
        c.forEach((t) -> {
            t.acreditarIntereses();
        });
        return c;
    }

    public void hacerDeposito() {
        List<cuenta> c = aplicarIntereses();
        c.forEach((t) -> {
            cuenta.actualizarMonto(t);
            movimiento ptrMovim = new movimiento(movimiento.generarIdMovimientoUnico(),
                    t.getNum_cuenta(), t.getSaldo_final(), t.getFecha_ultima_aplicacion(), 1, "Intereses ganados");
            movimiento.agregarMovimiento(ptrMovim);
        });
    }
}
