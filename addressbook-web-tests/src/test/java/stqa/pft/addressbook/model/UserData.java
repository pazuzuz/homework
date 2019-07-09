package stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@XStreamAlias("user")
@Entity
@Table(name = "addressbook")
public class UserData {

    @Expose
    @Column(name = "firstName")
    private String firstName = "";

    @Expose
    @Column(name = "lastName")
    private String lastName = "";

    @Column(name = "address")
    @Type(type = "text")
    private String address = "";

    @Expose
    @Column(name = "email")
    @Type(type = "text")
    private String firstEmail = "";

    @Column(name = "email2")
    @Type(type = "text")
    private String secondEmail = "";

    @Column(name = "email3")
    @Type(type = "text")
    private String thirdEmail = "";

    @Column(name = "work")
    @Type(type = "text")
    private String workPhone = "";

    @Column(name = "home")
    @Type(type = "text")
    private String homePhone = "";

    @Expose
    @Column(name = "mobile")
    @Type(type = "text")
    private String mobilePhone = "";

    @XStreamOmitField
    @Id
    @Column(name = "id")
    private int id;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "address_in_groups",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<GroupData> groups = new HashSet<>();

    @Transient
    private String allPhones;
    @Transient
    private String allEmails;
    @Transient
    private File photo;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public int getId() {
        return id;
    }

    public String getAllPhones() {
        return allPhones;
    }

    public String getFirstEmail() {
        return firstEmail;
    }

    public String getSecondEmail() {
        return secondEmail;
    }

    public String getThirdEmail() {
        return thirdEmail;
    }

    public String getAllEmails() {
        return allEmails;
    }

    public File getPhoto() {
        return photo;
    }

    public Groups getGroups() {
        return new Groups(groups);
    }

    public UserData withPhoto(File photo) {
        this.photo = photo;
        return this;
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

    public UserData withMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;
    }

    public UserData withId(int id) {
        this.id = id;
        return this;
    }

    public UserData withWorkPhone(String workPhone) {
        this.workPhone = workPhone;
        return this;
    }

    public UserData withHomePhone(String homePhone) {
        this.homePhone = homePhone;
        return this;
    }

    public UserData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }

    public UserData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public UserData withFirstEmail(String firstEmail) {
        this.firstEmail = firstEmail;
        return this;
    }

    public UserData withSecondEmail(String secondEmail) {
        this.secondEmail = secondEmail;
        return this;
    }

    public UserData withThirdEmail(String thirdEmail) {
        this.thirdEmail = thirdEmail;
        return this;
    }

    public UserData inGroups(GroupData group) {
        groups.add(group);
        return this;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", firstEmail='" + firstEmail + '\'' +
                ", secondEmail='" + secondEmail + '\'' +
                ", thirdEmail='" + thirdEmail + '\'' +
                ", workPhone='" + workPhone + '\'' +
                ", homePhone='" + homePhone + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserData userData = (UserData) o;

        if (id != userData.id) return false;
        if (!Objects.equals(firstName, userData.firstName)) return false;
        if (!Objects.equals(lastName, userData.lastName)) return false;
        if (!Objects.equals(address, userData.address)) return false;
        if (!Objects.equals(firstEmail, userData.firstEmail)) return false;
        if (!Objects.equals(secondEmail, userData.secondEmail))
            return false;
        if (!Objects.equals(thirdEmail, userData.thirdEmail)) return false;
        if (!Objects.equals(workPhone, userData.workPhone)) return false;
        if (!Objects.equals(homePhone, userData.homePhone)) return false;
        return Objects.equals(mobilePhone, userData.mobilePhone);
    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (firstEmail != null ? firstEmail.hashCode() : 0);
        result = 31 * result + (secondEmail != null ? secondEmail.hashCode() : 0);
        result = 31 * result + (thirdEmail != null ? thirdEmail.hashCode() : 0);
        result = 31 * result + (workPhone != null ? workPhone.hashCode() : 0);
        result = 31 * result + (homePhone != null ? homePhone.hashCode() : 0);
        result = 31 * result + (mobilePhone != null ? mobilePhone.hashCode() : 0);
        result = 31 * result + id;
        return result;
    }
}