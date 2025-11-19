public class Person {
    
    //Atriburos
    private String name;
    private float height, weight;

    /*
    public Person (String name, float height, float weight) {
        this.name = name;
        this.height = height;
        this.weight = weight;
    }*/

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
