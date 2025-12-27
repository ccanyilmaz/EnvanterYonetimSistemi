package envanter;

import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        SupplierManager supplierManager = new SupplierManager();
        Scanner scanner = new Scanner(System.in);

        // Arka planda sessizce verileri yükle
        Supplier s1 = new Supplier("Ahmet Gıda", "temel gıda");
        Supplier s2 = new Supplier("Manav Mehmet", "Sebze-Meyve");
        supplierManager.addSupplier(s1);
        supplierManager.addSupplier(s2);

        inventory.addProduct(new PerishableProduct("Süt", 50, 70, s1, "12.12.2026"));
        inventory.addProduct(new PerishableProduct("Yoğurt", 40, 100, s1, "31.12.2026"));
        inventory.addProduct(new PerishableProduct("Elma", 100, 15.0, s2, "07Mai.06.2026"));
        inventory.addProduct(new PerishableProduct("Muz", 80, 40.0, s2, "08.09.2026"));


    }
}