package ppss.ejercicio1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GestorLlamadasTest {

    public static GestorLlamadasTestable gestorLlamadas;

    @BeforeAll
    public static void setSeams() {
        gestorLlamadas = new GestorLlamadasTestable();
    }

    @Test
    public void C1_calculaConsumo_should_return_208_when_15_hours_and_10_minutes() {
        double resultadoEsperado = 208;
        int horas = 15;
        int minutos = 10;

        gestorLlamadas.setCalendar(horas);
        double resultadoReal = gestorLlamadas.calculaConsumo(minutos);

        assertEquals(resultadoEsperado, resultadoReal);
    }

    @Test
    public void C2_calculaConsumo_should_return_105_when_22_hours_and_10_minutes() {
        double resultadoEsperado = 105;
        int horas = 22;
        int minutos = 10;

        gestorLlamadas.setCalendar(horas);
        double resultadoReal = gestorLlamadas.calculaConsumo(minutos);

        assertEquals(resultadoEsperado, resultadoReal);
    }

}
