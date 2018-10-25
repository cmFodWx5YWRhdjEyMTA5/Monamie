package am.monamie.shop.view.constants;

public enum MonamieEnum {

    DEVICE_TYPE("android"),
    USER_TOKEN("user.token"),
    FIRST_NAME("firstName"),
    LAST_NAME("lastName"),
    EMAIL("email"),
    FULL_NAME("fullName");


    private String value;
    MonamieEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
