package envanter;

public class Supplier {
    private String name;
    private String contact;
    private String category;

    public String getName(){return name;}
    public String getContact(){return contact;}
    public String getCategory() {return category;}
    public void setName(String name) {this.name = name;}
    public void setContact(String contact) {this.contact = contact;}
    public void setCategory(String category) {this.category = category;}

    public Supplier(String name, String contact, String category){
        this.name=name;
        this.contact=contact;
        this.category=category;
    }
    @Override
    public String toString() {
        return "tedarikçi: " + name + " | Kategori: " + category + " | İletişim: " + contact;
    }
}
