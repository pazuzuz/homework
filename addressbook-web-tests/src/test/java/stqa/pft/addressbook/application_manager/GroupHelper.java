package stqa.pft.addressbook.application_manager;

import model.Group;
import org.openqa.selenium.WebDriver;
import page_factory.PageGenerator;
import page_objects.Groups;

public class GroupHelper extends HelperBase{
    private Groups groups  = new PageGenerator(driver).getInstance(Groups.class);


    public GroupHelper(WebDriver driver) {
        super(driver);
    }

    public void initGroupCreation() {
        groups.pressNewGroup();
    }

    public void fillGroupForm(Group group) {
        groups.fillForm(group);
    }

    public void submitGroupCreation() {
        groups.pressSubmit();
    }

    public void returnToGroupPage() {
        groups.pressReturnToGroupPage();
    }

    public void deleteSelectedGroups() {
        groups.submitDeletion();
    }

    public void selectGroup() {
        groups.pickGroup();
    }

    public void initGroupModification() {
        groups.pressEdit();
    }

    public void submitGroupModification() {
        groups.confirmUpdate();
    }
}
