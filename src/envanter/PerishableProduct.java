package envanter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
public class PerishableProduct extends Product{
    private String expirationDate;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public PerishableProduct(String name,int stock,double price,Supplier supplier,String expirationDate){
        super(name, stock, price, supplier);
        this.expirationDate=expirationDate;
    }
    public long getDaysUntilExpiration(){
        try {
            LocalDate skt= LocalDate.parse(this.expirationDate,DATE_FORMATTER);
            LocalDate bugun=LocalDate.now();
            return ChronoUnit.DAYS.between(bugun,skt);
        }
        catch (Exception e){
            return 999;
        }
    }
    @Override
    public String toString(){
        long daysLeft = getDaysUntilExpiration();
        String status = (daysLeft<0)?" [!!!  TARİHİ GEÇMİŞ ÜRÜN !!!]": " (" + daysLeft + " gün kaldı)";
        return super.toString() +" | SKT: " +expirationDate+status;
    }

    public String getExpirationDate(){
        return expirationDate;
    }
    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }
}
