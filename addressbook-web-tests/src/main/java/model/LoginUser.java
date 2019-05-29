package model;

public enum LoginUser {
    ADMIN("admin","secret"),
    ;

    private String username;
    private String password;

    LoginUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
