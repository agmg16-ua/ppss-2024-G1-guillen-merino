package ppss;

public class Factoria {
    private static Operacion operacion;

    public static Operacion create() {
        if (operacion != null) {
            return operacion;
        } else {
            return new Operacion();
        }
    }
    static void setServicio (Operacion op){
        operacion = op;
    }
}
