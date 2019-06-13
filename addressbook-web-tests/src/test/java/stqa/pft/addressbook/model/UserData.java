package stqa.pft.addressbook.model;

import java.util.Objects;

public class UserData {
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String mobile;
    private final String email;
    private String group;
    private int id;

    public UserData(int id, String firstName, String lastName, String address, String mobile, String email, String group) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.mobile = mobile;
        this.email = email;
        this.group = group;
        this.id = id;
    }

    public UserData(String firstName, String lastName, String address, String mobile, String email, String group) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.mobile = mobile;
        this.email = email;
        this.group = group;
        this.id = 0;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserData userData = (UserData) o;

        if (!Objects.equals(firstName, userData.firstName)) return false;
        return Objects.equals(lastName, userData.lastName);
    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }
}
