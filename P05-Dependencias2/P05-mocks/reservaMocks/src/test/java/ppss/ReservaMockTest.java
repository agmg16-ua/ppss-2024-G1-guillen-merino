package ppss;

import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.junit.jupiter.api.Assertions.*;

import ppss.excepciones.*;

public class ReservaMockTest {

    private static ArrayList<String> isbnsRegistrados;

    @BeforeAll
    public static void inicialization() {
        isbnsRegistrados = new ArrayList<String>();
        isbnsRegistrados.add("22222");
        isbnsRegistrados.add("33333");
    }

    @Test
    public void C1() {
        String login = "xxxx";
        String password = "xxxx";
        String socio = "Pepe";
        String[] isbns = {"33333"};
        String resultadoEsperado = "ERROR de permisos; ";

        IMocksControl ctrl = EasyMock.createStrictControl();
        Reserva reservaMock = EasyMock.partialMockBuilder(Reserva.class)
                .addMockedMethod("compruebaPermisos")
                .mock(ctrl);

        expect(reservaMock.compruebaPermisos(login, password, Usuario.BIBLIOTECARIO)).andReturn(false);
        ctrl.replay();

        ReservaException resultadoReal = assertThrows(ReservaException.class, ()->{
            reservaMock.realizaReserva(login, password, socio, isbns);
        });
        ctrl.verify();

        assertEquals(resultadoEsperado, resultadoReal.getMessage());


    }

    @Test
    public void C2() {
        String login = "ppss";
        String password = "ppss";
        String socio = "Pepe";
        String[] isbns = {"22222", "33333"};

        IMocksControl ctrl = EasyMock.createStrictControl();
        Reserva mockReserva = EasyMock.partialMockBuilder(Reserva.class)
                .addMockedMethod("compruebaPermisos")
                .addMockedMethod("setFactoria")
                .mock(ctrl);
        FactoriaBOs mockFactoria = ctrl.mock(FactoriaBOs.class);
        IOperacionBO mockIOperacionBO = ctrl.mock(IOperacionBO.class);

        expect(mockReserva.compruebaPermisos(login, password, Usuario.BIBLIOTECARIO)).andReturn(true);
        expect(mockReserva.setFactoria()).andReturn(mockFactoria);
        expect(mockFactoria.getOperacionBO()).andReturn(mockIOperacionBO);
        assertDoesNotThrow(()->{
            mockIOperacionBO.operacionReserva(socio, isbns[0]);
            expectLastCall();
            mockIOperacionBO.operacionReserva(socio, isbns[1]);
            expectLastCall();
        });
        ctrl.replay();

        assertDoesNotThrow(()->mockReserva.realizaReserva(login, password, socio, isbns));
        ctrl.verify();
    }

    @Test
    public void C3() {
        String login = "xxxx";
        String password = "xxxx";
        String socio = "Pepe";
        String[] isbns = {"11111", "22222", "55555"};
        String resultadoEsperado = "ISBN invalido:11111; ISBN invalido:55555; ";

        IMocksControl ctrl = EasyMock.createStrictControl();
        Reserva reservaMock = EasyMock.partialMockBuilder(Reserva.class)
                .addMockedMethod("setFactoria")
                .addMockedMethod("compruebaPermisos")
                .mock(ctrl);
        FactoriaBOs factoriaMock = ctrl.mock(FactoriaBOs.class);
        IOperacionBO operacionMock = ctrl.mock(IOperacionBO.class);

        expect(reservaMock.compruebaPermisos(login, password, Usuario.BIBLIOTECARIO)).andReturn(true);
        expect(reservaMock.setFactoria()).andReturn(factoriaMock);
        expect(factoriaMock.getOperacionBO()).andReturn(operacionMock);
        assertDoesNotThrow(()->{
            operacionMock.operacionReserva(socio, isbns[0]);
            expectLastCall().andThrow(new IsbnInvalidoException());
            operacionMock.operacionReserva(socio, isbns[1]);
            expectLastCall();
            operacionMock.operacionReserva(socio, isbns[2]);
            expectLastCall().andThrow(new IsbnInvalidoException());
        });
        ctrl.replay();

        ReservaException resultadoReal = assertThrows(ReservaException.class,()->{
            reservaMock.realizaReserva(login, password, socio, isbns);
        });
        ctrl.verify();

        assertEquals(resultadoEsperado, resultadoReal.getMessage());
    }

    @Test
    public void C4() {
        String login = "xxxx";
        String password = "xxxx";
        String socio = "Pepe";
        String[] isbns = {"22222"};
        String resultadoEsperado = "SOCIO invalido; ";

        IMocksControl ctrl = EasyMock.createStrictControl();
        Reserva reservaMock = EasyMock.partialMockBuilder(Reserva.class)
                .addMockedMethod("setFactoria")
                .addMockedMethod("compruebaPermisos")
                .mock(ctrl);
        FactoriaBOs factoriaMock = ctrl.mock(FactoriaBOs.class);
        IOperacionBO operacionMock = ctrl.mock(IOperacionBO.class);

        expect(reservaMock.compruebaPermisos(login, password, Usuario.BIBLIOTECARIO)).andReturn(true);
        expect(reservaMock.setFactoria()).andReturn(factoriaMock);
        expect(factoriaMock.getOperacionBO()).andReturn(operacionMock);
        assertDoesNotThrow(()->{
            operacionMock.operacionReserva(socio, isbns[0]);
            expectLastCall().andThrow(new SocioInvalidoException());
        });
        ctrl.replay();

        ReservaException resultadoReal = assertThrows(ReservaException.class,()->{
            reservaMock.realizaReserva(login, password, socio, isbns);
        });
        ctrl.verify();

        assertEquals(resultadoEsperado, resultadoReal.getMessage());
    }

    @Test
    public void C5() {
        String login = "xxxx";
        String password = "xxxx";
        String socio = "Pepe";
        String[] isbns = {"11111", "22222", "33333"};
        String resultadoEsperado = "ISBN invalido:11111; CONEXION invalida; ";

        IMocksControl ctrl = EasyMock.createStrictControl();
        Reserva reservaMock = EasyMock.partialMockBuilder(Reserva.class)
                .addMockedMethod("setFactoria")
                .addMockedMethod("compruebaPermisos")
                .mock(ctrl);
        FactoriaBOs factoriaMock = ctrl.mock(FactoriaBOs.class);
        IOperacionBO operacionMock = ctrl.mock(IOperacionBO.class);

        expect(reservaMock.compruebaPermisos(login, password, Usuario.BIBLIOTECARIO)).andReturn(true);
        expect(reservaMock.setFactoria()).andReturn(factoriaMock);
        expect(factoriaMock.getOperacionBO()).andReturn(operacionMock);
        assertDoesNotThrow(()->{
            operacionMock.operacionReserva(socio, isbns[0]);
            expectLastCall().andThrow(new IsbnInvalidoException());
            operacionMock.operacionReserva(socio, isbns[1]);
            expectLastCall();
            operacionMock.operacionReserva(socio, isbns[2]);
            expectLastCall().andThrow(new JDBCException());
        });
        ctrl.replay();

        ReservaException resultadoReal = assertThrows(ReservaException.class,()->{
            reservaMock.realizaReserva(login, password, socio, isbns);
        });
        ctrl.verify();

        assertEquals(resultadoEsperado, resultadoReal.getMessage());
    }
}
