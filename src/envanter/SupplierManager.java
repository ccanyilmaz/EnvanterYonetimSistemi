package envanter;

import java.util.ArrayList;

public class SupplierManager {
    private ArrayList<Supplier> suppliers=new ArrayList<>();

    //Yeni bir tedarikçiyi sisteme dahil eder.
    public void addSupplier(Supplier s){
        suppliers.add(s);
        System.out.println("Bilgi: " + s.getName() + " sisteme eklendi.");
    }
    // Kayıtlı tedarikçilerin tam listesini döndürür.
    public ArrayList<Supplier> getSuppliers() {
        return suppliers;
    }
    /**
     * İsim üzerinden tedarikçi araması yapar.
     * Eşleşme durumunda nesneyi, aksi halde null döndürür.
     */
    public Supplier findSupplier(String name){
        for (Supplier s : suppliers){
            if(s.getName().equalsIgnoreCase(name))
                return s;
        }
        return null;
    }
    /**
     * Mevcut tüm tedarikçileri konsola formatlı şekilde yazdırır.
     * Liste boşsa kullanıcıya uyarı verir.
     */
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
