package ppss;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ppss.Exceptions.ButacasException;
import org.junit.jupiter.params.ParameterizedTest;


import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;


public class CineTest {
    Cine cine;

    @BeforeEach
    public void setup() {
        cine = new Cine();
    }

    @Test
    public void C1_reservaButacas_should_return_Exception_when_fila_empty_and_want_3() {
        boolean[] asientosReales = new boolean[0];
        int solicitados = 3;

        assertThrows(ButacasException.class, ()-> cine.reservaButacas(asientosReales, solicitados));
    }

    @Test
    public void C2_reservaButacas_should_return_false_when_fila_empty_and_want_zero() {
        boolean[] asientosReales = new boolean[0];
        int solicitados = 0;

        boolean resultadoEsperado = false;
        boolean[] asientosEsperados = new boolean[0];

        assertDoesNotThrow(() -> {
            assertAll("reservaButacas", () -> {
                boolean resultadoReal = cine.reservaButacas(asientosReales, solicitados);
                assertEquals(resultadoEsperado, resultadoReal);
            }, () -> assertArrayEquals(asientosEsperados, asientosReales));
        });
    }

    @Test
    public void C3_reservaButacas_should_return_true_when_fila_has_3_seats_free_and_want_2() {
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

        assertDoesNotThrow(() -> {
            assertAll("reservaButacas", () -> {
                boolean resultadoReal = cine.reservaButacas(asientosReales, solicitados);
                assertEquals(resultadoEsperado, resultadoReal);
            }, () -> assertArrayEquals(asientosEsperados, asientosReales));
        });
    }

    @Test
    public void C4_reservaButacas_should_return_false_when_no_free_seats_and_want_1() {
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

        assertDoesNotThrow(() -> {
            assertAll("reservaButacas", () -> {
                boolean resultadoReal = cine.reservaButacas(asientosReales, solicitados);
                assertEquals(resultadoEsperado, resultadoReal);
            }, () -> assertArrayEquals(asientosEsperados, asientosReales));
        });
    }

    @ParameterizedTest(name="[{index}] should be {2} when we want {1} and {4}")
    @Tag("parametrizado")
    @DisplayName("reservaButacas_")
    @MethodSource("casosDePrueba")
    public void reservaButacasC5(boolean[] asientosReales, int solicitados, boolean resultadoEsperado, boolean[] asientosEsperados, String texto) throws ButacasException {
        boolean resultadoReal = cine.reservaButacas(asientosReales, solicitados);
        assertAll(
                () -> assertEquals(resultadoEsperado, resultadoReal),
                () -> assertArrayEquals(asientosEsperados, asientosReales)
        );
    }

    public static Stream<Arguments> casosDePrueba() {
        return Stream.of(
                Arguments.of(new boolean[]{}, 0, false, new boolean[]{}, "file has no seats"),
                Arguments.of(new boolean[]{false, false, false, true, true}, 2, true, new boolean[]{true, true, false, true, true}, "there are two free seats"),
                Arguments.of(new boolean[]{true, true, true}, 1, false, new boolean[]{true, true, true}, "all seats are already reserved")
        );
    }
}