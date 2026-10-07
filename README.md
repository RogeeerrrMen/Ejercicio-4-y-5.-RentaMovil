# RentaMovil

Ejercicio 4 y 5 de Programación Orientada a Objetos.

El programa simula un sistema de alquiler de vehículos utilizando herencia, clases abstractas, sobrescritura de métodos y polimorfismo.

## Compilación y ejecución

Desde la raíz del proyecto:

```bash
javac -d bin src/*.java
```

Para ejecutar:

```bash
java -cp bin Main
```

## Datos precargados

El programa inicia con vehículos y clientes precargados para facilitar las pruebas de las distintas reglas del sistema.

### Vehículos

| Placa | Categoría | Marca | Modelo | Tarifa diaria | Características |
|---|---|---|---|---:|---|
| `Carro001` | Carro | Toyota | Corolla | Q250 | 5 pasajeros, transmisión automática |
| `Carro002` | Carro | Honda | Civic | Q225 | 5 pasajeros, transmisión manual |
| `Moto001` | Moto | Honda | CZ330 | Q125 | 190 cc |
| `Moto002` | Moto | Yamaha | R5 | Q175 | 321 cc |
| `Cam001` | Camión de carga | Isuzu | NRA | Q400 | Capacidad máxima de 1.5 toneladas |
| `Cam002` | Camión de carga | Hino | 350 | Q450 | Capacidad máxima de 2.5 toneladas |
| `Micro001` | Microbús | Toyota | Twice | Q450 | 15 pasajeros, incluye piloto |
| `Micro002` | Microbús | Hyundai | H3 | Q400 | 12 pasajeros, no incluye piloto |

### Clientes

| ID | Nombre / Empresa | Tipo | Licencia | Información adicional |
|---|---|---|---|---|
| `2694795832875` | Erick | Individual | C | Inicia con 3 alquileres confirmados |
| `8567349593254` | Kimberly | Individual | M | Inicia sin alquileres confirmados |
| `Nit467836` | Intelaf | Corporativo | A | Contacto: Douglas |
| `Nit473842` | Steren | Corporativo | B | Contacto: Magda |


## Opciones del programa

El menú principal permite:

1. Registrar vehículo
2. Registrar cliente
3. Consultar vehículos
4. Consultar clientes
5. Cotizar alquiler
6. Realizar alquiler
7. Registrar devolución
8. Finalizar mantenimiento
9. Consultar reporte de flota
10. Consultar reporte de ingresos
11. Consultar alquileres activos
12. Consultar historial de un cliente
13. Salir


El análisis, diseño y diagrama UML utilizados para la implementación se encuentran en la carpeta `docs`.

Universidad del Valle de Guatemala
