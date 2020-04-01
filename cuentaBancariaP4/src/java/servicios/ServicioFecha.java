package servicios;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public abstract class ServicioFecha {

    public static String fechaActual() {
        DateFormat fmt = new SimpleDateFormat("dd 'de' MMMM, yyyy", new Locale("es", "CR"));
        java.util.Date fecha = Calendar.getInstance().getTime();
        return fmt.format(fecha);
    }

}
