package ppss;

import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GestorLlamadasTest {

    @Test
    public void C1_should_return_457_6_when_22_minutos_and_10_hora() {
        int minutos = 22;
        int hora = 10;
        double resultadoEsperado = 457.6;

        IMocksControl ctrl = EasyMock.createStrictControl();
        GestorLlamadas partialMock = EasyMock.partialMockBuilder(GestorLlamadas.class)
                .addMockedMethod("getCalendario")
                .mock(ctrl);
        Calendario mock = ctrl.mock(Calendario.class);

        EasyMock.expect(partialMock.getCalendario()).andReturn(mock);
        EasyMock.expect(mock.getHoraActual()).andReturn(hora);
        ctrl.replay();

        double resultadoReal = partialMock.calculaConsumo(minutos);
        ctrl.verify();

        assertEquals(resultadoEsperado, resultadoReal);
    }

    @Test
    public void C2_should_return_136_5_when_13_minutos_and_21_hora() {
        int minutos = 13;
        int hora = 21;
        double resultadoEsperado = 136.5;

        IMocksControl ctrl = EasyMock.createStrictControl();
        GestorLlamadas partialMock = EasyMock.partialMockBuilder(GestorLlamadas.class)
                .addMockedMethod("getCalendario")
                .mock(ctrl);
        Calendario mock = ctrl.mock(Calendario.class);

        EasyMock.expect(partialMock.getCalendario()).andReturn(mock);
        EasyMock.expect(mock.getHoraActual()).andReturn(hora);
        ctrl.replay();

        double resultadoReal = partialMock.calculaConsumo(minutos);
        ctrl.verify();

        assertEquals(resultadoEsperado, resultadoReal);

    }
}
