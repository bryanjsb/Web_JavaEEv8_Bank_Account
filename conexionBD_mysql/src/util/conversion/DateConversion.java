package util.conversion;

public class DateConversion {

    public static java.sql.Date util2sql(java.util.Date d) {

        // Esta conversión pierde el componente de tiempo (hora y minutos)
        // del parámetro.
        return new java.sql.Date(d.getTime());
    }

    public static java.sql.Timestamp util2Timestamp(java.util.Date d) {
        return new java.sql.Timestamp(d.getTime());
    }

    public static java.util.Date sql2Util(java.sql.Date sd) {
        return new java.util.Date(sd.getTime());
    }

    public static java.util.Date timestamp2Util(java.sql.Timestamp ts) {
        return new java.util.Date(ts.getTime());
    }

}
