public class Microbus extends Vehiculo{

    private int cantidadPasajeros;
    private boolean incluyePiloto;

    public Microbus(String placa, String marca, String modelo, double tarifaDiaria, int cantidadPasajeros, boolean incluyePiloto) {

        super(placa, marca, modelo, tarifaDiaria);

        if (cantidadPasajeros <= 0){
            throw new IllegalArgumentException("La cantidad de pasajeros debe ser mayor a 0");
        }

        this.cantidadPasajeros = cantidadPasajeros;
        this.incluyePiloto = incluyePiloto;
    }

    @Override 
    public double calcularSubtotal(int dias) {

        double subtotal = getTarifaDiaria() * dias;

        if (incluyePiloto) {

            subtotal += 250 * dias;
        }

        return subtotal;
    }

    @Override
    public boolean tieneLicenciaAdecuada(Cliente cliente) {

        if(incluyePiloto) {

            return true;
        }

        return cliente.tieneLicencia(TipoLicencia.B);
    }

    @Override 
    public int obtenerUmbralMantenimiento() {

        return 25;
    }

    @Override
    public String obtenerDescripcion() {

        String piloto;

        if (incluyePiloto) {

            piloto = "Si";
        } else {
            
            piloto = "No";
        }

        return "Pasajeros: " + cantidadPasajeros + ", incluye piloto: " + piloto;
    }

    @Override
    public String obtenerCategoria() {

        return "Microbus";
    }

    public int getCantidadPasajeros() {

        return cantidadPasajeros;
    }

    public boolean esIncluyePiloto() {

        return incluyePiloto;
    }
}