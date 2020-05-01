
package model.bean;

public class Products {
   private int id;
   private String description;
   private int quantity;
   private double price;
   
   public Products(){
       
   }

    public Products(String description, int quantity, double price) {
        this.description = description;
        this.quantity = quantity;
        this.price = price;
    }
   
   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return getDescription(); //To change body of generated methods, choose Tools | Templates.
    }
   
   
}
