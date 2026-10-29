import java.util.Scanner;
class Producto {
    String nombre;
    double precio;

    Producto(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }
}

//strategy: cada clase de descuento calcula el total a su manera
interface Descuento {
    double aplicar(double subtotal);
}

class SinDescuento implements Descuento {
    public double aplicar(double subtotal) {
        return subtotal;
    }
}

class DescuentoLive implements Descuento {
    public double aplicar(double subtotal) {
        return subtotal * 0.90;
    }
}

class DescuentoCumpleanos implements Descuento {
    public double aplicar(double subtotal) {
        return subtotal * 0.80;
    }
}

class Venta {
    private double subtotal = 0;
    void agregarProducto(Producto producto) {
        subtotal += producto.precio;
    }
    double getSubtotal() {
        return subtotal;
    }
    double calcularTotal(Descuento descuento) {
        return descuento.aplicar(subtotal);
    }
}

//singleton: el programa usa una sola caja
class Caja {
    private static final Caja unicaCaja = new Caja();
    private Caja() {
    }
    static Caja obtenerCaja() {
        return unicaCaja;
    }
    void cobrar(double total) {
        System.out.println("Total a pagar: " + total + " Bs");
    }
}

//fachada: muestra el resumen de la venta usando venta y caja
class ResumenVenta {
    void mostrar(Venta venta, Descuento descuento) {
        double subtotal = venta.getSubtotal();
        double total = venta.calcularTotal(descuento);

        System.out.println("Subtotal: " + subtotal + " Bs");
        System.out.println("Descuento: " + (subtotal - total) + " Bs");
        Caja.obtenerCaja().cobrar(total);
    }
}

public class TiendaMaquillaje {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        Venta venta = new Venta();
        String continuar;

        do {
            System.out.print("Nombre del producto: ");
            String nombre = entrada.nextLine();
            System.out.print("Precio: ");
            double precio = Double.parseDouble(entrada.nextLine());
            venta.agregarProducto(new Producto(nombre, precio));
            System.out.print("Agregar otro producto? (si/no): ");
            continuar = entrada.nextLine();
        } while (continuar.equalsIgnoreCase("si"));

        System.out.print("Descuento (0 para ningun descuento, 1 para desc.live, 2 para desc. de cumpleaños): ");
        String opcion = entrada.nextLine();

        Descuento descuento = new SinDescuento();
        if (opcion.equals("1")) {
            descuento = new DescuentoLive();
        } else if (opcion.equals("2")) {
            descuento = new DescuentoCumpleanos();
        }
        new ResumenVenta().mostrar(venta, descuento);
        entrada.close();
    }
}