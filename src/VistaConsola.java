import java.util.ArrayList;
import java.util.Scanner;

public class VistaConsola {

    private Scanner scanner;

    public VistaConsola() {

        scanner = new Scanner(System.in);
    }
    public void mostrarMenu() {

        System.out.println("\n===== RENTAMOVIL =====");
        System.out.println("1. Registrar vehiculo");
        System.out.println("2. Registrar cliente");
        System.out.println("3. Consultar vehiculos");
        System.out.println("4. Consultar clientes");
        System.out.println("5. Cotizar alquiler");
        System.out.println("6. Realizar alquiler");
        System.out.println("7. Registrar devolucion");
        System.out.println("8. Finalizar mantenimiento");
        System.out.println("9. Reporte de flota");
        System.out.println("10. Reporte de ingresos");
        System.out.println("11. Alquileres activos");
        System.out.println("12. Historial de cliente");
        System.out.println("13. Salir");
    }

    public int leerEntero(String mensaje) {

        System.out.print(mensaje);
        return scanner.nextInt();
    }

    public double leerDouble(String mensaje) {

        System.out.print(mensaje);
        return scanner.nextDouble();
    }

    public String leerTexto(String mensaje) {

        System.out.print(mensaje);
        scanner.nextLine();
        return scanner.nextLine();
    }

    public boolean leerBoolean(String mensaje) {

        System.out.print(mensaje + " (1 = si, 2 = No): ");
        int opcion = scanner.nextInt();
        return opcion == 1;
    }

    public void mostrarMensaje(String mensaje) {

        System.out.println(mensaje);
    }

    public void mostrarVehiculo(Vehiculo vehiculo) {

        System.out.println("\n--- VEHICULO ---");
        System.out.println("Placa: " + vehiculo.getPlaca());
        System.out.println("Marca: " + vehiculo.getMarca());
        System.out.println("Modelo: " + vehiculo.getModelo());
        System.out.println("Categoria: " + vehiculo.obtenerCategoria());
        System.out.println("Tarifa diaria: Q" + vehiculo.getTarifaDiaria());
        System.out.println("Estado: " + vehiculo.getEstado());
        System.out.println("Dias acumulados: " + vehiculo.getDiasAcumulados());
        System.out.println(vehiculo.obtenerDescripcion());
    }

    public void mostrarCliente(Cliente cliente) {
        System.out.println("\n--- CLIENTE ---");
        System.out.println("ID: " + cliente.getId());
        System.out.println("Nombre: " + cliente.getNombre());
        System.out.println("Licencias: " + cliente.getLicencias());
        System.out.println("Alquileres activos: " + cliente.getAlquileresActivos());
        System.out.println(cliente.obtenerDescripcion());
    }

    public void mostrarAlquiler(Alquiler alquiler) {

        System.out.println("\n--- ALQUILER ---");
        System.out.println("Numero: " + alquiler.getNumero());
        System.out.println("Cliente: " + alquiler.getCliente().getNombre());
        System.out.println("Vehiculo: " + alquiler.getVehiculo().getPlaca());
        System.out.println("Dias: " + alquiler.getDias());
        System.out.println("Subtotal: Q" + alquiler.getSubtotal());
        System.out.println("Descuento: Q" + alquiler.getDescuento());
        System.out.println("Total: Q" + alquiler.getTotal());
        System.out.println("Activo: " + alquiler.esActivo());
    }

    public void mostrarCotizacion(Vehiculo vehiculo, double subtotal, double descuento, double total, ArrayList<String> razones) {

        System.out.println("\n===== COTIZACION =====");
        mostrarVehiculo(vehiculo);

        System.out.println("Subtotal: Q" + subtotal);
        System.out.println("Descuento: Q" + descuento);
        System.out.println("Total: Q" + total);

        if (razones.isEmpty()) {

            System.out.println("El alquiler puede hacerse");
        } else {

            System.out.println("El alquiler no puede hacerse");

            for (String razon : razones) {

                System.out.println("- " + razon);
            }
        }

    }

    public void mostrarReporteFlota(SistemaRentaMovil sistema) {

        String[] categorias = {"Carro", "Moto", "Camion de carga", "Microbus" };

        System.out.println("\n===== REPORTE DE FLOTA =====");

        for (String categoria : categorias) {

            System.out.println("\n" + categoria);
            System.out.println("Total: " + sistema.contarVehiculosCategoria(categoria));

            System.out.println("Disponibles: " + sistema.contarVehiculosCategoriaEstado(categoria, EstadoVehiculo.DISPONIBLE));

            System.out.println("Alquilados: " + sistema.contarVehiculosCategoriaEstado(categoria, EstadoVehiculo.ALQUILADO));

            System.out.println("Mantenimiento: " + sistema.contarVehiculosCategoriaEstado(categoria, EstadoVehiculo.MANTENIMIENTO));
        }

    }

    public void mostrarReporteIngresos(SistemaRentaMovil sistema) {

        String[] categorias = {"Carro", "Moto", "Camion de carga", "Microbus"};

        System.out.println("\n===== REPORTE DE INGRESOS =====");
        System.out.println("Ingresos totales: Q" + sistema.getIngresosTotales());

        for (String categoria : categorias) {
            
            System.out.println(categoria + ": Q" + sistema.calcularIngresosCategoria(categoria));

        }

        System.out.println("Descuentos otorgados: Q" + sistema.calcularDescuentosTotales());


    }

    public void mostrarAlquileresActivos(ArrayList<Alquiler> alquileres) {

        System.out.println("\n===== ALQUILERES ACTIVOS =====");

        if (alquileres.isEmpty()) {System.out.println("No existen alquileres activos.");

            return;
        }

        for (Alquiler alquiler : alquileres) {

            mostrarAlquiler(alquiler);

        }

    }

    public void mostrarHistorialCliente(Cliente cliente, ArrayList<Alquiler> historial, double totalPagado) {

        System.out.println("\n===== HISTORIAL DEL CLIENTE =====");

        mostrarCliente(cliente);

        if (historial.isEmpty()) {System.out.println("El cliente no tiene alquileres registrados.");

        } else {

            for (Alquiler alquiler : historial) {

                mostrarAlquiler(alquiler);
            }
        }

        System.out.println("Total pagado: Q" + totalPagado);

    }

    public void limpiarEntrada() {

        scanner.nextLine();
    }


}