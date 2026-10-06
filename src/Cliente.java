import java.util.ArrayList;

public abstract class Cliente {

    private String id;
    private String nombre;
    private ArrayList<TipoLicencia> licencias;
    private int alquileresActivos;
    protected Cliente(String id, String nombre, ArrayList<TipoLicencia> licencias) {

        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("El identificador no puede estar vacio.");
        }

        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacio.");
        }

        if (licencias == null || licencias.isEmpty()) {
            throw new IllegalArgumentException("El cliente debe presentar al menos una licencia.");
        }

        this.id = id;
        this.nombre = nombre;
        this.licencias = licencias;
        this.alquileresActivos = 0;

    }

    public abstract double calcularDescuento(double subtotal);

    public abstract int obtenerLimiteAlquileres();

    public abstract void registrarAlquilerConfirmado();

    public abstract String obtenerDescripcion();

    public boolean puedeAlquilar() {
    return alquileresActivos < obtenerLimiteAlquileres();
    }

    public boolean tieneLicencia(TipoLicencia licencia) {

        if (licencias.contains(licencia)) {
            return true;
        }

        if (licencia == TipoLicencia.C) {
            return licencias.contains(TipoLicencia.B)
                    || licencias.contains(TipoLicencia.A);
        }

        if (licencia == TipoLicencia.B) {
            return licencias.contains(TipoLicencia.A);
        }

        return false;
    }

    public void aumentarAlquileresActivos() {
    alquileresActivos++;
    }

    public void disminuirAlquileresActivos() {
        if (alquileresActivos > 0) {
            alquileresActivos--;
        }
    }

    public String getId() {
    return id;
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<TipoLicencia> getLicencias() {
        return licencias;
    }

    public int getAlquileresActivos() {
        return alquileresActivos;
    }
}