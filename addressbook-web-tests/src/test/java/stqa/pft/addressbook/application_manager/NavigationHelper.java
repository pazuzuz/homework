package stqa.pft.addressbook.application_manager;

import org.openqa.selenium.WebDriver;
import page_factory.PageGenerator;
import page_objects.Navigation;

public class NavigationHelper extends HelperBase{
    private Navigation navigation = new PageGenerator(driver).getInstance(Navigation.class);


    public NavigationHelper(WebDriver driver) {
        super(driver);
    }


    public void goToGroupPage() {
        navigation.openGroupPage();
    }

    public void returnToHomePage() {
        navigation.goBackToHomePage();
    }
}
