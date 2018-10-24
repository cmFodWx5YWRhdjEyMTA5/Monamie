package am.monamie.shop.model.get;

import java.io.Serializable;

public class ProductCategoriesResponse implements Serializable{
    private long id;
    private String name;
    private String imageUrl;

    public ProductCategoriesResponse(long id, String name, String imageUrl) {
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
