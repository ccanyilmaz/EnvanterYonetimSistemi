package envanter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class ProductTest {
private Product urun;
private Supplier tedarikci;
    /**
     * Her test öncesi örnek bir tedarikçi ve ürün nesnesi hazırlar.
     */
    @BeforeEach
    void setUp() {
        tedarikci=new Supplier("Sütaş","Süt Ürünleri");
        urun=new Product("Süt", 100, 20.0, tedarikci);
    }

    /**
     * Stok artırma işleminin hem geçerli hem de geçersiz (negatif)
     * durumlardaki davranışını test eder.
     */
    @Test
    void stokArtir() {
        urun.stokArtir(-50);
        assertEquals(100,urun.getStock(),"Negatif değer stoğu değiştirmemeli!");

        urun.stokArtir(20);
        assertEquals(120,urun.getStock());
    }
    /**
     * Stok azaltım işlemini test eder.
     * Yetersiz stok durumunda işlemin reddedildiğini (Business Logic)
     * ve geçerli miktarlarda stoğun doğru düştüğünü kontrol eder.
     */
    @Test
    void stokAzalt() {
        urun.stokAzalt(150);
        assertEquals(100,urun.getStock(),"Yetersiz stokta miktar düşmemeli!");

        urun.stokAzalt(80);
        assertEquals(20,urun.getStock());

    }


}