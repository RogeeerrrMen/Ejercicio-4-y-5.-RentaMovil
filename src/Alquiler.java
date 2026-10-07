public class Alquiler {

    private int numero;
    private Cliente cliente;
    private Vehiculo vehiculo;
    private int dias;
    private double subtotal;
    private double descuento;
    private double total;
    private boolean activo;

    public Alquiler(int numero, Cliente cliente, Vehiculo vehiculo, int dias, double subtotal, double descuento, double total) {

        if (dias <= 0) {

        throw new IllegalArgumentException("Los dias de alquiler deben ser mayores que 0.");

        }

        this.numero = numero;
        this.cliente = cliente;
        this.vehiculo = vehiculo;
        this.dias = dias;
        this.subtotal = subtotal;
        this.descuento = descuento;
        this.total = total;
        this.activo = true;
        
    }

    public void finalizar() {

        activo = false;
    }
    public int getNumero() {
        return numero;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public int getDias() {
        return dias;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getDescuento() {
        return descuento;
    }

    public double getTotal() {
        return total;
    }

    public boolean esActivo() {
        return activo;
    }    
}