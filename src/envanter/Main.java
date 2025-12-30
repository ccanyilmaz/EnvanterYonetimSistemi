package envanter;

import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        SupplierManager supplierManager = new SupplierManager();

        Supplier s1 = new Supplier("Ahmet Gıda", "temel gıda");
        Supplier s2 = new Supplier("Manav Mehmet", "Sebze-Meyve");
        supplierManager.addSupplier(s1);
        supplierManager.addSupplier(s2);


        inventory.addProduct(new PerishableProduct("Süt", 50, 70, s1, "12.12.2026"));
        inventory.addProduct(new PerishableProduct("Yoğurt", 40, 100, s1, "31.12.2026"));
        inventory.addProduct(new PerishableProduct("Elma", 100, 15.0, s2, "07.06.2026"));
        inventory.addProduct(new PerishableProduct("Muz", 80, 40.0, s2, "08.09.2026"));


        System.out.println("\n\n-----ENVANTER YONETIM SISTEMI-----");
        System.out.println("\nMerhaba, lütfen yapmak istediğiniz işlemi seçiniz.");

        boolean running = true;
        while (running) {
            printMenu();
            int choice = getIntSafe("Seciminiz: ");

            switch (choice) {
                case 1:
                    inventory.displayInventory();
                    break;
                case 2:
                    String searchName = getStringSafe("Aranacak urun adi: ");
                    ArrayList<Product> results = inventory.searchProducts(searchName);
                    if (results.isEmpty()) {
                        System.out.println("[!] Urun bulunamadi.");
                    } else {
                        Product selectedProduct = selectProductFromList(results);
                        if (selectedProduct != null) {
                            int qty = getIntSafe("Kac adet almak istiyorsunuz? ");
                            if (qty > 0) {
                                new Order(selectedProduct, qty).processOrder();
                            } else {
                                System.out.println("[!] Gecersiz miktar.");
                            }
                        }
                    }
                    break;
                case 3:
                    String updateName = getStringSafe("Stok guncellenecek urun adi: ");
                    ArrayList<Product> updateResults = inventory.searchProducts(updateName);
                    if (updateResults.isEmpty()) {
                        System.out.println("[!] Urun bulunamadi.");
                    } else {
                        Product productToUpdate = selectProductFromList(updateResults);
                        if (productToUpdate != null) {
                            System.out.println("Mevcut Stok: " + productToUpdate.getStock());
                            int newStock = getIntSafe("Yeni stok miktari: ");
                            productToUpdate.setStock(newStock);
                            System.out.println("[ONAY] Stok guncellendi.");
                        }
                    }
                    break;
                    case 4: // YENİ ÜRÜN EKLE
                    System.out.println("\n--- Yeni Urun Girisi ---");
                    String name = getStringSafe("Urun Adi: ");
                    int stock = getIntSafe("Stok Adedi: ");
                    double price = getDoubleSafe("Birim Fiyat: ");
                    String date = getStringSafe("Son Kul. Tar. (Orn: 01.01.2025): ");

                    // --- TEDARİKÇİ SEÇİMİ BURADA BAŞLIYOR ---
                    System.out.println("\nLutfen bir tedarikci seciniz:");
                    ArrayList<Supplier> allSuppliers = supplierManager.getSuppliers(); // Tüm tedarikçileri al

                    if (allSuppliers.isEmpty()) {
                        System.out.println("[!] Sistemde hic tedarikci yok! Once tedarikci eklemelisiniz.");
                        break;
                    }

                    // Tedarikçileri numaralandırarak listele
                    for (int i = 0; i < allSuppliers.size(); i++) {
                        System.out.println((i + 1) + ". " + allSuppliers.get(i).getName());
                    }

                    int sChoice;
                    Supplier selectedSupplier = null;
                    while (selectedSupplier == null) {
                        sChoice = getIntSafe("Tedarikci No: ");
                        if (sChoice > 0 && sChoice <= allSuppliers.size()) {
                            selectedSupplier = allSuppliers.get(sChoice - 1);
                        } else {
                            System.out.println("[!] Gecersiz numara, tekrar deneyin.");
                        }
                    }
                    // --- TEDARİKÇİ SEÇİMİ BİTTİ ---

                    // Artık s1 yerine seçilen selectedSupplier kullanılıyor
                    inventory.addProduct(new PerishableProduct(name, stock, price, selectedSupplier, date));
                    System.out.println("[BASARILI] " + name + " (" + selectedSupplier.getName() + ") sisteme eklendi.");
                    break;
                case 5:
                    String delName = getStringSafe("Silinecek urun adi: ");
                    inventory.removeProduct(delName);
                    break;
                case 6:
                    System.out.println("\n1. Listele\n2. Yeni Ekle");
                    int sub = getIntSafe("Secim: ");
                    if (sub == 1) supplierManager.listSuppliers();
                    else if (sub == 2) {
                        supplierManager.addSupplier(new Supplier(getStringSafe("Ad: "), getStringSafe("Kategori: ")));
                    }
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("[!] Gecersiz secim.");
            }
        }
        scanner.close();
    }

    // --- YARDIMCI METOTLAR ---
    private static void printMenu() {
        System.out.println("\n1-Ürün Listele | 2-Ürün Ara/Sat | 3-Stok GÜncelleme | 4-Ürün Ekle | 5-Ürün Sil | 6-Tedarikci Yönetim | 0-Cikis");
    }

    private static int getIntSafe(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (Exception e) {
                System.out.println("[HATA] Sayi giriniz!");
            }
        }
    }

    private static double getDoubleSafe(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Double.parseDouble(scanner.nextLine().replace(",", ".").trim());
            } catch (Exception e) {
                System.out.println("[HATA] Gecersiz fiyat!");
            }
        }
    }

    private static String getStringSafe(String message) {
        String input = "";
        while (input.trim().isEmpty()) {
            System.out.print(message);
            input = scanner.nextLine();
        }
        return input;
    }

    private static Product selectProductFromList(ArrayList<Product> products) {
        if (products.size() == 1) return products.get(0);
        for (int i = 0; i < products.size(); i++) {
            System.out.println((i + 1) + ". " + products.get(i));
        }
        while (true) {
            int choice = getIntSafe("Secim (Iptal: 0): ");
            if (choice == 0) return null;
            if (choice > 0 && choice <= products.size()) return products.get(choice - 1);
        }
    }
}