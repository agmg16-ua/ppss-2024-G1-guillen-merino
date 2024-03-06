package ppss;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ppss.excepciones.ReservaException;

import static org.junit.jupiter.api.Assertions.*;

public class ReservaTest {
    public static ReservaTestable reservaTestable;

    @BeforeAll
    public static void inicializarSeams(){
        OperacionStub stub = new OperacionStub();
        Factoria.setServicio(stub);
        reservaTestable = new ReservaTestable();
    }

    @Test
    public void C1_Reserva_shloud_throw_ReservaException1_when_login_password_and_tipo_usuario_does_not_have_permissions(){
        //Arrange
        String login = "xxxx";
        String password = "xxxx";
        String socio = "Luis";
        String [] isbns = {"11111"};

        //Act
        ReservaException exception = assertThrows(ReservaException.class,()->reservaTestable.realizaReserva(login,password,socio,isbns));
        //Assert
        assertEquals( "ERROR de permisos; ",exception.getMessage());
    }

    @Test
    public void C2_Reserva_should_not_throw_any_exception_when_login_password_isbns_and_tipo_usuario_are_correct_values(){
        //Arrange
        String login = "ppss";
        String password = "ppss";
        String socio = "Luis";
        String [] isbns = {"11111","22222"};

        //Act,Assert
        assertDoesNotThrow(()->reservaTestable.realizaReserva(login,password,socio,isbns));
    }

    @Test
    public void C3_Reserva_should_throw_ReservaException2_when_there_are_some_incorrect_isbns(){
        //Arrange
        String login = "ppss";
        String password = "ppss";
        String socio = "Luis";
        String [] isbns = {"11111","33333","44444"};

        //Act
        ReservaException exception = assertThrows(ReservaException.class,()->reservaTestable.realizaReserva(login,password,socio,isbns));
        //Assert
        assertEquals( "ISBN invalido:33333; ISBN invalido:44444; ",exception.getMessage());
    }

    @Test
    public void C4_Reserva_shloud_throw_ReservaException3_when_socio_is_invalid(){
        //Arrange
        String login = "ppss";
        String password = "ppss";
        String socio = "Pepe";
        String [] isbns = {"11111"};

        //Act
        ReservaException exception = assertThrows(ReservaException.class,()->reservaTestable.realizaReserva(login,password,socio,isbns));
        //Assert
        assertEquals( "SOCIO invalido; ",exception.getMessage());
    }

    /*
    @Test
    public void C5_Reserva_shloud_throw_ReservaException4_when_the_conecction_is_invalid(){
        //Arrange
        String login = "ppss";
        String password = "ppss";
        String socio = "Luis";
        String [] isbns = {"11111","22222"};
        OperacionStub stub = new OperacionStub();
        Factoria.setServicio(stub);
        ReservaTestable reservaTestable = new ReservaTestable();
        //Act
        ReservaException exception = assertThrows(ReservaException.class,()->reservaTestable.realizaReserva(login,password,socio,isbns));
        //Assert
        assertEquals( "ERROR de permisos; ",exception.getMessage());
    }
    */
}
