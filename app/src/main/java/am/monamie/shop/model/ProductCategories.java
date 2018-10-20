package am.monamie.shop.model;

import java.io.Serializable;

public class ProductCategories implements Serializable{
    private long id;
    private String name;
    private String imageUrl;

    public ProductCategories(long id, String name, String imageUrl) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
