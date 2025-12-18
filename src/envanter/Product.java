package envanter;

public class Product implements Storable {
    private String name;
    private int quantity;
    private double price;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    Product(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }
    @Override
    public void displayInfo(){
        System.out.println("---ÜRÜN BİLGİLERİ---");
        System.out.println("İsim: "+name);
        System.out.println("Stok adedi: "+quantity);
        System.out.println("Fiyat: "+price+" TL");
    }

}
