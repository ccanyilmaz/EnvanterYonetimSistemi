    package envanter;
    import java.util.ArrayList;
    public class Inventory {
        private ArrayList<Product> products = new ArrayList<>();

        public void addProduct(Product p) {
            products.add(p);
            System.out.println(p.getName() + " başarıyla envantere eklendi.");
        }
        public void removeProduct(String productName, String supplierName) {

            boolean silindiMi = products.removeIf(p ->
                    p.getName().equalsIgnoreCase(productName) &&
                            p.getSupplier().getName().equalsIgnoreCase(supplierName)
            );

            if (silindiMi) {
                System.out.println("[ONAY] " + supplierName + " tedarikçisine ait '" + productName + "' silindi.");
            } else {
                System.out.println("[HATA] " + supplierName + " tedarikçisinden '" + productName + "' bulunamadı!");
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

        public ArrayList<Product> getProducts() {
            return this.products;
        }
    }

