package ppss;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import ppss.Exceptions.FicheroException;

class FicheroTextoTest {

    FicheroTexto ficheroTexto;

    @BeforeEach
    public void setup() { ficheroTexto = new FicheroTexto(); };
    @Test
    public void C1_contarCaracteres_should_return_Exception_when_file_does_not_exist() {
        String fichero = "ficheroC1.txt";

        assertThrows(FicheroException.class, () -> ficheroTexto.contarCaracteres(fichero));
    }

    @Test
    public void C2_contarCaracteres_should_return_3_when_file_has_3_chars() {
        String fichero = "ficheroCorrecto.txt";
        int resultadoEsperado = 3;

        assertDoesNotThrow(() -> {
            int resultadoReal = ficheroTexto.contarCaracteres(fichero);
            assertEquals(resultadoEsperado, resultadoReal);
        });

    }

    @Tag("excluido")
    @Test
    public void C3_contarCaracteres_should_return_Exception_when_file_cannot_be_read() {
        Assertions.fail();
    }

    @Tag("excluido")
    @Test
    public void C4_contarCaracteres_should_return_Exception_when_file_cannot_be_closed() {
        Assertions.fail();
    }
}