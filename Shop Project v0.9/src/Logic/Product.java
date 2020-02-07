package Logic;


import java.util.Date;

public class Product
{
    private int id;
    private String image;
    private String name;
    private int price;
    private String brand;
    private String mini_description;
    private String full_description;
    private String features;
    private Date DateTime;
    private int isNew;
    private int isLeader;
    private int isSale;
    private String type;
    private String category;
    private int brand_id;
    private String system;


    public Product(int id, String image, String name, int price, String brand, String mini_description, String full_description, String features, Date dateTime, int isNew, int isLeader, int isSale, String type, String system)
    {
        this.id = id;
        this.image = image;
        this.name = name;
        this.price = price;
        this.brand = brand;
        this.mini_description = mini_description;
        this.full_description = full_description;
        this.features = features;
        DateTime = dateTime;
        this.isNew = isNew;
        this.isLeader = isLeader;
        this.isSale = isSale;
        this.type = type;
        this.system = system;
    }
    
    public Product(int id, String name, int price)
    {
        this.id= id;
        this.name = name;
        this.price = price;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getImage()
    {
        return image;
    }

    public void setImage(String image)
    {
        this.image = image;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getPrice()
    {
        return price;
    }

    public void setPrice(int price)
    {
        this.price = price;
    }

    public String getBrand()
    {
        return brand;
    }

    public void setBrand(String brand)
    {
        this.brand = brand;
    }

    public String getMini_discription()
    {
        return mini_description;
    }

    public void setMini_discription(String mini_discription)
    {
        this.mini_description = mini_discription;
    }

    public String getFull_discription()
    {
        return full_description;
    }

    public void setFull_discription(String full_discription)
    {
        this.full_description = full_discription;
    }

    public String getFeatures()
    {
        return features;
    }

    public void setFeatures(String features)
    {
        this.features = features;
    }

    public Date getDateTime()
    {
        return DateTime;
    }

    public void setDateTime(Date dateTime)
    {
        DateTime = dateTime;
    }

    public int getIsNew()
    {
        return isNew;
    }

    public void setIsNew(int isNew)
    {
        this.isNew = isNew;
    }

    public int getIsLeader()
    {
        return isLeader;
    }

    public void setIsLeader(int isLeader)
    {
        this.isLeader = isLeader;
    }

    public int getIsSale()
    {
        return isSale;
    }

    public void setIsSale(int isSale)
    {
        this.isSale = isSale;
    }

    public String getType()
    {
        return type;
    }

    public String getSystem()
    {
        return system;
    }

    public void setSystem(String system)
    {
        this.system = system;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getCategory()
    {
        return category;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }

    public int getBrand_id()
    {
        return brand_id;
    }

    public void setBrand_id(int brand_id)
    {
        this.brand_id = brand_id;
    }
}
