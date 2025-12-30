    package envanter;
    import java.util.ArrayList;
    public class Inventory {
        private ArrayList<Product> products = new ArrayList<>();

        public void addProduct(Product p) {
            products.add(p);
            System.out.println(p.getName() + " başarıyla envantere eklendi.");
        }
        public void removeProduct(String name) {
            // removeIf bir eleman silerse true döner, bulamazsa false döner.
            boolean silindiMi = products.removeIf(p -> p.getName().equalsIgnoreCase(name));

            if (silindiMi) {
                System.out.println("[ONAY] " + name + " envanterden başarıyla silindi.");
            } else {
                System.out.println("[HATA] '" + name + "' adında bir ürün bulunamadı!");
            }
        }

        public ArrayList<Product> searchProducts(String name) {
            ArrayList<Product> found = new ArrayList<>();
            for (Product p : products) {
                if (p.getName().equalsIgnoreCase(name)) {
                    found.add(p);
                }
            }
            return found;
        }

        public void displayInventory() {
            if (products.isEmpty()) {
                System.out.println("Envanter şu an boş.");
            } else {
                System.out.println("\n--- GÜNCEL ENVANTER LİSTESİ ---");
                for (Product p : products) {
                    System.out.println(p);
                }
            }
        }
    }

