import controller.Controlador;
import model.ModeloPersona;
import view.Vista;

public class App {
    public static void main(String[] args) {
        ModeloPersona m = new ModeloPersona();
        Vista v = new Vista();
        Controlador c = new Controlador(m, v);
        
        c.init();
    }
}