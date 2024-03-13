package ppss;


import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.junit.jupiter.api.Test;
import ppss.Exceptions.FicheroException;

import java.io.FileReader;
import java.io.IOException;

import static org.easymock.EasyMock.expect;
import static org.junit.jupiter.api.Assertions.*;

public class FicheroTextoTest {

    @Test
    public void C1_should_return_FicheroException_when_fichero1txt_and_a_b_IOException() {
        String nombreFichero = "src/test/resources/ficheroC1.txt";
        String resultadoEsperado = nombreFichero + " (Error al leer el archivo)";
        IMocksControl ctrl = EasyMock.createStrictControl();
        FileReader mockFileReader = ctrl.mock(FileReader.class);
        FicheroTexto mockFicheroTexto = EasyMock.partialMockBuilder(FicheroTexto.class)
                .addMockedMethod("getFileReader")
                .mock(ctrl);


        assertDoesNotThrow(()->{
            expect(mockFicheroTexto.getFileReader(nombreFichero)).andReturn(mockFileReader);
            expect(mockFileReader.read())
                    .andReturn((int) 'a').once()
                    .andReturn((int) 'b').once()
                    .andThrow(new IOException()).once();
        });
        ctrl.replay();

        FicheroException resultadoReal = assertThrows(FicheroException.class, ()->{
            mockFicheroTexto.contarCaracteres(nombreFichero);
        });
        ctrl.verify();

        assertEquals(resultadoEsperado, resultadoReal.getMessage());
    }

    @Test
    public void C2_should_return_FicheroException_when_fichero2txt_and_a_b_c_menos_1() {
        String nombreFichero = "src/test/resources/ficheroC2.txt";
        String resultadoEsperado = nombreFichero + " (Error al cerrar el archivo)";
        IMocksControl ctrl = EasyMock.createStrictControl();
        FileReader mockFileReader = ctrl.mock(FileReader.class);
        FicheroTexto mockFicheroTexto = EasyMock.partialMockBuilder(FicheroTexto.class)
                .addMockedMethod("getFileReader")
                .mock(ctrl);


        assertDoesNotThrow(()->{
            expect(mockFicheroTexto.getFileReader(nombreFichero)).andReturn(mockFileReader);
            expect(mockFileReader.read())
                    .andReturn((int) 'a').once()
                    .andReturn((int) 'b').once()
                    .andReturn((int) 'c').once()
                    .andReturn(-1).once();
            mockFileReader.close();
            EasyMock.expectLastCall()
                    .andThrow(new IOException());
        });
        ctrl.replay();

        FicheroException resultadoReal = assertThrows(FicheroException.class, ()->{
            mockFicheroTexto.contarCaracteres(nombreFichero);
        });
        ctrl.verify();

        assertEquals(resultadoEsperado, resultadoReal.getMessage());
    }
}
