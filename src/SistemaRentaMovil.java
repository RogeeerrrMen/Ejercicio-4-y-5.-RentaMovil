import java.util.ArrayList;

public class SistemaRentaMovil {

    private ArrayList<Vehiculo> vehiculos;
    private ArrayList<Cliente> clientes;
    private ArrayList<Alquiler> alquileres;
    private double ingresosTotales;
    private int siguienteNumeroAlquiler;

    public SistemaRentaMovil() {

        vehiculos = new ArrayList<Vehiculo>();
        clientes = new ArrayList<Cliente>();
        alquileres = new ArrayList<Alquiler>();

        ingresosTotales = 0;
        siguienteNumeroAlquiler = 1;
    }

    public boolean registrarVehiculo(Vehiculo vehiculo) {

        if (vehiculo == null) {
            return false;
        }

        if (buscarVehiculo(vehiculo.getPlaca()) != null) {
            return false;
        }

        vehiculos.add(vehiculo);
        return true;        
    }

    public Vehiculo buscarVehiculo(String placa) {

        for (Vehiculo vehiculo : vehiculos) {

            if (vehiculo.getPlaca().equals(placa)) {

                return vehiculo;
            }
        }

        return null;
    }

    public boolean registrarCliente(Cliente cliente) {

        if (cliente == null) {

            return false;
        }

        if (buscarCliente(cliente.getId()) != null) {

            return false;
        }

        clientes.add(cliente);
        return true;
    }

    public Cliente buscarCliente(String id) {

        for(Cliente cliente : clientes) {

            if (cliente.getId().equals(id)) {

                return cliente;
            }
        }

        return null;
    }

    private Alquiler buscarAlquilerActivo(String placa) {

        for (Alquiler alquiler : alquileres) {

            if (alquiler.esActivo() && alquiler.getVehiculo().getPlaca().equals(placa)) {

                return alquiler;
            }
        }

        return null;
    } 

    public double calcularSubtotal(Vehiculo vehiculo, int dias) {

        return vehiculo.calcularSubtotal(dias);
    }   

    public double calcularDescuento(Cliente cliente, double subtotal) {

        return cliente.calcularDescuento(subtotal);
    }

    public double calcularTotal(double subtotal, double descuento) {

        return subtotal - descuento;
    }

    public ArrayList<String> obtenerRazonesRechazo(Cliente cliente, Vehiculo vehiculo) {

        ArrayList<String> razones = new ArrayList<String>();

        if (!vehiculo.estaDisponible()) {

            razones.add("El vehiculo no esta disponible.");
        }

        if (!vehiculo.tieneLicenciaAdecuada(cliente)) {

            razones.add("El cliente no posee una licencia adecuada.");
        }

        if (!cliente.puedeAlquilar()) {

            razones.add("El cliente alcanzo su limite de alquileres activos.");
        }

        return razones;
    }

    public Alquiler confirmarAlquiler(

        Cliente cliente, Vehiculo vehiculo, int dias) {

        if (dias <= 0) {

            throw new IllegalArgumentException("Los dias deben ser mayores que 0.");
        }

        ArrayList<String> razones = obtenerRazonesRechazo(cliente, vehiculo);
    
        if (!razones.isEmpty()) {

            return null;
        }

        double subtotal = calcularSubtotal(vehiculo, dias);
        double descuento = calcularDescuento(cliente, subtotal);
        double total = calcularTotal(subtotal, descuento);

        Alquiler alquiler = new Alquiler(siguienteNumeroAlquiler, cliente, vehiculo, dias, subtotal, descuento, total);

        alquileres.add(alquiler);

        vehiculo.marcarAlquilado();
        cliente.aumentarAlquileresActivos();
        cliente.registrarAlquilerConfirmado();

        ingresosTotales += total;
        siguienteNumeroAlquiler++;

        return alquiler;
    }

    public boolean registrarDevolucion(String placa) {

        Alquiler alquiler = buscarAlquilerActivo(placa);

        if (alquiler == null) {

            return false;
        }

        alquiler.finalizar();

        Cliente cliente = alquiler.getCliente();
        Vehiculo vehiculo = alquiler.getVehiculo();

        cliente.disminuirAlquileresActivos();
        vehiculo.registrarDevolucion(alquiler.getDias());

        return true;
    }

    public boolean finalizarMantenimiento(String placa) {

        Vehiculo vehiculo = buscarVehiculo(placa);

        if (vehiculo == null) {

            return false;

        }

        if (vehiculo.getEstado() != EstadoVehiculo.MANTENIMIENTO) {

            return false;
        }

        vehiculo.finalizarMantenimiento();

        return true;
        
    }

    public ArrayList<Vehiculo> getVehiculos() {

        return vehiculos;
    }

    public ArrayList<Cliente> getClientes() {

        return clientes;
    }

    public ArrayList<Alquiler> obtenerAlquileresActivos() {

        ArrayList<Alquiler> activos = new ArrayList<Alquiler>();

        for (Alquiler alquiler : alquileres) {

            if (alquiler.esActivo()) {

                activos.add(alquiler);
            }
        }

        return activos;
    }

    public ArrayList<Alquiler> obtenerHistorialCliente(String id) {

        ArrayList<Alquiler> historial = new ArrayList<Alquiler>();

        for (Alquiler alquiler : alquileres) {

            if (alquiler.getCliente().getId().equals(id)) {

                historial.add(alquiler);
            }
        }

        return historial;
    }
    
    public double calcularTotalPagadoCliente(String id) {

        double totalPagado = 0;

        for (Alquiler alquiler : alquileres) {

            if (alquiler.getCliente().getId().equals(id)) {

                totalPagado += alquiler.getTotal();
            }
        }

        return totalPagado;
    }

    public double calcularDescuentosTotales() {

        double descuentosTotales = 0;

        for (Alquiler alquiler : alquileres) {

            descuentosTotales += alquiler.getDescuento();
        }

        return descuentosTotales;
    }

    public double getIngresosTotales() {

        return ingresosTotales;
    }

    public double calcularIngresosCategoria(String categoria) {

        double ingresos = 0;

        for (Alquiler alquiler : alquileres) {

            if (alquiler.getVehiculo().obtenerCategoria().equals(categoria)) {

                ingresos += alquiler.getTotal();
            }
        }

        return ingresos;
    }

    public int contarVehiculosCategoria(String categoria) {

        int cantidad = 0;

        for (Vehiculo vehiculo : vehiculos) {

            if (vehiculo.obtenerCategoria().equals(categoria)) {

                cantidad++;
            }
        }

        return cantidad;

    }

    public int contarVehiculosCategoriaEstado(String categoria, EstadoVehiculo estado) {

        int cantidad = 0;

        for (Vehiculo vehiculo : vehiculos) {

            if (vehiculo.obtenerCategoria().equals(categoria) && vehiculo.getEstado() == estado) {

                cantidad++;
            }
        }

        return cantidad;
        
    }            


}