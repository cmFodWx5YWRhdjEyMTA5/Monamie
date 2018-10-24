package am.monamie.shop.model.post;

public class CreateDevice {
    private String deviceId;
    private String token;
    private String deviceType;

    public CreateDevice(String deviceId, String token, String deviceType) {
        this.deviceId = deviceId;
        this.token = token;
        this.deviceType = deviceType;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public String getToken() {
        return token;
    }

    public String getDeviceType() {
        return deviceType;
    }

    @Override
    public String toString() {
        return "CreateDevice{" +
                "deviceId='" + deviceId + '\'' +
                ", token='" + token + '\'' +
                ", deviceType='" + deviceType + '\'' +
                '}';
    }
}
