package stqa.pft.addressbook.model;

public class UserData {
    private String firstName;
    private String lastName;
    private String address;
    private String mobile;
    private String email;
    private String group;
    private int id;

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

    public String getGroup() {
        return group;
    }

    public int getId() {
        return id;
    }

    public UserData withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserData withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserData withAddress(String address) {
        this.address = address;
        return this;
    }

    public UserData withMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public UserData withEmail(String email) {
        this.email = email;
        return this;
    }

    public UserData withGroup(String group) {
        this.group = group;
        return this;
    }

    public UserData withId(int id) {
        this.id = id;
        return this;
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

        return id == userData.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
