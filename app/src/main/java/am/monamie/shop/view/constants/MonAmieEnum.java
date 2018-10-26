package am.monamie.shop.view.constants;

public enum MonAmieEnum {

    DEVICE_TYPE("android"),
    USER_TOKEN("user.token"),
    TOKEN_FCM("device_token"),
    FIRST_NAME("firstName"),
    LAST_NAME("lastName"),
    EMAIL("email"),
    PASSWORD("password"),
    FULL_NAME("fullName");


    private String value;

    MonAmieEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
