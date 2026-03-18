package model;
import view.VistaAlmacen;

import java.util.ArrayList;

public class ModeloAlmacen {

    private VistaAlmacen vista;

    public ModeloAlmacen(VistaAlmacen vista) {

        this.vista = vista;
    }

    private ArrayList<AlmacenProducto> arrayAlmacenes = new ArrayList<>();
    private Double ingresosTotales = 0.0;



    public void agregarVentas() {

        AlmacenProducto almacen;
    
        for (int i = 0; i < 5; i++) {
            int ventasProducto1 = Integer.parseInt(vista.leerTeclado("[ALMACÉN " + (i+1) + "] - Introduzca el precio de Producto 1 vendido: ", 3));
            int ventasProducto2 = Integer.parseInt(vista.leerTeclado("[ALMACÉN " + (i+1) + "] - Introduzca el precio de Producto 2 vendido: ", 0));

            if (arrayAlmacenes.size() < 5) {
                almacen = new AlmacenProducto();
            }
            else {
                almacen = arrayAlmacenes.get(i);
            }
            
            almacen.setVentasProductos(ventasProducto1, ventasProducto2);
            arrayAlmacenes.add(almacen);
        }
    }



    public void modificarPrecios() {

        AlmacenProducto almacen;
    
        for (int i = 0; i < 5; i++) {
            Double precioProducto1 = Double.parseDouble(vista.leerTeclado("[ALMACÉN " + (i+1) + "] - Introduzca el precio del Producto 1: ", 3));
            Double precioProducto2 = Double.parseDouble(vista.leerTeclado("[ALMACÉN " + (i+1) + "] - Introduzca el precio del Producto 2: ", 0));

            if (arrayAlmacenes.size() < 5) {
                almacen = new AlmacenProducto();
            }
            else {
                almacen = arrayAlmacenes.get(i);
            }
            
            almacen.setPrecioProductos(precioProducto1, precioProducto2);
            arrayAlmacenes.add(almacen);
        }

    }



    public void calcularIngresos() {

        if (arrayAlmacenes.isEmpty()) {
            vista.mostrarMensaje("ERROR: No hay ninguna venta registrada!!", 3);
            return;
        }
        
        AlmacenProducto almacen = arrayAlmacenes.get(0);

        if (almacen.getPrecioProducto1() == null) {
            vista.mostrarMensaje("No has introducido el precio al que has vendido el material", 3);
            return;
        }
        
        for (int i = 0; i < 5; i++) {
            almacen = arrayAlmacenes.get(i);
            ingresosTotales = ingresosTotales + (almacen.getVentasProducto1() * almacen.getPrecioProducto1()) + (almacen.getVentasProducto2() * almacen.getPrecioProducto2());
        }
    }

    public Double getIngresosTotales() {
        return ingresosTotales;
    }

}
