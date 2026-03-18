import controller.Controlador;
import view.Vista;
import model.Modelo;

public class App {
    public static void main(String[] args) throws Exception {
        
        Vista vista = new Vista();
        Modelo modelo = new Modelo();
        Controlador controlador = new Controlador(modelo, vista);
        modelo.setControlador(controlador);

        controlador.iniciar();
    }
}
