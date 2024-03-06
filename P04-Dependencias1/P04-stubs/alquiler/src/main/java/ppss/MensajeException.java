package ppss;

public class MensajeException extends Exception{
    public MensajeException(String mensaje){
        super(mensaje);
    }
    @Override
    public String getMessage() {
        return super.getMessage();
    }


}
