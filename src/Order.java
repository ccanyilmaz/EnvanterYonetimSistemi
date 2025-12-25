import envanter.Product;

public class Order {
    private Product product;
    private int quantity;
    private double totalPrice;

    public Order(Product product,int quantity){
        this.product=product;
        this.quantity=quantity;
        this.totalPrice= product.getPrice()*quantity;

    }
}
