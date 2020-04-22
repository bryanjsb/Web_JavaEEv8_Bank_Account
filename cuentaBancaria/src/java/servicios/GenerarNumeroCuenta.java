package servicios;

public class GenerarNumeroCuenta implements java.io.Serializable {

    public static String generarNumeroCuentaAuto(String moneda) {
        StringBuilder s = new StringBuilder();

        s.append(String.format("%s-%s-%s-%s-%s", ServicioFecha.AñoActual(),
                moneda, generarNumeros(3), generarNumeros(6), generarNumeros(1)));
        return s.toString();
    }

    public static String generarNumeros(int len) {
        return generadorClave.getPinNumber(len);
    }

    public static void main(String[] args) {

        System.err.println("numero de cuenta");
        System.out.println(generarNumeroCuentaAuto("CRC"));

        System.err.println("numero de cuenta");
        System.out.println(generarNumeroCuentaAuto("USD"));

    }
}
