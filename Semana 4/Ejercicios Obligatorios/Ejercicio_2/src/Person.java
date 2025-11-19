public class Person {
    
    //Atriburos
    private String name;
    private Float height, weight;

    /*
    public Person (String name, float height, float weight) {
        this.name = name;
        this.height = height;
        this.weight = weight;
    }*/

    public float getIMC() {
        
        if (this.name == null || this.height == null || this.weight == null) {
            return -1;
        }

        float imc = (this.weight * (this.height * this.height));

        return imc;
    }




    public String getName() {
        return this.name;
    }
    public float getHeight() {
        return this.height;
    }
    public float getWeight() {
        return this.weight;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setHeight(Float height) {
        this.height = height;
    }
    public void setWeight(Float weight) {
        this.weight = weight;
    }

}
