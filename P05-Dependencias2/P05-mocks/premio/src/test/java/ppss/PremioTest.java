package ppss;

import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PremioTest {

    @Test
    public void C1_should_return_Premiado_con_entrada_final_champions_when_random_is_0_07f_and_premio_entrada_final_champions() {
        String premio = "entrada final champions";
        float random = 0.07f;
        String resultadoEsperado = "Premiado con entrada final champions";

        IMocksControl ctrl = EasyMock.createStrictControl();
        Premio partialMock = EasyMock.partialMockBuilder(Premio.class)
                .addMockedMethod("generaNumero")
                .mock(ctrl);
        ClienteWebService mock = ctrl.mock(ClienteWebService.class);


        assertDoesNotThrow(() -> {
            EasyMock.expect(partialMock.generaNumero()).andReturn(random);
            EasyMock.expect(mock.obtenerPremio()).andReturn(premio);
        });
        ctrl.replay();

        partialMock.cliente = mock;
        String resultadoReal = partialMock.compruebaPremio();
        ctrl.verify();

        assertEquals(resultadoEsperado, resultadoReal);
    }

    @Test
    public void C2_should_return_No_se_ha_podido_obtener_el_premio_when_random_0_03f_and_premio_ClienteWebServiceException() {
        float random = 0.03f;
        String resultadoEsperado = "No se ha podido obtener el premio";

        IMocksControl ctrl = EasyMock.createStrictControl();
        Premio partialMock = EasyMock.partialMockBuilder(Premio.class)
                .addMockedMethod("generaNumero")
                .mock(ctrl);
        ClienteWebService mock = ctrl.mock(ClienteWebService.class);

        EasyMock.expect(partialMock.generaNumero()).andReturn(random);
        assertDoesNotThrow(()->{
            mock.obtenerPremio();
            EasyMock.expectLastCall().andThrow(new ClienteWebServiceException());
        });
        ctrl.replay();

        partialMock.cliente = mock;
        String resultadoReal = partialMock.compruebaPremio();
        ctrl.verify();

        assertEquals(resultadoEsperado, resultadoReal);
    }

    @Test
    public void C3_should_return_Sin_premio_when_random_0_3f() {
        float random = 0.3f;
        String resultadoEsperado = "Sin premio";

        IMocksControl ctrl = EasyMock.createStrictControl();
        Premio partialMock = EasyMock.partialMockBuilder(Premio.class)
                .addMockedMethod("generaNumero")
                .mock(ctrl);
        ClienteWebService mock = ctrl.mock(ClienteWebService.class);

        assertDoesNotThrow(()-> {
            EasyMock.expect(partialMock.generaNumero()).andReturn(random);
        });
        ctrl.replay();

        partialMock.cliente = mock;
        String resultadoReal = partialMock.compruebaPremio();
        ctrl.verify();

        assertEquals(resultadoEsperado, resultadoReal);
    }
}
