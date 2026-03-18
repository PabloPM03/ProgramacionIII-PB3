import controller.ControladorAlmacen;
import model.ModeloAlmacen;
import view.VistaAlmacen;

public class Main {
    
    public static void main(String[] args) {
        
        VistaAlmacen vista = new VistaAlmacen();
        ModeloAlmacen modelo = new ModeloAlmacen(vista);
        ControladorAlmacen controlador = new ControladorAlmacen(modelo, vista);

        controlador.iniciarControlador();
    }

}
