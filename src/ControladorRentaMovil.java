import java.util.ArrayList;


public class ControladorRentaMovil {

    private SistemaRentaMovil sistema;
    private VistaConsola vista;
    private boolean ejecutando;

    public ControladorRentaMovil() {

        sistema = new SistemaRentaMovil();
        vista = new VistaConsola();
        ejecutando = true;
    }

    public void iniciar() {

        cargarDatosIniciales();

        while (ejecutando) {

            try {

                vista.mostrarMenu();
                int opcion = vista.leerEntero("Seleccione una opcion: ");
                procesarOpcion(opcion);

            } catch (NumberFormatException e) {

                manejarEntradaInvalida();

            } catch (IllegalArgumentException e) {

                vista.mostrarMensaje("Error: " + e.getMessage());
            }
        }
    }

    private void procesarOpcion(int opcion) {


        switch (opcion) {

            case 1:
                registrarVehiculo();
                break;

            case 2:
                registrarCliente();
                break;

            case 3:
                consultarVehiculos();
                break;

            case 4:
                consultarClientes();
                break;

            case 5:
                cotizarAlquiler();
                break;

            case 6:
                realizarAlquiler();
                break;

            case 7:
                registrarDevolucion();
                break;

            case 8:
                finalizarMantenimiento();
                break;

            case 9:
                mostrarReporteFlota();
                break;

            case 10:
                mostrarReporteIngresos();
                break;

            case 11:
                mostrarAlquileresActivos();
                break;

            case 12:
                mostrarHistorialCliente();
                break;

            case 13:
                ejecutando = false;
                vista.mostrarMensaje("Programa finalizado.");
                break;

            default:
                vista.mostrarMensaje("Opcion invalida.");
        
        }
    }

    private void cargarDatosIniciales() {

        sistema.registrarVehiculo(new Carro("Carro001", "Toyota", "Corolla", 250, 5, true));

        sistema.registrarVehiculo(new Carro("Carro002", "Honda", "Civic", 225, 5, false));

        sistema.registrarVehiculo(new Moto("Moto001", "Honda", "CZ330", 125, 190));

        sistema.registrarVehiculo(new Moto("Moto002", "Yamaha", "R5", 175, 321));

        sistema.registrarVehiculo(new CamionCarga("Cam001", "Isuzu", "NRA", 400, 1.5));

        sistema.registrarVehiculo(new CamionCarga("Cam002", "Hino", "350", 450, 2.5));

        sistema.registrarVehiculo(new Microbus("Micro001", "Toyota", "Twice", 450, 15, true));

        sistema.registrarVehiculo(new Microbus("Micro002", "Hyundai", "H3", 400, 12, false));

        ArrayList<TipoLicencia> licenciasIndividual1 = new ArrayList<TipoLicencia>();

        licenciasIndividual1.add(TipoLicencia.C);

        ClienteIndividual individual1 = new ClienteIndividual("2694795832875","Erick",licenciasIndividual1);

        individual1.registrarAlquilerConfirmado();
        individual1.registrarAlquilerConfirmado();
        individual1.registrarAlquilerConfirmado();

        sistema.registrarCliente(individual1);

        ArrayList<TipoLicencia> licenciasIndividual2 = new ArrayList<TipoLicencia>();

        licenciasIndividual2.add(TipoLicencia.M);

        sistema.registrarCliente(new ClienteIndividual("8567349593254","Kimberly", licenciasIndividual2));

        ArrayList<TipoLicencia> licenciasEmpresa1 = new ArrayList<TipoLicencia>();

        licenciasEmpresa1.add(TipoLicencia.A);

        sistema.registrarCliente(new ClienteCorporativo("Nit467836", "Intelaf", "Douglas", licenciasEmpresa1));

        ArrayList<TipoLicencia> licenciasEmpresa2 = new ArrayList<TipoLicencia>();

        licenciasEmpresa2.add(TipoLicencia.B);

        sistema.registrarCliente(new ClienteCorporativo("Nit473842", "Steren", "Magda", licenciasEmpresa2));

    }

    private void registrarVehiculo() {

        vista.mostrarMensaje("\n1. Carro");
        vista.mostrarMensaje("2. Moto");
        vista.mostrarMensaje("3. Camion de carga");
        vista.mostrarMensaje("4. Microbus");

        int opcion = vista.leerEntero("Seleccione categoria: ");

        switch (opcion) {

            case 1:
                registrarCarro();
                break;
            case 2:
                registrarMoto();
                break;
            case 3:
                registrarCamionCarga();
                break;
            case 4:
                registrarMicrobus();
                break;
            default:
                vista.mostrarMensaje("Categoria invalida.");

        }

    }

    private void registrarCarro() {

        String placa = vista.leerTexto("Placa: ");
        String marca = vista.leerTexto("Marca: ");
        String modelo = vista.leerTexto("Modelo: ");
        double tarifa = vista.leerDouble("Tarifa diaria: ");
        int pasajeros = vista.leerEntero("Cantidad de pasajeros: ");
        boolean automatico = vista.leerBoolean("Tiene transmision automatica?");

        Carro carro = new Carro(placa, marca, modelo, tarifa, pasajeros, automatico);

        if (sistema.registrarVehiculo(carro)) {

            vista.mostrarMensaje("Carro registrado");
        } else {

            vista.mostrarMensaje("Ya existe un vehiculo con esa placa");
        }
    }

    private void registrarMoto() {

        String placa = vista.leerTexto("Placa: ");
        String marca = vista.leerTexto("Marca: ");
        String modelo = vista.leerTexto("Modelo: ");
        double tarifa = vista.leerDouble("Tarifa diaria: ");
        int cilindraje = vista.leerEntero("Cilindraje: ");

        Moto moto = new Moto(placa, marca, modelo, tarifa, cilindraje);

        if (sistema.registrarVehiculo(moto)) {

            vista.mostrarMensaje("Moto registrada");
        } else {

            vista.mostrarMensaje("Ya existe un vehiculo con esa placa");
        }
    }

    private void registrarCamionCarga() {

        String placa = vista.leerTexto("Placa: ");
        String marca = vista.leerTexto("Marca: ");
        String modelo = vista.leerTexto("Modelo: ");
        double tarifa = vista.leerDouble("Tarifa diaria: ");
        double capacidad = vista.leerDouble("Capacidad maxima en toneladas: ");

        CamionCarga camion = new CamionCarga(placa, marca, modelo, tarifa, capacidad);

        if (sistema.registrarVehiculo(camion)) {

            vista.mostrarMensaje("Camion registrado");
        } else {

            vista.mostrarMensaje("Ya existe un vehiculo con esa placa");
        }
    }

    private void registrarMicrobus() {

        String placa = vista.leerTexto("Placa: ");
        String marca = vista.leerTexto("Marca: ");
        String modelo = vista.leerTexto("Modelo: ");
        double tarifa = vista.leerDouble("Tarifa diaria: ");
        int pasajeros = vista.leerEntero("Cantidad de pasajeros: ");
        boolean piloto = vista.leerBoolean("Incluye piloto?");

        Microbus microbus = new Microbus (placa, marca, modelo, tarifa, pasajeros, piloto);

        if (sistema.registrarVehiculo(microbus)) {

            vista.mostrarMensaje("Microbus registrado");
        } else {

            vista.mostrarMensaje("Ya existe un vehiculo con esa placa");
        }
    }

    private void registrarCliente() {

        vista.mostrarMensaje("\n1. Cliente individual");
        vista.mostrarMensaje("2. Cliente corporativo");

        int opcion = vista.leerEntero("Seleccione tipo: ");

        switch (opcion) {
            case 1:
                registrarClienteIndividual();
                break;
            case 2:
                registrarClienteCorporativo();
                break;
            default:
                vista.mostrarMensaje("Tipo de cliente invalido");
        }
    }

    private void registrarClienteIndividual() {

        String dpi = vista.leerTexto("DPI: ");
        String nombre = vista.leerTexto("Nombre: ");

        ArrayList<TipoLicencia> licencias = solicitarLicencias();

        ClienteIndividual cliente = new ClienteIndividual(dpi, nombre, licencias);

        if (sistema.registrarCliente(cliente)) {

            vista.mostrarMensaje("Cliente registrado");
        } else {

            vista.mostrarMensaje("Ya existe un cliente con ese ID");
        }
    }

    private void registrarClienteCorporativo() {

        String nit = vista.leerTexto("NIT: ");
        String empresa = vista.leerTexto("Nombre de empresa: ");
        String contacto = vista.leerTexto("Nombre del contacto: ");

        ArrayList<TipoLicencia> licencias = solicitarLicencias();

        ClienteCorporativo cliente = new ClienteCorporativo(nit, empresa, contacto, licencias);

        if (sistema.registrarCliente(cliente)) {

            vista.mostrarMensaje("Cliente corporativo registrado");
        } else {

            vista.mostrarMensaje("Ya existe un cliente con ese ID");
        }

    }

     private ArrayList<TipoLicencia> solicitarLicencias() {

        ArrayList<TipoLicencia> licencias = new ArrayList<TipoLicencia>();

        boolean continuar = true;

        while (continuar) {

            vista.mostrarMensaje("\n1. Licencia A");
            vista.mostrarMensaje("2. Licencia B");
            vista.mostrarMensaje("3. Licencia C");
            vista.mostrarMensaje("4. Licencia M");

            int opcion = vista.leerEntero("Seleccione licencia: ");

            switch (opcion) {
                case 1:
                    licencias.add(TipoLicencia.A);
                    break;
                case 2:
                    licencias.add(TipoLicencia.B);
                    break;
                case 3:
                    licencias.add(TipoLicencia.C);
                    break;
                case 4:
                    licencias.add(TipoLicencia.M);
                    break;
                default:
                    vista.mostrarMensaje("Licencia invalida");

            }

            continuar = vista.leerBoolean("Desea agregar otra licencia?");
        }

        return licencias;
    }

    private void consultarVehiculos() {

        ArrayList<Vehiculo> vehiculos = sistema.getVehiculos();

        for (Vehiculo vehiculo : vehiculos) {

            vista.mostrarVehiculo(vehiculo);
        }
    }

    private void consultarClientes() {

        ArrayList<Cliente> clientes = sistema.getClientes();

        for (Cliente cliente : clientes) {

            vista.mostrarCliente(cliente);
        }
    }

    private void cotizarAlquiler() {

        String placa = vista.leerTexto("Placa del vehiculo: ");
        String id = vista.leerTexto("ID del cliente: ");
        int dias = vista.leerEntero("Dias de alquiler: ");

        Vehiculo vehiculo = sistema.buscarVehiculo(placa);
        Cliente cliente = sistema.buscarCliente(id);

        if (vehiculo == null || cliente == null) {

            vista.mostrarMensaje("Vehiculo o cliente no encontrado");
            return;
        }

        if (dias <= 0) {

            vista.mostrarMensaje("Los dias deben ser mayores a 0");
            return;
        }

        double subtotal = sistema.calcularSubtotal(vehiculo, dias);
        double descuento = sistema.calcularDescuento(cliente, subtotal);
        double total = sistema.calcularTotal(subtotal, descuento);

        ArrayList<String> razones = sistema.obtenerRazonesRechazo(cliente, vehiculo);

        vista.mostrarCotizacion(vehiculo, subtotal, descuento, total, razones);

    }

    private void realizarAlquiler() {

        String placa = vista.leerTexto("Placa del vehiculo: ");
        String id = vista.leerTexto("ID del cliente: ");
        int dias = vista.leerEntero("Dias de alquiler: ");

        Vehiculo vehiculo = sistema.buscarVehiculo(placa);
        Cliente cliente = sistema.buscarCliente(id);

         if (vehiculo == null || cliente == null) {

            vista.mostrarMensaje("Vehiculo o cliente no encontrado");
            return;
        }

        if (dias <= 0) {

            vista.mostrarMensaje("Los dias deben ser mayores que 0");
            return;
        }

        double subtotal = sistema.calcularSubtotal(vehiculo, dias);
        double descuento = sistema.calcularDescuento(cliente, subtotal);
        double total = sistema.calcularTotal(subtotal, descuento);

        ArrayList<String> razones = sistema.obtenerRazonesRechazo(cliente, vehiculo);

        vista.mostrarCotizacion(vehiculo, subtotal, descuento, total, razones);

        if (!razones.isEmpty()) {

            return;
        }

        boolean confirmar = vista.leerBoolean("Desea confirmar el alquiler?");

        if (!confirmar) {

            vista.mostrarMensaje("Alquiler cancelado");
            return;
        }

        Alquiler alquiler = sistema.confirmarAlquiler(cliente, vehiculo, dias);

        if (alquiler != null) {

            vista.mostrarMensaje("Alquiler confirmado");
            vista.mostrarAlquiler(alquiler);
        }
    }

    private void registrarDevolucion() {

        String placa = vista.leerTexto("Placa del vehiculo: ");

        if (sistema.registrarDevolucion(placa)) {

            vista.mostrarMensaje("Devolucion registrada");

        } else {

            vista.mostrarMensaje("No existe un alquiler activo para ese vehiculo");

        }
    }

    private void finalizarMantenimiento() {

        String placa = vista.leerTexto("Placa del vehiculo: ");

        if (sistema.finalizarMantenimiento(placa)) {

            vista.mostrarMensaje("Mantenimiento finalizado");

        } else {

            vista.mostrarMensaje("El vehiculo no existe o no esta en mantenimiento");

        }
    }

    private void mostrarReporteFlota() {

        vista.mostrarReporteFlota(sistema);
    }

    private void mostrarReporteIngresos() {

        vista.mostrarReporteIngresos(sistema);
    }

    private void mostrarAlquileresActivos() {

        vista.mostrarAlquileresActivos(sistema.obtenerAlquileresActivos());

    }

    private void mostrarHistorialCliente() {

        String id = vista.leerTexto("ID del cliente: ");

        Cliente cliente = sistema.buscarCliente(id);

        if (cliente == null) {

            vista.mostrarMensaje("Cliente no encontrado");
            return;
        }

        ArrayList<Alquiler> historial = sistema.obtenerHistorialCliente(id);

        double totalPagado = sistema.calcularTotalPagadoCliente(id);

        vista.mostrarHistorialCliente(cliente, historial, totalPagado);

    }

    private void manejarEntradaInvalida() {

        vista.mostrarMensaje("Entrada invalida.");
        vista.limpiarEntrada();
    }

}