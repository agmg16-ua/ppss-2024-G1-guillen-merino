package ppss;

import java.time.LocalDate;
public class AlquilaCoches {
    protected Calendario calendario = new Calendario();

    public Servicio getServicio() {
        return new Servicio();
    }

    public Ticket calculaPrecio(TipoCoche tipo, LocalDate inicio, int ndias) throws MensajeException {
        Ticket ticket = new Ticket();
        float precioDia,precioTotal = 0.0f;
        float porcentaje = 0.25f;

        String observaciones = "";
        IService servicio = getServicio();
        precioDia = servicio.consultaPrecio(tipo);  //Dependencia externa
        for (int i=0; i<ndias;i++) {
            LocalDate otroDia = inicio.plusDays((long)i);
            try {
                if (calendario.es_festivo(otroDia)) {   //Dependencia externa
                    precioTotal += (1+ porcentaje)*precioDia;
                } else {
                    precioTotal += (1- porcentaje)*precioDia;
                }
            } catch (CalendarioException ex) {
                observaciones += "Error en dia: "+otroDia+"; ";
            }
        }

        if (observaciones.length()>0) {
            throw new MensajeException(observaciones);
        }

        ticket.setPrecio_final(precioTotal);
        return ticket;
    }
}
