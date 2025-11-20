public class Controlador {

    private Modelo modelo;
    private Vista vista;

    public Controlador(Modelo modelo, Vista vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    public void iniciarControlador() {
        
        boolean flag = true;
        String opcion;
        char opcionChar;

        do {

            opcion = vista.mostrarMenuPrincipal();
            opcionChar = opcion.charAt(0);

            switch (opcionChar) {
                case '1':

                    modelo.guardarNumero(1, vista.leerNumero(1));
                    modelo.guardarNumero(2, vista.leerNumero(2));

                    vista.mostrarMensaje("Números introducidos correctamente", 3);
                    
                break;


                case '2':

                    modelo.calcularSuma();
                    vista.mostrarMensaje("Suma realizada", 3);
                    
                break;


                case '3':

                    vista.mostrarResultado(modelo.getSuma(), 3);
                    
                break;


                case 'q':
                case 'Q':
                        vista.mostrarMensaje("Gracias por utilizar nuestro programa", 3);
                return;
            

                default:
                    vista.mostrarMensaje("Cáracter introducino NO VÁLIDO", 3);
                break;
            }
            
        } while (flag);

    }
}
