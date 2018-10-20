package am.monamie.shop.model;

import java.io.Serializable;

public class ProductCategories implements Serializable{
    private String name;
    private String ImageUrl;

    public ProductCategories(String name, String imageUrl) {
        this.name = name;
        ImageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    @Override
    public String toString() {
        return "ProductCategories{" +
                "name='" + name + '\'' +
                ", ImageUrl='" + ImageUrl + '\'' +
                '}';
    }
}
