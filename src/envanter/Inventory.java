    package envanter;
    import java.io.File;
    import java.io.FileWriter;
    import java.io.IOException;
    import java.io.PrintWriter;
    import java.util.ArrayList;
    import java.util.Scanner;

    public class Inventory {
        private ArrayList<Product> products = new ArrayList<>();
        /**
          Yeni bir ürünü dinamik listeye ekler.
         */
        public void addProduct(Product p) {
            products.add(p);
            System.out.println(p.getName() + " başarıyla envantere eklendi.");
        }
        /**
         * Ürün ismi ve tedarikçi eşleşmesine göre spesifik bir ürünü listeden siler.
         * Lambda ifadeleri (removeIf) kullanılarak etkin bir arama ve silme işlemi gerçekleştirilir.
         */
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

        // DOSYA İŞLEMLERİ
        /**
         * Mevcut envanter verilerini "envanter.csv" dosyasına virgülle ayrılmış değerler olarak kaydeder.
         * Polimorfizm kullanılarak PerishableProduct olup olmadığı kontrol edilir.
         */
        public void saveToCsv() {
            try (PrintWriter out = new PrintWriter(new FileWriter("envanter.csv"))) {
                for (Product p : products) {

                    String line = p.getName() + "," + p.getStock() + "," + p.getPrice() + "," + p.getSupplier().getName();


                    if (p instanceof PerishableProduct pp) {
                        line += "," + pp.getExpirationDate();
                    }
                    out.println(line);
                }
            } catch (IOException e) {
                System.out.println("[HATA] Dosyaya kaydedilemedi: " + e.getMessage());
            }
        }
        /**
         * CSV dosyasındaki verileri satır satır okuyarak nesneye dönüştürür ve listeyi doldurur.
         * Veri yükleme sırasında nesne türünü parametre sayısına göre belirler.
         */
        public void loadFromCsv() {
            File file = new File("envanter.csv");
            if (!file.exists()) return;

            try (Scanner fileScanner = new Scanner(file)) {
                products.clear();
                while (fileScanner.hasNextLine()) {
                    String line = fileScanner.nextLine();
                    String[] data = line.split(",");


                    String name = data[0];
                    int stock = Integer.parseInt(data[1]);
                    double price = Double.parseDouble(data[2]);
                    Supplier supplier = new Supplier(data[3], "Bilinmiyor");

                    if (data.length == 5) {
                        products.add(new PerishableProduct(name, stock, price, supplier, data[4]));
                    } else {
                        products.add(new Product(name, stock, price, supplier));
                    }
                }
            } catch (Exception e) {
                System.out.println("[HATA] Veriler yüklenirken sorun çıktı: " + e.getMessage());
            }
        }
    }

