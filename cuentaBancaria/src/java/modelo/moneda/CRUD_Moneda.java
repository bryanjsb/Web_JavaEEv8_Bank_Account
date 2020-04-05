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
public enum CRUD_Moneda {
     LISTAR("SELECT nombre, descripcion, simbolo,tipo_cambio_compra,tipo_cambio_venta"
             + " FROM moneda ORDER BY nombre; ");

     CRUD_Moneda(String comando) {
        this.comando = comando;
    }

    public String obtenerComando() {
        return comando;
    }

    private final String comando;
}
