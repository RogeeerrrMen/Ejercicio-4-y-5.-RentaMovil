public class Moto extends Vehiculo {

    private int cilindraje;

    public Moto(String placa, String marca, String modelo, double tarifaDiaria, int cilindraje){

        super(placa, marca, modelo, tarifaDiaria);

        if (cilindraje <= 0){

            throw new IllegalArgumentException("El cilindraje debe ser mayor que 0");
        }

        this.cilindraje = cilindraje;
    }

    @Override
    public double calcularSubtotal(int dias) {

        double subtotal = getTarifaDiaria() * dias;

        if (cilindraje > 250) {
            subtotal += 75;
        }

        return subtotal;
    }
    @Override
    public boolean tieneLicenciaAdecuada(Cliente cliente) {
        return cliente.tieneLicencia(TipoLicencia.M);
    }

    @Override
    public int obtenerUmbralMantenimiento() {
        return 20;
    }

    @Override 
    public String obtenerDescripcion(){

        return "Cilindraje: " + cilindraje + " cc";
    }

    @Override
    public String obtenerCategoria(){
        return "Moto";
    }

    public int getCilindraje(){

        return cilindraje;
    }
}