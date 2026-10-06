import java.util.ArrayList;

public class ClienteCorporativo extends Cliente {

    private String nombreContacto;

    public ClienteCorporativo(String nit, String nombreEmpresa, String nombreContacto, ArrayList<TipoLicencia> licencias) {

        super(nit, nombreEmpresa, licencias);

        if (nombreContacto == null || nombreContacto.isEmpty()) {
            throw new IllegalArgumentException("El nombre del contacto no puede estar vacio.");
        }

        this.nombreContacto = nombreContacto;
    
    }

    @Override
    public double calcularDescuento(double subtotal) {

        return subtotal * 0.10;
    }

    @Override
    public int obtenerLimiteAlquileres() {

        return 3;
    }

    @Override
    public void registrarAlquilerConfirmado(){

        // El cliente corporativo no nececita corroborar su historial, siempre tiene 10% de decuento
    }

    @Override
    public String obtenerDescripcion() {

        return "Contacto: " + nombreContacto;
    }

    public String getNombreContacto() {

        return nombreContacto;
    }
}