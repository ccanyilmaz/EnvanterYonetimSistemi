package envanter;

public class Product implements Storable {
    private String name;
    private int stock;
    private double price;
    private Supplier supplier;

    public Product(String name, int stock, double price,Supplier supplier) {
        this.name = name;
        this.stock =stock;
        this.price = price;
        this.supplier=supplier;
    }
    /**
     * Storable arayüzünden ezilen metot: Stoğu belirtilen miktarda artırır.
     * Negatif miktar girişine karşı korumalıdır.
     */
    @Override
    public void stokArtir(int quantity) {
        if (quantity>0) {
            this.stock += quantity;
            System.out.println(name+" stoğuna "+quantity+" eklendi. Yeni stok: "+stock);
        }
    }
    /**
     * Storable arayüzünden ezilen metot: Stok miktarını düşürür.
     * Miktarın pozitif olmasını ve mevcut stoktan fazla olmamasını kontrol eder.
     */
    @Override
    public void stokAzalt(int quantity) {
        if (quantity > 0 && quantity <= stock) {
            this.stock -= quantity;
            System.out.println(name+" stoğundan "+quantity+" düşürüldü. Yeni stok "+stock);
        }
        else
            System.out.println("HATA: Yetersiz stok!");
    }

    public String getName() { return name; }
    public int getStock() { return stock; }
    public double getPrice() { return price; }
    public Supplier getSupplier(){return supplier;}

    public void setName(String name) {this.name = name;}

     //Fiyat güncellemesi yapar. Fiyatın negatif olamayacağı kuralını denetler.
    public void setPrice(double price) {
        if (price >= 0) {
            this.price = price;
        } else {
            System.out.println("Hata: Fiyat 0'dan küçük olamaz!");
        }
    }
    public void setStock(int stock) {
        if (stock >= 0) {
            this.stock = stock;
        } else {
            System.out.println("Hata: Stok negatif olamaz!");

        }
    }
    public void setSupplier(Supplier supplier) {this.supplier = supplier;}
    /**
     * Ürün bilgilerini okunabilir bir formatta döndürür.
     */
    @Override
    public String toString() {
        return "Ürün:" + name + " | stok: "+stock + " | fiyat: " + price + " | tedarikçi: " + supplier.getName();
    }
}
