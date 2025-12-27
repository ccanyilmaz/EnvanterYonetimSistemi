package envanter;

public class PerishableProduct extends Product{
    private String expirationDate;
    public PerishableProduct(String name,int stock,double price,Supplier supplier,String expirationDate){
        super(name, stock, price, supplier);
        this.expirationDate=expirationDate;
    }
    @Override
    public String toString(){
        return super.toString() +" | SKT: " +expirationDate;
    }

    public String getExpirationDate(){
        return expirationDate;
    }
    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }
}
