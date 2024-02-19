package ppss;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import ppss.Exceptions.DataException;

class DataArrayTest {

    @Test
    void C1_delete_should_return_1_3_7_when_coleccion_is_1_3_5_7_and_delete_5() {
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
    void C2_delete_should_return_1_3_5_7_when_coleccion_is_1_3_3_5_7_and_delete_5() {
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
    void C3_delete_should_return_1_2_3_5_6_7_8_9_10_when_coleccion_is_1_2_3_4_5_6_7_8_9_10_and_delete_4() {
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
    void C4_delete_should_return_Exception_when_coleccion_is_empty() {
        int[] coleccionInicio = {};

        DataArray dataArray = new DataArray(coleccionInicio);

        int eliminar = 8;

        DataException exception = assertThrows(DataException.class, () -> dataArray.delete(eliminar));

        assertEquals("No hay elementos en la colección", exception.getMessage());
    }

    @Test
    void C5_delete_should_return_Exception_when_delete_value_is_less_or_equal_than_0() {
        int[] coleccionInicio = {1,3,5,7};

        DataArray dataArray = new DataArray(coleccionInicio);

        int eliminar = -5;

        DataException exception = assertThrows(DataException.class, () -> dataArray.delete(eliminar));

        assertEquals("El valor a borrar debe ser > 0", exception.getMessage());
    }

    @Test
    void C6_delete_should_return_Exception_when_coleccion_is_empty_and_delete_value_is_0() {
        int[] coleccionInicio = {};

        DataArray dataArray = new DataArray(coleccionInicio);

        int eliminar = 0;

        DataException exception = assertThrows(DataException.class, () -> dataArray.delete(eliminar));

        assertEquals("Colección vacía. Y el valor a borrar debe ser > 0", exception.getMessage());
    }

    @Test
    void C7_delete_should_return_Exception_when_delete_value_is_not_in_coleccion() {
        int[] coleccionInicio = {1,3,5,7};

        DataArray dataArray = new DataArray(coleccionInicio);

        int eliminar = 8;

        DataException exception = assertThrows(DataException.class, () -> dataArray.delete(eliminar));

        assertEquals("Elemento no encontrado", exception.getMessage());
    }
}