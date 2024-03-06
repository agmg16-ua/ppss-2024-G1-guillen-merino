package ppss.ejercicio2;

public class GestorLlamadasTestable extends GestorLlamadas{

    public GestorLlamadasStub stub;

    public void setCalendario(GestorLlamadasStub stub) {
        this.stub = stub;
    }

    @Override
    public GestorLlamadasStub getCalendario() {
        return stub;
    }

}
