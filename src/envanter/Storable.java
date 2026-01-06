package envanter;

public interface Storable {

    //Ürünün mevcut stok miktarını artırmak için kullanılır.
    void stokArtir(int quantity);

    //Ürünün mevcut stok miktarını düşürmek için kullanılır.
    void stokAzalt(int quantity);
}
