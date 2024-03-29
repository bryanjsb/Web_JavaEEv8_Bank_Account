package servicios;

public class generadorClave implements java.io.Serializable {

    public static String NUMEROS = "0123456789";

    public static String MAYUSCULAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String MINUSCULAS = "abcdefghijklmnopqrstuvwxyz";

    public static String ESPECIALES = "ñÑ";

    public static String getPinNumber() {
        return getPassword(NUMEROS, 4);
    }

    public static String getPinNumber(int len) {
        return getPassword(NUMEROS, len);
    }

    public static String getPassword() {
        return getPassword(8);
    }

    public static String getPassword(int length) {
        return getPassword(NUMEROS + MAYUSCULAS + MINUSCULAS, length);
    }

    public static String getPassword(String key, int length) {
        String pswd = "";

        for (int i = 0; i < length; i++) {
            pswd += (key.charAt((int) (Math.random() * key.length())));
        }

        return pswd;
    }

    public static void main(String[] args) {

        System.out.println("password");
        System.out.println(getPassword());

        System.out.println("pin numuero");
        System.out.println(getPinNumber());

    }
}
