package ppss;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ppss.Exceptions.DataException;
import org.junit.jupiter.params.ParameterizedTest;

import java.util.stream.Stream;

class DataArrayTest {

    @Test
    public void C1_delete_should_return_1_3_7_when_coleccion_is_1_3_5_7_and_delete_5() {
        int[] coleccionInicio = new int[4];
        coleccionInicio[0] = 1;
        coleccionInicio[1] = 3;
        coleccionInicio[2] = 5;
        coleccionInicio[3] = 7;
        DataArray dataArray = new DataArray(coleccionInicio);

        int eliminar = 5;

        int[] coleccionEsperada = new int[3];
        coleccionEsperada[0] = 1;
        coleccionEsperada[1] = 3;
        coleccionEsperada[2] = 7;
        int longitudEsperada = 3;

        assertDoesNotThrow(() -> {
            dataArray.delete(eliminar);

            int[] coleccionReal = dataArray.getColeccion();
            int longitudReal = dataArray.size();

            assertAll("delete", () -> {
                assertArrayEquals(coleccionEsperada, coleccionReal);
            }, () -> {
                assertEquals(longitudEsperada, longitudReal);
            });
        });

    }

    @Test
    public void C2_delete_should_return_1_3_5_7_when_coleccion_is_1_3_3_5_7_and_delete_5() {
        int[] coleccionInicio = new int[5];
        coleccionInicio[0] = 1;
        coleccionInicio[1] = 3;
        coleccionInicio[2] = 3;
        coleccionInicio[3] = 5;
        coleccionInicio[4] = 7;
        DataArray dataArray = new DataArray(coleccionInicio);

        int eliminar = 3;

        int[] coleccionEsperada = new int[4];
        coleccionEsperada[0] = 1;
        coleccionEsperada[1] = 3;
        coleccionEsperada[2] = 5;
        coleccionEsperada[3] = 7;
        int longitudEsperada = 4;

        assertDoesNotThrow(() -> {
            dataArray.delete(eliminar);

            int[] coleccionReal = dataArray.getColeccion();
            int longitudReal = dataArray.size();

            assertAll("delete", () -> {
                assertArrayEquals(coleccionEsperada, coleccionReal);
            }, () -> {
                assertEquals(longitudEsperada, longitudReal);
            });
        });
    }

    @Test
    public void C3_delete_should_return_1_2_3_5_6_7_8_9_10_when_coleccion_is_1_2_3_4_5_6_7_8_9_10_and_delete_4() {
        int[] coleccionInicio = {1,2,3,4,5,6,7,8,9,10};

        DataArray dataArray = new DataArray(coleccionInicio);

        int eliminar = 4;

        int[] coleccionEsperada = {1,2,3,5,6,7,8,9,10};
        int longitudEsperada = 9;

        assertDoesNotThrow(() -> {
            dataArray.delete(eliminar);

            int[] coleccionReal = dataArray.getColeccion();
            int longitudReal = dataArray.size();

            assertAll("delete", () -> {
                assertArrayEquals(coleccionEsperada, coleccionReal);
            }, () -> {
                assertEquals(longitudEsperada, longitudReal);
            });
        });
    }

    @Test
    public void C4_delete_should_return_Exception_when_coleccion_is_empty() {
        int[] coleccionInicio = {};

        DataArray dataArray = new DataArray(coleccionInicio);

        int eliminar = 8;

        DataException exception = assertThrows(DataException.class, () -> dataArray.delete(eliminar));

        assertEquals("No hay elementos en la colección", exception.getMessage());
    }

    @Test
    public void C5_delete_should_return_Exception_when_delete_value_is_less_or_equal_than_0() {
        int[] coleccionInicio = {1,3,5,7};

        DataArray dataArray = new DataArray(coleccionInicio);

        int eliminar = -5;

        DataException exception = assertThrows(DataException.class, () -> dataArray.delete(eliminar));

        assertEquals("El valor a borrar debe ser > 0", exception.getMessage());
    }

    @Test
    public void C6_delete_should_return_Exception_when_coleccion_is_empty_and_delete_value_is_0() {
        int[] coleccionInicio = {};

        DataArray dataArray = new DataArray(coleccionInicio);

        int eliminar = 0;

        DataException exception = assertThrows(DataException.class, () -> dataArray.delete(eliminar));

        assertEquals("Colección vacía. Y el valor a borrar debe ser > 0", exception.getMessage());
    }

    @Test
    public void C7_delete_should_return_Exception_when_delete_value_is_not_in_coleccion() {
        int[] coleccionInicio = {1,3,5,7};

        DataArray dataArray = new DataArray(coleccionInicio);

        int eliminar = 8;

        DataException exception = assertThrows(DataException.class, () -> dataArray.delete(eliminar));

        assertEquals("Elemento no encontrado", exception.getMessage());
    }

    @ParameterizedTest(name="[{index}] Mesage exception should be \"{2}\" when we want delete {1}")
    @Tag("parametrizado")
    @Tag("conExcepciones")
    @DisplayName("delete_with_exceptions_")
    @MethodSource("casosDePruebaExcepciones")
    public void C8_deleteWithExceptions(int[] coleccion, int eliminar, String mensaje) {
        DataArray dataArray = new DataArray(coleccion);
        DataException exception = assertThrows(DataException.class, () -> dataArray.delete(eliminar));
        assertEquals(mensaje, exception.getMessage());
    }

    public static Stream<Arguments> casosDePruebaExcepciones() {
        return Stream.of(
                Arguments.of(new int[]{}, 8, "No hay elementos en la colección"),
                Arguments.of(new int[]{1,3,5,7}, -5, "El valor a borrar debe ser > 0"),
                Arguments.of(new int[]{}, 0, "Colección vacía. Y el valor a borrar debe ser > 0"),
                Arguments.of(new int[]{1,3,5,7}, 8, "Elemento no encontrado")
        );
    }

    @ParameterizedTest(name="[{index}] should be {2} when we want delete {1}")
    @Tag("parametrizado")
    @DisplayName("delete_without_exceptions")
    @MethodSource("casosDePruebaSinExcepciones")
    public void C9_deleteWithoutExceptions(int[] coleccionReal, int eliminar, int[] coleccionEsperada, int longitudEsperada) {
        DataArray dataArray = new DataArray(coleccionReal);

        assertDoesNotThrow(
                () -> dataArray.delete(eliminar)
        );

        assertAll(
                () -> assertArrayEquals(coleccionEsperada, dataArray.getColeccion()),
                () -> assertEquals(longitudEsperada, dataArray.size())
        );

    }

    public static Stream<Arguments> casosDePruebaSinExcepciones() {
        return Stream.of(
                Arguments.of(new int[]{1,3,5,7}, 5, new int[]{1,3,7}, 3),
                Arguments.of(new int[]{1,3,3,5,7}, 3, new int[]{1,3,5,7}, 4),
                Arguments.of(new int[]{1,2,3,4,5,6,7,8,9,10}, 4, new int[]{1,2,3,5,6,7,8,9,10}, 9)
        );
    }
}