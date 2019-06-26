package stqa.pft.addressbook.application_manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import stqa.pft.addressbook.model.UserData;
import stqa.pft.addressbook.model.Users;

import java.util.List;

public class UserHelper extends HelperBase{
    private Users usersCache = null;

    public UserHelper(WebDriver driver) {
        super(driver);
    }

    public void initAddNewUser() {
        click(By.xpath("//a[@href='edit.php']"));
    }

    public void fillUserForm(UserData userData, boolean isUserCreation) {
        type(By.name("firstname"), userData.getFirstName());
        type(By.name("lastname"), userData.getLastName());
        type(By.name("address"), userData.getAddress());
        type(By.name("mobile"), userData.getMobilePhone());
        type(By.name("work"), userData.getWorkPhone());
        type(By.name("home"), userData.getHomePhone());
        type(By.name("email"), userData.getFirstEmail());
        type(By.name("email2"), userData.getSecondEmail());
        type(By.name("email3"), userData.getThirdEmail());
        attach(By.name("photo"), userData.getPhoto());

        if (isUserCreation){
            new Select(driver.findElement(By.name("new_group"))).selectByVisibleText(userData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void submitNewUserForm() {
        click(By.xpath("(//input[@name='submit'])[2]"));
    }

    private void initModifyUserById(int id) {
        driver.findElement(By.cssSelector("a[href='edit.php?id=" + id + "']")).click();
    }

    public void submitUpdateUserForm() {
        click(By.xpath("//input[@name='update']"));
    }

    private void selectUserById(int id) {
        driver.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void deleteSelectedUsers() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public int count() {
        return driver.findElements(By.name("selected[]")).size();
    }

    public void create(UserData userData, boolean isUserCreation) {
        initAddNewUser();
        fillUserForm(userData, isUserCreation);
        submitNewUserForm();
        usersCache = null;
        returnToHomePage();
    }

    public void delete(UserData user) {
        selectUserById(user.getId());
        deleteSelectedUsers();
        if (isAlertPresent()){
            acceptAlert();
        }
        usersCache = null;
    }

    public void modify(UserData user) {
        initModifyUserById(user.getId());
        fillUserForm(user, false);
        submitUpdateUserForm();
        usersCache = null;
        returnToHomePage();
    }

    public boolean isThereAUser() {
        return isElementPresent(By.name("selected[]"));
    }

    public Users all() {
        if (usersCache != null){
            return new Users(usersCache);
        }
        usersCache = new Users();
        List<WebElement> elements = driver.findElements(By.xpath("//tr[@name='entry']"));
        for (WebElement element: elements ) {
            List<WebElement> cells = element.findElements(By.tagName("td"));
            String lastname = cells.get(1).getText();
            String firstname = cells.get(2).getText();
            String address = cells.get(3).getText();
            String allEmails = cells.get(4).getText();
            String allPhones = cells.get(5).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
            usersCache.add(
                    new UserData()
                        .withId(id)
                        .withFirstName(firstname)
                        .withLastName(lastname)
                        .withAddress(address)
                        .withAllEmails(allEmails)
                        .withAllPhones(allPhones))
            ;
        }
        return new Users(usersCache);
    }

    public void returnToHomePage() {
        if (isElementPresent(By.id("maintable"))){
            return;
        }
        click(By.linkText("home page"));
    }

    public UserData infoFromEditForm(UserData user) {
        initModifyUserById(user.getId());
        String firstname = driver.findElement(By.name("firstname")).getAttribute("value");
        String lastname = driver.findElement(By.name("lastname")).getAttribute("value");
        String address = driver.findElement(By.name("address")).getAttribute("value");
        String firstEmail = driver.findElement(By.name("email")).getAttribute("value");
        String secondEmail = driver.findElement(By.name("email2")).getAttribute("value");
        String thirdEmail = driver.findElement(By.name("email3")).getAttribute("value");
        String home = driver.findElement(By.name("home")).getAttribute("value");
        String mobile = driver.findElement(By.name("mobile")).getAttribute("value");
        String work = driver.findElement(By.name("work")).getAttribute("value");
        driver.navigate().back();
        return
                new UserData()
                        .withId(user.getId())
                        .withFirstName(firstname)
                        .withLastName(lastname)
                        .withAddress(address)
                        .withFirstEmail(firstEmail)
                        .withSecondEmail(secondEmail)
                        .withThirdEmail(thirdEmail)
                        .withHomePhone(home)
                        .withWorkPhone(work)
                        .withMobilePhone(mobile);
    }
}
