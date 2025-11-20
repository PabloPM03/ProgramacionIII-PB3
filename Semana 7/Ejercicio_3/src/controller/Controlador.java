package controller;

import view.Vista;
import model.Modelo;

public class Controlador {

    private Modelo modelo;
    private Vista vista;

    public Controlador(Modelo modelo, Vista vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    private boolean bandera = true;
    private String isbn;

    public void iniciar() {

        do {

            switch (vista.mostrarMenuPrincipal()) {
                case '1':
                    modelo.agregarLibro();
                    break;
                case '2':
                    isbn = modelo.seleccionarLibro();
                    if (isbn != "fallo") {
                        vista.listarLibros(modelo.getLibros());
                        modelo.eliminarLibro(isbn);
                    }
                    break;
                case '3':
                    if(!modelo.checkNoLibros()) {

                        vista.listarLibros(modelo.getLibros());
                        isbn = modelo.seleccionarLibro();

                        if (isbn != "fallo") {
                            vista.listarLibros(modelo.getLibros());
                            modelo.modificarLibro(isbn, vista.mostrarMenuModificacion());
                        }
                    }
                    break;
                case '4':
                    vista.listarLibros(modelo.getLibros());
                    break;
                case 'q':
                case 'Q':
                    
                    return;
            
                default:
                    break;
            }
            
        } while (bandera);
        
        
    }



    
    //OTRAS FUNCIONES
    public void mostrarMensaje(String mensaje, int saltosAdicionales) {
        vista.mostrarMensaje(mensaje, saltosAdicionales);
    }

    public String leerTeclado(String mensaje, int saltosAdicionales) {
       return vista.leerTeclado(mensaje, saltosAdicionales);
    }

}
