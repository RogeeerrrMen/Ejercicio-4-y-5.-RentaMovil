public class CamionCarga extends Vehiculo{

    private double capacidadMaxima;

    public CamionCarga(String placa, String marca, String modelo, double tarifaDiaria, double capacidadMaxima) {

        super(placa, marca, modelo, tarifaDiaria);

        if (capacidadMaxima <= 0) {

            throw new IllegalArgumentException("La capacidad maxima debe ser mayor a 0");
        }

        this.capacidadMaxima = capacidadMaxima;
    }

    @Override
    public double calcularSubtotal(int dias){

        double subtotal = getTarifaDiaria() * dias;
        subtotal += 100 * capacidadMaxima * dias;
        
        return subtotal;
    }

    @Override
    public boolean tieneLicenciaAdecuada(Cliente cliente) {

        return cliente.tieneLicencia(TipoLicencia.B);
    }

    @Override
    public int obtenerUmbralMantenimiento() {

        return 15;
    }

    @Override 
    public String obtenerDescripcion() {

        return "Capacidad maxima: " + capacidadMaxima + " toneladas";
    }

    @Override
    public String obtenerCategoria() {

        return "Camion de carga";
    }

    public double getCapacidadMaxima() {

        return capacidadMaxima;
    }
}