package envanter;

import java.util.ArrayList;

public class SupplierManager {
    private ArrayList<Supplier> suppliers=new ArrayList<>();

    public void addSupplier(Supplier s){
        suppliers.add(s);
        System.out.println("Bilgi: " + s.getName() + " sisteme eklendi.");
    }

    public Supplier findSupplier(String name){
        for (Supplier s : suppliers){
            if(s.getName().equalsIgnoreCase(name))
                return s;
        }
        return null;
    }

    public void listSuppliers(){
        System.out.println("\n---KAYITLI TEDARİKÇİLER---");
        if (suppliers.isEmpty())
            System.out.println("Listeniz boş. Önce tedarikçi ekleyin.");
        else {
            for (Supplier s : suppliers)
                System.out.println(s.toString());
        }

    }
}
