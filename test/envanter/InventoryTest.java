package envanter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {
    private Inventory envanter;
    /**
     * Her test senaryosu için izole ve temiz bir envanter nesnesi oluşturur.
     */
    @BeforeEach
    void setUp() {
        envanter = new Inventory();
    }
    /**
     * Aynı isimdeki ürünlerin tedarikçi bazlı silinmesini test eder.
     * Bu test, sistemin sadece isme göre değil, "İsim + Tedarikçi" ikilisine göre
     * doğru ürünü hedefleyip hedeflemediğini doğrular.
     */
    @Test
    void spesifikTedarikciUrununuSilmeTesti() {

        Supplier s1 = new Supplier("Ahmet Gıda", "Gıda");
        Supplier s2 = new Supplier("Mehmet Gıda", "Gıda");

        Product urun1 = new Product("Süt", 10, 50.0, s1);
        Product urun2 = new Product("Süt", 20, 55.0, s2);

        envanter.addProduct(urun1);
        envanter.addProduct(urun2);
        // Belirli bir tedarikçinin ürününü silme işlemi
        envanter.removeProduct("Süt", "Ahmet Gıda");

        ArrayList<Product> sonuclar = envanter.searchProducts("Süt");

        assertEquals(1, sonuclar.size(), "Sadece bir ürün silinmeliydi.");

        assertEquals("Mehmet Gıda", sonuclar.get(0).getSupplier().getName(), "Yanlış tedarikçinin ürünü silindi!");
    }
    @Test
    void yetersizStokSatisiTesti() {
        // 1. HAZIRLIK: 10 adet stoğu olan bir ürün ekle
        Supplier s1 = new Supplier("Test Gıda", "Kategori");
        Product urun = new Product("Süt", 10, 50.0, s1);
        envanter.addProduct(urun);

        // 2. İŞLEM: 15 adet satmaya çalış (Stoktan fazla!)
        // Senin Order sınıfın veya stokAzalt metodun burada hata vermeli veya işlemi durdurmalı.
        urun.stokAzalt(15);

        // 3. DOĞRULAMA: Stok hala 10 mu kalmış? (Yani negatif -5'e düşmemiş olmalı)
        assertEquals(10, urun.getStock(), "Yetersiz stok durumunda miktar değişmemeli!");
    }

}