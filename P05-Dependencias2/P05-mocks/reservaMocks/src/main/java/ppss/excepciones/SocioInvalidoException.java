package ppss.excepciones;

public class SocioInvalidoException extends Exception{

    public SocioInvalidoException() {

    }

    public SocioInvalidoException(String mensaje) {
        super(mensaje);
    }
}
