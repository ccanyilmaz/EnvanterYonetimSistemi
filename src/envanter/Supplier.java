package envanter;

public class Supplier {
    private String name;

    public String getName(){return name;}
    Supplier(String name){
        this.name=name;
    }
    @Override
    public String toString() {
        return "tedarikçi:" + name;
    }
}
