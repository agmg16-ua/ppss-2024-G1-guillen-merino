package ppss;

import ppss.excepciones.IsbnInvalidoException;
import ppss.excepciones.JDBCException;
import ppss.excepciones.SocioInvalidoException;

import java.util.jar.JarException;

public interface IOperacionBO {

    public void operacionReserva (String socio, String isbn) throws IsbnInvalidoException, JDBCException, SocioInvalidoException;
}
