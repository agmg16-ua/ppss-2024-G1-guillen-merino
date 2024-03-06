package ppss;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CalendarioStub extends Calendario{
    @Override
    public boolean es_festivo(LocalDate otroDia) {
        ArrayList<LocalDate> diasFestivos = new ArrayList<>(10);
        ArrayList<LocalDate> diasException = new ArrayList<>(10);
        diasFestivos.add(LocalDate.of(2024,6,20));
        diasFestivos.add(LocalDate.of(2024,6,24));
        diasException.add(LocalDate.of(2024,4,18));
        diasException.add(LocalDate.of(2024,4,21));
        diasException.add(LocalDate.of(2024,4,22));

        if (diasException.contains(otroDia)){
            throw new CalendarioException();
        }

        return diasFestivos.contains(otroDia);
    }
}
