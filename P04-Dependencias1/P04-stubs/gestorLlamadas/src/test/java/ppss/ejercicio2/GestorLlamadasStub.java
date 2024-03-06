package ppss.ejercicio2;

public class GestorLlamadasStub extends Calendario{

    public int hora;

    public void setHora(int hora) {
        this.hora = hora;
    }

    @Override
    public int getHoraActual() {
        return hora;
    }

}
