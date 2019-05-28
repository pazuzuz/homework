package stqa.pft.addressbook.tests;

import exceptions.NoSuchBrowserException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import stqa.pft.addressbook.application_manager.ApplicationManager;

public class TestBase {

    protected final ApplicationManager applicationManager = new ApplicationManager();

    @Parameters("browser")
    @BeforeMethod(alwaysRun = true)
    public void setUp(@Optional("chrome") String browser) throws NoSuchBrowserException {
        applicationManager.init(browser);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        applicationManager.stop();
    }

}
