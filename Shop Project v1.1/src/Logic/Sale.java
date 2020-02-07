package Logic;


import java.util.Date;

public class Sale {
    int id;
    User seller,buyer;
    Date date;
    String address;
    Product product;

    public Sale(int id, int seller, int buyer, Date date, String address) {
        this.id = id;
        this.seller = new User(seller);
        this.buyer = new User(buyer);
        this.date = date;
        this.address = address;
    }

    public Sale(int id, User seller, User buyer, Date date, String address, Product product) {
        this.id = id;
        this.seller = seller;
        this.buyer = buyer;
        this.date = date;
        this.address = address;
        this.product = product;
    }
}
