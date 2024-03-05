package ppss.ejercicio1;

public class GestorLlamadasTestable extends GestorLlamadas{

    private int hora;
    @Override
    public int getHoraActual() {
        return hora;
    }

    public void setCalendar(int hora) {
        this.hora = hora;
    }
}
