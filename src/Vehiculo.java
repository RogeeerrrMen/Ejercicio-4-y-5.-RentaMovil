public abstract class Vehiculo {

    private String placa;
    private String marca;
    private String modelo;
    private double tarifaDiaria;
    private EstadoVehiculo estado;
    private int diasAcumulados;

    protected Vehiculo(String placa, String marca, String modelo, double tarifaDiaria) {

        if (placa == null || placa.trim().isEmpty()) {

        throw new IllegalArgumentException("La placa no puede estar vacia.");

        }

        if (tarifaDiaria <= 0) {

        throw new IllegalArgumentException("La tarifa diaria debe ser mayor que 0.");

        }

        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.tarifaDiaria = tarifaDiaria;

        this.estado = EstadoVehiculo.DISPONIBLE;
        this.diasAcumulados = 0;
    }

    public abstract double calcularSubtotal(int dias);

    public abstract boolean tieneLicenciaAdecuada(Cliente cliente);

    public abstract int obtenerUmbralMantenimiento();

    public abstract String obtenerDescripcion();

    public abstract String obtenerCategoria();

    public boolean estaDisponible() {
    return estado == EstadoVehiculo.DISPONIBLE;
    }

    public void marcarAlquilado() {
        estado = EstadoVehiculo.ALQUILADO;
    }

    public void registrarDevolucion(int dias) {

        diasAcumulados += dias;

        if (diasAcumulados >= obtenerUmbralMantenimiento()) {
            estado = EstadoVehiculo.MANTENIMIENTO;
        } else {
            estado = EstadoVehiculo.DISPONIBLE;
        }
    }

    public void finalizarMantenimiento() {

        diasAcumulados = 0;
        estado = EstadoVehiculo.DISPONIBLE;
    }

        public String getPlaca() {
        return placa;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public double getTarifaDiaria() {
            return tarifaDiaria;
    }

    public EstadoVehiculo getEstado() {
            return estado;
    }

    public int getDiasAcumulados() {
            return diasAcumulados;
    }


}