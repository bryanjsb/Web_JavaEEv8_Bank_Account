/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.moneda;

/**
 *
 * @author Bryan
 */
public class moneda {
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
    
    public moneda(){
        this("CRC","Colón","₡",1.0,1.0);
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
    
    public String mostrarMoneda(){
        StringBuilder s= new StringBuilder();
        s.append(String.format("%s %s:Compra:%f%n", nombreMoneda,simboloMoneda,
                tipoCambioCompra));
         s.append(String.format("%s %s:Venta:%f%n", nombreMoneda,simboloMoneda,
                tipoCambioVenta));
        
        return s.toString();
    }
    
}
