package envanter;

public class Order {
    private Product product;
    private int quantity;
    private double totalPrice;
    // Siparişi oluşturur ve toplam fiyatı birim fiyat üzerinden hesaplar.
    public Order(Product product,int quantity){
        this.product=product;
        this.quantity=quantity;
        this.totalPrice= product.getPrice()*quantity;

    }
    /**
     * Satış işlemini gerçekleştirir.
     * Stok miktarını kontrol eder; yeterli stok varsa düşüm yapar ve makbuz yazdırır.
     * Yetersiz stok durumunda kullanıcıyı bilgilendirir.
     */
    public void processOrder(){
        if(product.getStock()>=quantity){
            product.stokAzalt(quantity);
            printReceipt();
        }
        else{
            System.out.println("\n[SİSTEM MESAJI]: İşlem başarısız. Yetersiz stok!");
            System.out.println("Talep edilen: "+ quantity +" | Mevcut: " + product.getStock());
        }
    }
    // Satış sonrası profesyonel bir terminal çıktısı (makbuz) oluşturur.
    private void printReceipt(){
        System.out.println("\n******************************");
        System.out.println("       SATIŞ MAKBUZU");
        System.out.println("******************************");
        System.out.println("Ürün: " + product.getName());
        System.out.println("Tedarikçi: " + product.getSupplier().getName());
        System.out.println("Adet: " + quantity);
        System.out.println("Birim fiyat: " + product.getPrice()+" TL");
        System.out.println("TOPLAM TUTAR : " + totalPrice + " TL");
        System.out.println("******************************");
        System.out.println("İşlem başarıyla tamamlandı.\n");
    }
    public double getTotalPrice(){
        return totalPrice;
    }
}
