package ppss;

import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.junit.jupiter.api.Test;

public class PremioTest {

    @Test
    public void C1_should_return_Premiado_con_entrada_final_champions_when_random_is_0_07_and_premio_entrada_final_champions() {
        String premio = "entrada final champions";
        float random = 0.07f;
        String resultadoEsperado = "Premiado con entrada final champions";

        IMocksControl ctrl = EasyMock.createStrictControl();
        Premio partialMock = EasyMock.partialMockBuilder(Premio.class)
                .addMockedMethod("generaNumero")
                .mock(ctrl);
        ClienteWebService mock = ctrl.mock(ClienteWebService.class);
        ctrl.replay();
        partialMock.cliente = mock;



    }
}
