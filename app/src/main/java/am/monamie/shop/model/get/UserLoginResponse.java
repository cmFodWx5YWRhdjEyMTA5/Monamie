package am.monamie.shop.model.get;

public class UserLoginResponse {
    private Boolean success;
    private String message;
    private UserLoginResponse.Data data;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserLoginResponse.Data getData() {
        return data;
    }

    public void setData(UserLoginResponse.Data data) {
        this.data = data;
    }

    public class Data {
        private String token;
        private UserLoginResponse.User user;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public UserLoginResponse.User getUser() {
            return user;
        }

        public void setUser(UserLoginResponse.User user) {
            this.user = user;
        }
    }

    public class User {

        private String firstName;
        private String lastName;
        private String email;
        private String phone;
        private String dob;
        private String updatedAt;
        private String createdAt;
        private String id;
        private String fullName;

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getDob() {
            return dob;
        }

        public void setDob(String dob) {
            this.dob = dob;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

    }
}
