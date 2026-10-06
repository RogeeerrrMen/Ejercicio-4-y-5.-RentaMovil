public class Carro extends Vehiculo {

    private int cantidadPasajeros;
    private boolean transmisionAutomatica;

    public Carro(String placa, String marca, String modelo, double tarifaDiaria, int cantidadPasajeros, boolean transmisionAutomatica) {

        super(placa, marca, modelo, tarifaDiaria);

        if (cantidadPasajeros <= 0) {
            throw new IllegalArgumentException("La cantidad de pasajeros debe ser mayor que 0.");
        }

        this.cantidadPasajeros = cantidadPasajeros;
        this.transmisionAutomatica = transmisionAutomatica;
    }

    @Override
    public double calcularSubtotal(int dias) {

        double subtotal = getTarifaDiaria() * dias;

        if (transmisionAutomatica) {
            subtotal += 50 * dias;
        }

        return subtotal;
    }

    @Override
    public boolean tieneLicenciaAdecuada(Cliente cliente) {
        return cliente.tieneLicencia(TipoLicencia.C);
    }

    @Override
    public int obtenerUmbralMantenimiento() {
        return 30;
    }

    @Override
    public String obtenerDescripcion() {

        String transmision;

        if (transmisionAutomatica) {
            transmision = "Automatica";
        } else {
            transmision = "Manual";
        }

        return "Pasajeros: " + cantidadPasajeros + ", transmision: " + transmision;
    }

    @Override
    public String obtenerCategoria() {
        return "Carro";
    }

    public int getCantidadPasajeros() {
        return cantidadPasajeros;
    }

    public boolean esTransmisionAutomatica() {
        return transmisionAutomatica;
    }
}