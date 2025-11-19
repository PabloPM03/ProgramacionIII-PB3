public class Coche {

    private String marca, modelo;
    private int anioMatriculacion;
    private int id;


    public Coche(int id, String marca, String modelo, int anioMatriculacion) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.anioMatriculacion = anioMatriculacion;
    }

    public int getId() {
        return id;
    }
    public String getMarca() {
        return marca;
    }
    public String getModelo() {
        return modelo;
    }
    public int getAnioMatriculacion() {
        return anioMatriculacion;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public void setAnioMatriculacion(int anioMatriculacion) {
        this.anioMatriculacion = anioMatriculacion;
    }
}
