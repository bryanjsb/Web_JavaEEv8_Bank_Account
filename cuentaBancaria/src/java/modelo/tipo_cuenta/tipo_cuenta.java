package modelo.tipo_cuenta;

public class tipo_cuenta {

    public tipo_cuenta(int id_tipo_cuenta, String descripcion, double tasa_interes) {
        this.id_tipo_cuenta = id_tipo_cuenta;
        this.descripcion = descripcion;
        this.tasa_interes = tasa_interes;
    }

    public int getTipo_cuenta() {
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
        return "Tipo_cuenta{" + "tipo_cuenta=" + id_tipo_cuenta + ", descripcion=" + descripcion + ", tasa_interes=" + tasa_interes + '}';
    }
    
    
    
    private final int id_tipo_cuenta;
    private final String descripcion;
    private final double tasa_interes;
    
}
