package Logic;


import java.util.Date;

public class Product {
    private int id;
    private String image;
    private String name;
    private int price;
    private String mini_description;
    private String full_description;
    private Date DateTime;
    private int category;
    private int subcategory;
    private int count;
    private  User seller;

    Product(int id, String image, String name, int price, String full_description, Date dateTime, int count, int subcategory,int category, User seller) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.price = price;
        this.full_description = full_description;
        DateTime = dateTime;
        this.count = count;
        this.seller = seller;
        this.category = category;
        this.subcategory = subcategory;
    }

    Product(int id, String image, String name, int price, String mini_description, String full_description, Date dateTime, int category, int subcategory, int count) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.price = price;
        this.mini_description = mini_description;
        this.full_description = full_description;
        DateTime = dateTime;
        this.category = category;
        this.subcategory = subcategory;
        this.count = count;
    }

    Product(int id, String image, String name, int price, String mini_description, String full_description, Date dateTime, int subcategory, int count) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.price = price;
        this.mini_description = mini_description;
        this.full_description = full_description;
        DateTime = dateTime;
        this.subcategory = subcategory;
        this.count = count;
    }

    Product(int id, String image, String name, int price, String mini_description, Date dateTime, int count) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.price = price;
        this.mini_description = mini_description;
        DateTime = dateTime;
        this.count = count;
    }

    public Product(int subcateg_id, int user_id, int price, String name, String description, String img_url) {
        this.subcategory=subcateg_id;
        this.seller = new User(user_id);
        this.price = price;
        this.name = name;
        this.full_description = description;
        this.image = img_url;

    }

    public Product(int subcateg_id, int user_id, int price, int count, String name, String description, String img_url) {
        this.subcategory=subcateg_id;
        this.seller = new User(user_id);
        this.price = price;
        this.count = count;
        this.name = name;
        this.full_description = description;
        this.image = img_url;

    }

    public Product(int id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Product(int id, String image, String name, int price, String full_description, int subcategory, int count) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.price = price;
        this.full_description = full_description;
        this.subcategory = subcategory;
        this.count = count;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getMini_description() {
        return mini_description;
    }

    public void setMini_description(String mini_description) {
        this.mini_description = mini_description;
    }

    public String getFull_description() {
        return full_description;
    }

    public void setFull_description(String full_description) {
        this.full_description = full_description;
    }

    public Date getDateTime() {
        return DateTime;
    }

    public void setDateTime(Date dateTime) {
        DateTime = dateTime;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(int subcategory) {
        this.subcategory = subcategory;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
