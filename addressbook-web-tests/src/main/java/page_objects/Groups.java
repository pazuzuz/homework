package page_objects;

import model.Group;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import page_factory.BasePage;

public class Groups extends BasePage {
    public Groups(WebDriver driver) {
        super(driver);
    }

    @FindBy(how = How.NAME, using = "group_name")
    public WebElement groupName;

    @FindBy(how = How.NAME, using = "group_header")
    public WebElement groupHeader;

    @FindBy(how = How.NAME, using = "group_footer")
    public WebElement groupFooter;

    @FindBy(how = How.LINK_TEXT, using = "group page")
    public WebElement returnToGroupPageButton;

    @FindBy(how = How.NAME, using = "selected[]")
    public WebElement selectGroup;

    @FindBy(how = How.NAME, using = "delete")
    public WebElement deleteGroupsButton;

    @FindBy(how = How.NAME, using = "new")
    public WebElement addNewGroupButton;

    @FindBy(how = How.NAME, using = "submit")
    public WebElement submitButton;

    @FindBy(how = How.NAME, using = "edit")
    public WebElement editButton;

    @FindBy(how = How.NAME, using = "update")
    public WebElement confirmUpdate;

    public void fillForm(Group group) {
        writeText(groupName, group.getName());
        writeText(groupHeader, group.getHeader());
        writeText(groupFooter, group.getFooter());
    }

    public void pressEdit() {
        click(editButton);
    }

    public void confirmUpdate() {
        click(confirmUpdate);
    }

    public void pressNewGroup(){
        click(addNewGroupButton);
    }

    public void pressSubmit() {
        click(submitButton);
    }

    public void submitDeletion() {
        click(deleteGroupsButton);
    }

    public void pressReturnToGroupPage() {
        click(returnToGroupPageButton);
    }

    public void pickGroup() {
        click(selectGroup);
    }
}
