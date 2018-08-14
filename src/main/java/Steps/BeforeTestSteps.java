package Steps;

import com.codeborne.selenide.Configuration;
import pages.LoginPage;
import helpers.LoadProperties;

import static com.codeborne.selenide.Selenide.open;

public class BeforeTestSteps {

    public void setupAndLogin () {

        LoginPage loginPage = new LoginPage();
        LoadProperties loadProperties = new LoadProperties();
        Configuration.remote = "http://localhost:4444/wd/hub";
        Configuration.browser = "chrome";
        Configuration.timeout = 9000;


        open(loadProperties.getProjectProperty());

        loginPage.enterUserName(loadProperties.getUserName());
        loginPage.enterPassword(loadProperties.getPassword());
        loginPage.clickSubmitButton();
    }
}
