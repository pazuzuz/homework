package stqa.pft.addressbook;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.fail;

public class UserCreationTest {
  private WebDriver driver;

  @BeforeMethod(alwaysRun = true)
  public void setUp(){
    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    login("admin", "secret");
  }

  @AfterMethod(alwaysRun = true)
  public void tearDown(){
    logout();
    driver.quit();
  }

  @Test
  public void testUserCreation(){
    initAddNewUser();
    fillNewUserForm(new AddNewUserData("Morbo", "Annulyator", "New New York City, 12313, Westend", "80993452312", "morbo_annulyator@gmail.com"));
    submitNewUserForm();
    returnToHomePage();

  }

  private void login(String username, String password) {
    driver.get("http://localhost/addressbook/");
    driver.findElement(By.name("user")).sendKeys(username);
    driver.findElement(By.name("pass")).sendKeys(password);
    driver.findElement(By.xpath("//input[@value='LOGIN']")).click();
  }

  private void initAddNewUser() {
    driver.findElement(By.xpath("//a[@href='edit.php']")).click();
  }

  private void fillNewUserForm(AddNewUserData addNewUserData) {
    driver.findElement(By.name("firstname")).sendKeys(addNewUserData.getFirstName());
    driver.findElement(By.name("lastname")).sendKeys(addNewUserData.getLastName());
    driver.findElement(By.name("address")).sendKeys(addNewUserData.getAddress());
    driver.findElement(By.name("mobile")).sendKeys(addNewUserData.getMobile());
    driver.findElement(By.name("email")).sendKeys(addNewUserData.getEmail());
  }

  private void submitNewUserForm() {
    driver.findElement(By.xpath("(//input[@name='submit'])[2]")).click();
  }

  private void returnToHomePage() {
    driver.findElement(By.linkText("home page")).click();
  }
  private void logout() {
    driver.findElement(By.linkText("LOGOUT")).click();
  }
  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }
}
