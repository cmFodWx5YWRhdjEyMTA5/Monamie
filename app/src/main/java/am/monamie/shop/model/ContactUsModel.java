package am.monamie.shop.model;

public class ContactUsModel {
    private int imagePath;
    private String title;

    public ContactUsModel(int imagePath, String title) {
        this.imagePath = imagePath;
        this.title = title;
    }

    public int getImagePath() {
        return imagePath;
    }

    public String getTitle() {
        return title;
    }
}
