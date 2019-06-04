package stqa.pft.addressbook.model;

public class NewUserData {
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String mobile;
    private final String email;
    private String group;

    public NewUserData(String firstName, String lastName, String address, String mobile, String email, String group) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.mobile = mobile;
        this.email = email;
        this.group = group;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getAddress() {
        return this.address;
    }

    public String getMobile() {
        return this.mobile;
    }

    public String getEmail() {
        return this.email;
    }

    public String getGroup() {
        return group;
    }
}
