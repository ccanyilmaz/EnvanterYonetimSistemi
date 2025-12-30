package envanter;

import java.util.ArrayList;

public class Supplier {
    private String name;
    private String category;

    public String getName(){return name;}
    public String getCategory() {return category;}
    public void setName(String name) {this.name = name;}
    public void setCategory(String category) {this.category = category;}

    public Supplier(String name, String category){
        this.name=name;
        this.category=category;
    }

    @Override
    public String toString() {
        return "tedarikçi: " + name + " | Kategori: " + category;
    }
}
