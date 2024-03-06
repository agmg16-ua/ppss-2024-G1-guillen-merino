package ppss;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class AlquilaCochesTest {
    static AlquilaCochesTestable alquilar;
    static ServicioStub servicioStub;
    static CalendarioStub calendarioStub;

    @BeforeAll
    public static void createSEAMS(){
        alquilar = new AlquilaCochesTestable();
        servicioStub = new ServicioStub();
        calendarioStub = new CalendarioStub();

        alquilar.setCalendario(calendarioStub);
        alquilar.setServicio(servicioStub);
    }

    @Test
    public void C1_AlquilaCoches_should_return_ticket_75_when_tipo_is_Turismo_date_is_correct_dias_is_10_and_es_festivo_is_false_for_all(){
        //Arrange
        float resultadoEsperado = 75F;
        LocalDate fechaInicio = LocalDate.of(2024,5,18);
        int dias = 10;
        TipoCoche tipo = TipoCoche.TURISMO;
        //Act
        Ticket ticket = assertDoesNotThrow(() -> alquilar.calculaPrecio(tipo,fechaInicio,dias));
        //Assert
        assertEquals(resultadoEsperado,ticket.getPrecio_final());
    }

    @Test
    public void C2_AlquilaCoches_should_return_ticket_62_5_when_tipo_is_Caravana_date_is_correct_dias_is_7_and_es_festivo_is_true_for_two_days(){
        //Arrange
        float resultadoEsperado = 62.5F;
        LocalDate fechaInicio = LocalDate.of(2024,6,19);
        int dias = 7;
        TipoCoche tipo = TipoCoche.CARAVANA;
        //Act
        Ticket ticket = assertDoesNotThrow(() -> alquilar.calculaPrecio(tipo,fechaInicio,dias));
        //Assert
        assertEquals(resultadoEsperado,ticket.getPrecio_final());
    }

    @Test
    public void C3_AlquilaCoches_should_return_CalendarioException_when_there_are_incorrect_days_in_the_interval(){
        //Arrange
        String resultadoEsperado = "Error en dia: 2024-04-18; " + "Error en dia: 2024-04-21; " + "Error en dia: 2024-04-22; ";
        LocalDate fechaInicio = LocalDate.of(2024,4,17);
        int dias = 8;
        TipoCoche tipo = TipoCoche.TURISMO;
        //Act
        MensajeException exception = assertThrows(MensajeException.class,() -> alquilar.calculaPrecio(tipo,fechaInicio,dias));
        //Assert
        assertEquals(resultadoEsperado,exception.getMessage());
    }

}
