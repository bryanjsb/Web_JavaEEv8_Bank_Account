package servicios;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public abstract class ServicioFecha implements java.io.Serializable {

    public static String fechaActual() {
        DateFormat fmt = new SimpleDateFormat("dd 'de' MMMM, yyyy", new Locale("es", "CR"));
        java.util.Date fecha = Calendar.getInstance().getTime();
        return fmt.format(fecha);
    }

    public static Date fechaActualCuenta() {

        java.util.Date fecha = Calendar.getInstance().getTime();
        return fecha;
    }

    public static String HoraActual() {
        DateFormat fmt = new SimpleDateFormat("HH:mm:ss.SSS", new Locale("es", "CR"));
        java.util.Date fecha = Calendar.getInstance().getTime();
        return fmt.format(fecha);
    }

    public static String AñoActual() {
        DateFormat fmt = new SimpleDateFormat("yyyy", new Locale("es", "CR"));
        java.util.Date año = Calendar.getInstance().getTime();
        return fmt.format(año);
    }
}
