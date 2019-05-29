package model;

public enum User {
    MORBO("Morbo","Annulyator","New New York City, 12313, Westend","80993452312","morbo_annulyator@gmail.com"),
    BENDER("Bender","Rodriges","New New York City, 432442, Eastend","1-321-424-43-34","bender@gmail.com")
    ;

    private String firstName;
    private String lastName;
    private String address;
    private String mobile;
    private String email;

    User(String firstName, String lastName, String address, String mobile, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.mobile = mobile;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getMobile() {
        return mobile;
    }

    public String getEmail() {
        return email;
    }
}
