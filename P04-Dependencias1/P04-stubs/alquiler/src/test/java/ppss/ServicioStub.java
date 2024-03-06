package ppss;

public class ServicioStub extends Servicio{
    @Override
    public float consultaPrecio(TipoCoche tipo) {
        switch (tipo){
            case TURISMO, CARAVANA -> {
                return 10F;
            }
            default -> {
                return 0;
            }
        }
    }
}
