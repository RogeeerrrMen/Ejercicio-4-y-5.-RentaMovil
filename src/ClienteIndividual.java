import java.util.ArrayList;

public class ClienteIndividual extends Cliente {

    private int alquileresConfirmados;

    public ClienteIndividual(String dpi, String nombre, ArrayList<TipoLicencia> licencias){

        super(dpi, nombre, licencias);

        if (dpi == null || dpi.length() != 13) {

            throw new IllegalArgumentException("El DPI debe tener 13 digitos");
        }

        this.alquileresConfirmados = 0;
    }

    @Override
    public double calcularDescuento(double subtotal) {

        if (alquileresConfirmados >= 3) {

            return subtotal * 0.05;
        }

        return 0;
    }

    @Override
    public int obtenerLimiteAlquileres() {

        return 1;
    }

    @Override
    public void registrarAlquilerConfirmado() {

        alquileresConfirmados++;
    }

    @Override
    public String obtenerDescripcion() {

        return "Alquileres confirmados: " + alquileresConfirmados;
    }

    public int getAlquileresConfirmados() {

        return alquileresConfirmados;
    }
}