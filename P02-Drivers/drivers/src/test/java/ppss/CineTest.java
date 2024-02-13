package ppss;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import ppss.Exceptions.ButacasException;

import static org.junit.jupiter.api.Assertions.*;


public class CineTest {
    Cine cine;

    @BeforeEach
    public void setup() {
        cine = new Cine();
    }

    @Test
    public void C1_reservaButacas_should_return_Exception_when_fila_empty_and_want_3() throws ButacasException {
        boolean[] asientosReales = new boolean[0];
        int solicitados = 3;
        ButacasException exception = assertThrows(ButacasException.class, ()-> cine.reservaButacas(asientosReales, solicitados));

        assertEquals("No se puede procesar la solicitud", exception.getMessage());
    }

    @Test
    public void C2_reservaButacas_should_return_false_when_fila_empty_and_want_zero() throws ButacasException {
        boolean[] asientosReales = new boolean[0];
        int solicitados = 0;

        boolean resultadoEsperado = false;
        boolean[] asientosEsperados = new boolean[0];

        boolean resultadoReal = cine.reservaButacas(asientosReales, solicitados);

        assertEquals(resultadoEsperado, resultadoReal);
        assertArrayEquals(asientosEsperados, asientosReales);
    }

    @Test
    public void C3_reservaButacas_should_return_true_when_fila_has_3_seats_free_and_want_2() throws ButacasException {
        boolean[] asientosReales = new boolean[5];
        asientosReales[0] = false;
        asientosReales[1] = false;
        asientosReales[2] = false;
        asientosReales[3] = true;
        asientosReales[4] = true;
        int solicitados = 2;

        boolean resultadoEsperado = true;
        boolean[] asientosEsperados = new boolean[5];
        asientosEsperados[0] = true;
        asientosEsperados[1] = true;
        asientosEsperados[2] = false;
        asientosEsperados[3] = true;
        asientosEsperados[4] = true;

        boolean resultadoReal = cine.reservaButacas(asientosReales, solicitados);

        assertEquals(resultadoEsperado, resultadoReal);
        assertArrayEquals(asientosEsperados, asientosReales);
    }

    @Test
    public void C4_reservaButacas_should_return_false_when_no_free_seats_and_want_1() throws ButacasException {
        boolean[] asientosReales = new boolean[3];
        asientosReales[0] = true;
        asientosReales[1] = true;
        asientosReales[2] = true;
        int solicitados = 2;

        boolean resultadoEsperado = false;
        boolean[] asientosEsperados = new boolean[3];
        asientosEsperados[0] = true;
        asientosEsperados[1] = true;
        asientosEsperados[2] = true;

        boolean resultadoReal = cine.reservaButacas(asientosReales, solicitados);

        assertEquals(resultadoEsperado, resultadoReal);
        assertArrayEquals(asientosEsperados, asientosReales);
    }
}