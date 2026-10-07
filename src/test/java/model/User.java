package model;

public class User {

    private String email;
    private String password;
    private String submitPassword;

    public User(String email, String password, String submitPassword) {
        this.email = email;
        this.password = password;
        this.submitPassword = submitPassword;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getSubmitPassword() {
        return submitPassword;
    }
}