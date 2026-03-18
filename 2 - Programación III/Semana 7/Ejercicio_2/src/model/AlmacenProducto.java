package model;

public class AlmacenProducto {

    private int ventasProducto1, ventasProducto2;
    private Double precioProducto1, precioProducto2;

    public Double getPrecioProducto1() {
        return precioProducto1;
    }
    public Double getPrecioProducto2() {
        return precioProducto2;
    }
    public int getVentasProducto1() {
        return ventasProducto1;
    }
    public int getVentasProducto2() {
        return ventasProducto2;
    }

    public void setPrecioProductos(Double precioProducto1, Double precioProducto2) {
        this.precioProducto1 = precioProducto1;
        this.precioProducto2 = precioProducto2;
    }
    public void setVentasProductos(int ventasProducto1, int ventasProducto2) {
        this.ventasProducto1 = ventasProducto1;
        this.ventasProducto2 = ventasProducto2;
    }

}
