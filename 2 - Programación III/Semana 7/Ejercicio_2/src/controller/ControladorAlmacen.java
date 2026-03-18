package controller;

import model.ModeloAlmacen;
import view.VistaAlmacen;


public class ControladorAlmacen {

    private ModeloAlmacen modelo;
    private VistaAlmacen vista;

    private boolean bandera = true;


    public ControladorAlmacen(ModeloAlmacen modelo, VistaAlmacen vista) {
        this.modelo = modelo;
        this.vista = vista;
    }






    public void iniciarControlador() {

        do {

            switch (vista.mostrarMenuPrincipal()) {
                case '1':
                    modelo.agregarVentas();
                    break;
                case '2':
                    modelo.modificarPrecios();
                    break;
                case '3':
                    modelo.calcularIngresos();
                    break;
                case '4':
                    vista.MostrarResultados(modelo.getIngresosTotales());
                    break;
                case 'q':
                case 'Q':
                    
                    return;
            
                default:
                    break;
            }
            
        } while (bandera);
        
        
    }
}
