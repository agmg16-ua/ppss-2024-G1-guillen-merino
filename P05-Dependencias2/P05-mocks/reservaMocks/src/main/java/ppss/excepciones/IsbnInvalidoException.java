package ppss.excepciones;

public class IsbnInvalidoException extends Exception{

    public IsbnInvalidoException() {

    }

    public IsbnInvalidoException(String mensaje) {
        super(mensaje);
    }
}
