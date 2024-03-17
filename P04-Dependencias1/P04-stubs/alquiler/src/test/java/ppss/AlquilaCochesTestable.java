package ppss;

public class AlquilaCochesTestable extends AlquilaCoches{

    private Servicio servicio;

    public void setCalendario(Calendario calendario){

        this.calendario = calendario;
    }

    @Override
    public Servicio getServicio() {
        return this.servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

}
