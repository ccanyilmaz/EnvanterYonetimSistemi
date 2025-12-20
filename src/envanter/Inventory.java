package envanter;
import java.util.ArrayList;
public class Inventory {
    private ArrayList<Product>products=new ArrayList<>();
    public void addProduct(Product p){
        products.add(p);
        System.out.println(p.getName() + " başarıyla envantere eklendi.");
    }
}
