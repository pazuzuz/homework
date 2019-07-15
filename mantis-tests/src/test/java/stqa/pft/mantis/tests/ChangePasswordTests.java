package stqa.pft.mantis.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ChangePasswordTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions(){
        if (app.db().users().size() == 0){

        }
    }

    @Test
    public void testChangePassword(){

    }
}
