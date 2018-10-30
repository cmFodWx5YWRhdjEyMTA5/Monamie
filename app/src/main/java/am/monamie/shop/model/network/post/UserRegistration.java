package am.monamie.shop.model.network.post;

import java.io.Serializable;

public class UserRegistration implements Serializable {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String password;
    private String dob;

    public UserRegistration(String firstName, String lastName, String email,String phone, String password, String dob) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.dob = dob;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getDob() {
        return dob;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "UserRegistration{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", dob='" + dob + '\'' +
                '}';
    }
}
