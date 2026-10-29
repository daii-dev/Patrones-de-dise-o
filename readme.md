# Sistema de Ventas de Maquillaje

Sistema desarrollado en Java que permite registrar productos de maquillaje, sumar sus precios y aplicar descuentos del 10% por live de TikTok o del 20% por cumpleaños.

## Patrones de diseño utilizados

- **Singleton (Creacional):** Se utiliza en la clase `Caja` para tener una sola caja en el programa.
- **Fachada (Estructural):** Se utiliza en la clase `ResumenVenta` para reunir los pasos del resumen y cobro de una venta.
- **Strategy (Comportamiento):** Se utiliza en la interfaz `Descuento` y sus clases para aplicar diferentes tipos de descuentos.

## Ejecucion del proyecto

**Para compilar:**
```bash
javac TiendaMaquillaje.java
```

**Para ejecutar:**
```bash
java TiendaMaquillaje
```