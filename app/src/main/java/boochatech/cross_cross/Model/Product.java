package boochatech.cross_cross.Model;

/**
 * Created by User on 6/5/2016.
 */
public class Product {
    String name;
    double rating;
    String description;
    double price;
    int image;
    int shopLogo;

    public Product(String name, double rating, String description, double price, int image, int shopLogo) {
        this.name = name;
        this.rating = rating;
        this.description = description;
        this.price = price;
        this.image = image;
        this.shopLogo = shopLogo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getShopLogo() {
        return shopLogo;
    }

    public void setShopLogo(int shopLogo) {
        this.shopLogo = shopLogo;
    }
}
