package stqa.pft.addressbook.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import stqa.pft.addressbook.application_manager.ApplicationManager;

public class TestBase {

    protected final ApplicationManager applicationManager = new ApplicationManager();

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        applicationManager.init();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        applicationManager.stop();
    }

}
