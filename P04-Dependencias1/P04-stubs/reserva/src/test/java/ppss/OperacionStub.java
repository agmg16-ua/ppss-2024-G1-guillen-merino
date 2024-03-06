package ppss;

import ppss.excepciones.IsbnInvalidoException;
import ppss.excepciones.JDBCException;
import ppss.excepciones.SocioInvalidoException;

import java.util.ArrayList;

public class OperacionStub extends Operacion{
    @Override
    public void operacionReserva(String socio, String isbn) throws IsbnInvalidoException, JDBCException, SocioInvalidoException {
        ArrayList<String> isbnsValidos = new ArrayList<>(2);
        isbnsValidos.add("11111");
        isbnsValidos.add("22222");
        if(socio != "Luis"){
            throw new SocioInvalidoException();
        } else if (!isbnsValidos.contains(isbn)) {
            throw new IsbnInvalidoException(isbn);
        }
    }
}
